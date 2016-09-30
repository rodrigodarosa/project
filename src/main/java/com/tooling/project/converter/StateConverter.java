package com.tooling.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tooling.project.facade.StateFacade;
import com.tooling.project.model.State;

@FacesConverter(value = "stateConverter")
public class StateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		State state = new State();
		if (string != null && !string.equals("")) {
		StateFacade stateFacade = new StateFacade();
		 state = stateFacade.retrieveByExactName(string);
		}
		return state;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		State state = new State();
		if(o instanceof State){
			state = (State) o;
		}else{
			return "";
		}
		return state.getName();
	}

}
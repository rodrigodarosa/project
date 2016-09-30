package com.tooling.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tooling.project.facade.CityFacade;
import com.tooling.project.model.City;

@FacesConverter(value = "cityConverter")
public class CityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

		City city = new City();
		if (string != null && !string.equals("")) {
			CityFacade cityFacade = new CityFacade();
			city = cityFacade.retrieveByExactName(string);
		}
		return city;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		City city = new City();
		if (o instanceof City) {
			city = (City) o;
		} else {
			return "";
		}
		return city.getName();
	}

}
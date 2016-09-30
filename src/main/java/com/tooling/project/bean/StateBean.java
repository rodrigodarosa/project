package com.tooling.project.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.tooling.project.facade.StateFacade;
import com.tooling.project.model.State;

@ManagedBean
@ViewScoped
public class StateBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private State entity;
	private StateFacade stateFacade;

	public State getEntity() {
		if (entity == null) {
			entity = new State();
		}
		return entity;
	}

	public void setEntity(State entity) {
		this.entity = entity;
	}

	public StateFacade getStateFacade() {
		if (stateFacade == null) {
			stateFacade = new StateFacade();
		}
		return stateFacade;
	}

	public void setStateFacade(StateFacade stateFacade) {
		this.stateFacade = stateFacade;
	}

	public String save() {
		getStateFacade().save(entity);
		entity = new State();
		return "";
	}

	public String remove() {
		getStateFacade().remove(entity);
		return "";

	}

	public State retrieve(Long id) {
		return getStateFacade().retrieve(id);
	}

	public void update(State entity) {
		getStateFacade().update(entity);

	}

	public List<State> getAll() {
		return getStateFacade().getAll();
	}
	
}

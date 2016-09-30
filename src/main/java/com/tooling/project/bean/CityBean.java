package com.tooling.project.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.tooling.project.facade.CityFacade;
import com.tooling.project.model.City;
import com.tooling.project.util.Business;

@ManagedBean
@ViewScoped
public class CityBean implements Business<City> {

	private static final long serialVersionUID = 1L;
	private City entity;
	private CityFacade cityFacade;

	public City getEntity() {
		if (entity == null) {
			entity = new City();
		}
		return entity;
	}

	public void setEntity(City entity) {
		this.entity = entity;
	}

	public CityFacade getCityFacade() {
		if (cityFacade == null) {
			cityFacade = new CityFacade();
		}
		return cityFacade;
	}

	public void setCityFacade(CityFacade cityFacade) {
		this.cityFacade = cityFacade;
	}

	@Override
	public void save(City entity) {
		getCityFacade().save(entity);

	}

	@Override
	public void remove(City entity) {
		getCityFacade().remove(entity);

	}

	@Override
	public City retrieve(Long id) {
		return getCityFacade().retrieve(id);
	}

	@Override
	public void update(City entity) {
		getCityFacade().update(entity);

	}

	@Override
	public List<City> getAll() {
		return getCityFacade().getAll();
	}

}

package com.tooling.project.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.tooling.project.datamodel.ProviderDataModel;
import com.tooling.project.facade.CityFacade;
import com.tooling.project.facade.ProviderFacade;
import com.tooling.project.model.City;
import com.tooling.project.model.Provider;
import com.tooling.project.model.State;
import com.tooling.project.util.Util;
import com.tooling.project.validators.ProviderValidator;

@ManagedBean
@ViewScoped
public class ProviderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Provider entity;
	private ProviderFacade providerFacade;
	private ProviderDataModel providerDataModel;
	private Provider entitySelected;
	private State state;
	private City city;
	private CityFacade cityFacade;
	private List<City> cities;
	private List<Provider> filteredProviders;
	
	public List<Provider> getFilteredProviders() {
		return filteredProviders;
	}

	public void setFilteredProviders(List<Provider> filteredProviders) {
		this.filteredProviders = filteredProviders;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Provider getEntity() {
		if (entity == null) {
			entity = new Provider();
		}
		return entity;
	}

	public void setEntity(Provider entity) {
		this.entity = entity;
	}

	public ProviderFacade getProviderFacade() {
		if (providerFacade == null) {
			providerFacade = new ProviderFacade();
		}
		return providerFacade;
	}

	public void setProviderFacade(ProviderFacade providerFacade) {
		this.providerFacade = providerFacade;
	}

	public ProviderDataModel getProviderDataModel() {
		return providerDataModel;
	}

	public void setProviderDataModel(ProviderDataModel providerDataModel) {
		this.providerDataModel = providerDataModel;
	}

	public Provider getEntitySelected() {
		return entitySelected;
	}

	public void setEntitySelected(Provider entitySelected) {
		this.entitySelected = entitySelected;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public ProviderBean() {
		providerDataModel = new ProviderDataModel(getAll());
	}

	public String save() {
		if (city != null) {
			entity.setCity(city);
		}
		if (ProviderValidator.validate(entity)) {
			getProviderFacade().save(entity);
			entity = new Provider();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fornecedor", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			city = null;
			state = null;
		}
		return "providers";

	}

	public String remove() {
		getProviderFacade().remove(entitySelected);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fornecedor", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);

		return "providers";
	}

	public Provider retrieve(Long id) {
		return getProviderFacade().retrieve(id);
	}

	public void update() {
		if (city != null) {
			entitySelected.setCity(city);
		}
		if (ProviderValidator.validate(entitySelected)) {
			getProviderFacade().update(entitySelected);
			entitySelected = new Provider();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fornecedor", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			city = null;
			state = null;
		}
	}

	public List<Provider> getAll() {
		return getProviderFacade().getAll();
	}

	public List<Provider> complete(String complete) {
		List<Provider> results = getProviderFacade().retrieveByName(complete);
		return results;

	}

	public void handleCityChange() {
		if (state != null) {
			cities = getCityFacade().retrieveByState(state);
		}
	}

	public void edit() {
		if (getEntitySelected() != null && getEntitySelected().getCity() != null) {
			state = getEntitySelected().getCity().getState();
			city = getEntitySelected().getCity();
			cities = getCityFacade().retrieveByState(state);
		} else {
			city = null;
			state = null;
		}
	}
	
	public String extractReport() throws IOException {
		Util.extractReport("RelatorioFornecedores-", getFilteredProviders() == null ? getAll() : getFilteredProviders(), "providerReport.jrxml");
		return "";
	}
	
	public String extractInformation() throws IOException {
		List<Provider> providers = new ArrayList<Provider>();
		providers.add(getEntitySelected());
		Util.extractReport("Fornecedor-"+providers.get(0).getId()+"-", providers, "providerInformation.jrxml");
		return "";
	}

}

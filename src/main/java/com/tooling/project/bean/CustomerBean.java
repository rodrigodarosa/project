package com.tooling.project.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.tooling.project.datamodel.CustomerDataModel;
import com.tooling.project.facade.CityFacade;
import com.tooling.project.facade.CustomerFacade;
import com.tooling.project.model.City;
import com.tooling.project.model.Customer;
import com.tooling.project.model.State;
import com.tooling.project.util.Util;
import com.tooling.project.validators.CustomerValidator;

@ManagedBean
@ViewScoped
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Customer entity;
	private CustomerFacade customerFacade;
	private Customer entitySelected;
	private CustomerDataModel customerDataModel;
	private State state;
	private City city;
	private CityFacade cityFacade;
	private List<City> cities;
	private List<Customer> filteredCustomers;
	
	public List<Customer> getFilteredCustomers() {
		return filteredCustomers;
	}

	public void setFilteredCustomers(List<Customer> filteredCustomers) {
		this.filteredCustomers = filteredCustomers;
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

	public Customer getEntity() {
		if (entity == null) {
			entity = new Customer();
		}
		return entity;
	}

	public CustomerBean() {
		customerDataModel = new CustomerDataModel(getAll());
	}

	public void setEntity(Customer entity) {
		this.entity = entity;
	}

	public CustomerFacade getCustomerFacade() {
		if (customerFacade == null) {
			customerFacade = new CustomerFacade();
		}
		return customerFacade;
	}

	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public Customer getEntitySelected() {
		if (entitySelected == null) {
			entitySelected = new Customer();
		}
		return entitySelected;
	}

	public void setEntitySelected(Customer entitySelected) {
		this.entitySelected = entitySelected;
	}

	public CustomerDataModel getCustomerDataModel() {
		return customerDataModel;
	}

	public void setCustomerDataModel(CustomerDataModel customerDataModel) {
		this.customerDataModel = customerDataModel;
	}

	public String save() {
		if (city != null) {
			entity.setCity(city);
		}
		if (CustomerValidator.validate(entity)) {
			if(!validateLogin(entity)){
				getCustomerFacade().save(entity);
				entity = new Customer();
	
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente", "Salvo com Sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, message);
				city = null;
				state = null;
			}else{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente", "Login J\u00E1 Existente!");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
		return "customers";

	}

	public String remove() {
		getCustomerFacade().remove(entitySelected);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);

		return "customers";

	}

	public Customer retrieve(Long id) {
		return getCustomerFacade().retrieve(id);
	}

	public void update() {
		if (city != null) {
			entitySelected.setCity(city);
		}
		if (CustomerValidator.validate(entitySelected)) {
			if(!validateLogin(entitySelected)){
				getCustomerFacade().update(entitySelected);
				entitySelected = new Customer();
	
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente", "Atualizado com Sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, message);
				city = null;
				state = null;
			}else{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente", "Login J� Existente!");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}

	}

	public List<Customer> getAll() {
		return getCustomerFacade().getAll();
	}

	public List<Customer> complete(String complete) {
		List<Customer> results = getCustomerFacade().retrieveByName(complete);
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
		}else{
			city=null;
			state=null;
		}
	}
	
	public String extractReport() throws IOException {
		Util.extractReport("RelatorioClientes-", getFilteredCustomers() == null ? getAll() : getFilteredCustomers(), "customerReport.jrxml");
		return "";
	}
	
	public void doLogin() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;

		if (this.entity.getUsername() != null && this.entity.getPassword() != null && getCustomerFacade().doLogin(this.entity.getUsername(), this.entity.getPassword()) != null) {
			loggedIn = true;
			context.addCallbackParam("loggedIn", loggedIn);
			try {
					Util.redirect("pages/homeCustomer.jsf");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			loggedIn = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Falhado", "Usu\u00E1rio e/ou senha inv\u00E1lidos!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void doLogoff() {
		getCustomerFacade().doLogoff();
		this.entity = new Customer();
		Util.redirect("/.." + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
	}
	
	public Customer getCurrentCustomer() {
		Customer currentLogged = getCustomerFacade().getCurrentLogged();
		return currentLogged;
	}
	
	public boolean validateLogin(Customer customer){
		return getCustomerFacade().isExistsLogin(customer);
		
	}
	
	public String extractInformation() throws IOException {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(getEntitySelected());
		Util.extractReport("Cliente-"+customers.get(0).getId()+"-", customers, "customerInformation.jrxml");
		return "";
	}
	
}

package com.tooling.project.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.tooling.project.datamodel.EmployeeDataModel;
import com.tooling.project.enums.ProfileEnum;
import com.tooling.project.facade.CityFacade;
import com.tooling.project.facade.CustomerFacade;
import com.tooling.project.facade.EmployeeFacade;
import com.tooling.project.facade.ThemeFacade;
import com.tooling.project.model.City;
import com.tooling.project.model.Employee;
import com.tooling.project.model.State;
import com.tooling.project.model.Theme;
import com.tooling.project.util.Util;
import com.tooling.project.validators.EmployeeValidator;

@ManagedBean
@ViewScoped
public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 2464418641496983053L;
	private EmployeeFacade employeeFacade;
	private ThemeFacade themeFacade;
	private Employee entity;
	private Employee entitySelected;
	private EmployeeDataModel employeeDataModel;
	private EmployeeDataModel buyerDataModel;
	private EmployeeDataModel sellerDataModel;
	private EmployeeDataModel operatorDataModel;
	private SelectItem[] profilesOptions;
	private SelectItem[] sexOptions;
	private SelectItem[] civilStatusOptions;
	private final static String[] profilesEnum;
	private final static String[] profiles;
	private final static String[] sexEnum;
	private final static String[] sexes;
	private final static String[] civilStatusEnum;
	private final static String[] civilStatus;
	private List<Employee> filteredEmployees;
	private State state;
	private City city;
	private CityFacade cityFacade;
	private List<City> cities;
	private CustomerFacade customerFacade;

	public CustomerFacade getCustomerFacade() {
		if (customerFacade == null) {
			customerFacade = new CustomerFacade();
		}
		return customerFacade;
	}

	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

	public ThemeFacade getThemeFacade() {
		if (themeFacade == null) {
			themeFacade = new ThemeFacade();
		}
		return themeFacade;
	}

	public void setThemeFacade(ThemeFacade themeFacade) {
		this.themeFacade = themeFacade;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
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

	public List<Employee> getFilteredEmployees() {
		return filteredEmployees;
	}

	public void setFilteredEmployees(List<Employee> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	static {

		profilesEnum = new String[6];
		profilesEnum[0] = "ADMINISTRATOR";
		profilesEnum[1] = "FINANCIAL";
		profilesEnum[2] = "MANAGER_OS";
		profilesEnum[3] = "BUYER";
		profilesEnum[4] = "SELLER";
		profilesEnum[5] = "OPERATOR";

		profiles = new String[6];
		profiles[0] = "Administrador";
		profiles[1] = "Financeiro";
		profiles[2] = "Processista";
		profiles[3] = "Comprador";
		profiles[4] = "Vendedor";
		profiles[5] = "Oper\u00E1rio";

		sexEnum = new String[2];
		sexEnum[0] = "MALE";
		sexEnum[1] = "FEMALE";

		sexes = new String[2];
		sexes[0] = "Masculino";
		sexes[1] = "Feminino";

		civilStatusEnum = new String[4];
		civilStatusEnum[0] = "SINGLE";
		civilStatusEnum[1] = "MARRIED";
		civilStatusEnum[2] = "DIVORCED";
		civilStatusEnum[3] = "WIDOWER";

		civilStatus = new String[4];
		civilStatus[0] = "Solteiro";
		civilStatus[1] = "Casado";
		civilStatus[2] = "Divorciado";
		civilStatus[3] = "Vi\u00FAvo";

	}

	public Employee getEntity() {
		if (entity == null) {
			entity = new Employee();
		}
		return entity;
	}

	public EmployeeBean() {
		if (entity == null) {
			entity = new Employee();
		}
		employeeDataModel = new EmployeeDataModel(getAll());
		profilesOptions = createFilterOptions(profilesEnum, profiles);
		sexOptions = createFilterOptions(sexEnum, sexes);
		civilStatusOptions = createFilterOptions(civilStatusEnum, civilStatus);
	}

	public EmployeeDataModel getBuyerDataModel() {
		if (buyerDataModel == null) {
			buyerDataModel = new EmployeeDataModel(getEmployeeFacade().retrieveByProfile(ProfileEnum.BUYER));
		}
		return buyerDataModel;
	}

	public EmployeeDataModel getOperatorDataModel() {
		if (operatorDataModel == null) {
			operatorDataModel = new EmployeeDataModel(getEmployeeFacade().retrieveByProfile(ProfileEnum.OPERATOR));
		}
		return operatorDataModel;
	}

	public EmployeeDataModel getSellerDataModel() {
		if (sellerDataModel == null) {
			sellerDataModel = new EmployeeDataModel(getEmployeeFacade().retrieveByProfile(ProfileEnum.SELLER));
		}
		return sellerDataModel;

	}

	public void setEntity(Employee entity) {
		this.entity = entity;
	}

	public EmployeeFacade getEmployeeFacade() {
		if (employeeFacade == null) {
			employeeFacade = new EmployeeFacade();
		}
		return employeeFacade;
	}

	public void setEmployeeFacade(EmployeeFacade employeeFacade) {
		this.employeeFacade = employeeFacade;
	}

	public Employee getEntitySelected() {
		if (entitySelected == null) {
			entitySelected = new Employee();
		}
		return entitySelected;
	}

	public void setEntitySelected(Employee entitySelected) {
		this.entitySelected = entitySelected;
	}

	public EmployeeDataModel getEmployeeDataModel() {
		return employeeDataModel;
	}

	public void setEmployeeDataModel(EmployeeDataModel employeeDataModel) {
		this.employeeDataModel = employeeDataModel;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public String save() {
		if (city != null) {
			entity.setCity(city);
		}

		if (EmployeeValidator.validate(entity)) {
			if (!validateLogin(entity)) {
				getEmployeeFacade().save(entity);
				Theme theme = new Theme();
				theme.setEmployee(entity);
				theme.setTheme("bootstrap");
				getThemeFacade().save(theme);

				entity = new Employee();

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcion\u00E1rio", "Salvo com Sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, message);
				city = null;
				state = null;
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Funcion\u00E1rio", "Login Já Existente!");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
		return "employees";
	}

	public String remove() {

		try {
			Theme theme = getThemeFacade().retrieveByEmployee(entitySelected);
			getThemeFacade().remove(theme);
			getEmployeeFacade().remove(entitySelected);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcion\u00E1rio", "Removido com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ConstraintViolationException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "N\u00E3o foi poss\u00EDvel excluir!", "Registro sendo usado.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return "employees";

	}

	public Employee retrieve(Long id) {
		return getEmployeeFacade().retrieve(id);
	}

	public void update() {
		if (city != null) {
			entitySelected.setCity(city);
		}
		if (EmployeeValidator.validate(entitySelected)) {
			if (!validateLogin(entitySelected)) {
				getEmployeeFacade().update(entitySelected);
				entitySelected = new Employee();

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcion\u00E1rio", "Atualizado com Sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, message);
				city = null;
				state = null;
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Funcion\u00E1rio", "Login Já Existente!");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}

	}

	public List<Employee> getAll() {
		return getEmployeeFacade().getAll();
	}

	public void doLogin() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;

		if (this.entity.getUsername() != null && this.entity.getPassword() != null && getEmployeeFacade().doLogin(this.entity.getUsername(), this.entity.getPassword()) != null) {
			loggedIn = true;
			context.addCallbackParam("loggedIn", loggedIn);
			try {
				if (getCurrentEmployee().getProfile().equals(ProfileEnum.MANAGER_OS)) {
					Util.redirect("pages/admin/homeManagerOS.jsf");
				}
				else if (getCurrentEmployee().getProfile().equals(ProfileEnum.OPERATOR)) {
					Util.redirect("pages/admin/homeOperator.jsf");
				}
				else if (getCurrentEmployee().getProfile().equals(ProfileEnum.SELLER)) {
					Util.redirect("pages/admin/homeSeller.jsf");
				}
				else if (getCurrentEmployee().getProfile().equals(ProfileEnum.FINANCIAL)) {
					Util.redirect("pages/admin/homeFinancial.jsf");
				}
				else if (getCurrentEmployee().getProfile().equals(ProfileEnum.BUYER)) {
					Util.redirect("pages/admin/homeBuyer.jsf");
				} else {
					Util.redirect("pages/home.jsf");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			if (this.entity.getUsername() != null && this.entity.getPassword() != null && getCustomerFacade().doLogin(this.entity.getUsername(), this.entity.getPassword()) != null) {
				loggedIn = true;
				context.addCallbackParam("loggedIn", loggedIn);
				try {
					Util.redirect("pages/admin/homeCustomer.jsf");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				loggedIn = false;
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Falhado", "Usu\u00E1rio e/ou senha inv\u00E1lidos!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	}

	public void doLogoff() {
		getEmployeeFacade().doLogoff();
		getCustomerFacade().doLogoff();
		this.entity = new Employee();
		Util.redirect("/.." + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
	}

	public Employee getCurrentEmployee() {
		Employee currentLogged = getEmployeeFacade().getCurrentLogged();
		return currentLogged;
	}

	public List<Employee> complete(String complete) {
		List<Employee> results = getEmployeeFacade().retrieveByName(complete);
		return results;

	}

	private SelectItem[] createFilterOptions(String[] enums, String[] label) {
		SelectItem[] options = new SelectItem[enums.length + 1];

		options[0] = new SelectItem("", "Selecione");
		for (int i = 0; i < enums.length; i++) {
			options[i + 1] = new SelectItem(enums[i], label[i]);
		}

		return options;
	}

	public SelectItem[] getProfilesOptions() {
		return profilesOptions;
	}

	public void setProfilesOptions(SelectItem[] profilesOptions) {
		this.profilesOptions = profilesOptions;
	}

	public SelectItem[] getSexOptions() {
		return sexOptions;
	}

	public void setSexOptions(SelectItem[] sexOptions) {
		this.sexOptions = sexOptions;
	}

	public SelectItem[] getCivilStatusOptions() {
		return civilStatusOptions;
	}

	public void setCivilStatusOptions(SelectItem[] civilStatusOptions) {
		this.civilStatusOptions = civilStatusOptions;
	}

	public String extractReport() throws IOException {
		Util.extractReport("RelatorioFuncionario-", getFilteredEmployees() == null ? getAll() : getFilteredEmployees(), "employeeReport.jrxml");
		return "";
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

	public boolean validateLogin(Employee employee) {
		return getEmployeeFacade().isExistsLogin(employee);

	}
	
	public String extractInformation() throws IOException {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(getEntitySelected());
		Util.extractReport("Funcionario-"+employees.get(0).getId()+"-", employees, "employeeInformation.jrxml");
		return "";
	}
	
}

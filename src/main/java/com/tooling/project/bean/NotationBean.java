package com.tooling.project.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.chart.PieChartModel;

import com.tooling.project.datamodel.NotationDataModel;
import com.tooling.project.enums.PauseReasonNotationEnum;
import com.tooling.project.enums.ProfileEnum;
import com.tooling.project.facade.EmployeeFacade;
import com.tooling.project.facade.NotationFacade;
import com.tooling.project.facade.ServiceOrderFacade;
import com.tooling.project.model.Employee;
import com.tooling.project.model.Notation;
import com.tooling.project.model.ServiceOrder;
import com.tooling.project.util.Util;
import com.tooling.project.validators.NotationValidator;

@ManagedBean
@ViewScoped
public class NotationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Notation entity;
	private NotationFacade notationFacade;
	private PieChartModel pieModel;
	private NotationDataModel notationDataModel;
	private Notation entitySelected;
	private String employeeName;
	private Long serviceOrderNumber;
	private Employee entitySelectedSearch;
	private ServiceOrder entitySelectedSearch2;
	private ServiceOrderFacade serviceOrderFacade;
	private EmployeeFacade employeeFacade;
	private List<Notation> filteredNotations;
	private final static String[] reasonEnum;
	private final static String[] reasons;
	private SelectItem[] pauseReasonOptions;

	static {

		reasonEnum = new String[6];
		reasonEnum[0] = "LACK_OF_STUFF";
		reasonEnum[1] = "ENERGY_BLACKOUT";
		reasonEnum[2] = "MECHANICAL_ISSUE";
		reasonEnum[3] = "OTHERS_PRIORITIES";
		reasonEnum[4] = "MEAL";
		reasonEnum[5] = "REUNION";

		reasons = new String[6];
		reasons[0] = "Falta de Material";
		reasons[1] = "Queda de Energia";
		reasons[2] = "Problema Mec\u00E2nico";
		reasons[3] = "Outra Prioridade";
		reasons[4] = "Refei\u00E7\u00E3o";
		reasons[5] = "Reuni\u00E3o";
	}

	public SelectItem[] getPauseReasonOptions() {
		return pauseReasonOptions;
	}

	public void setPauseReasonOptions(SelectItem[] pauseReasonOptions) {
		this.pauseReasonOptions = pauseReasonOptions;
	}

	public List<Notation> getFilteredNotations() {
		return filteredNotations;
	}

	public void setFilteredNotations(List<Notation> filteredNotations) {
		this.filteredNotations = filteredNotations;
	}

	public ServiceOrderFacade getServiceOrderFacade() {
		if (serviceOrderFacade == null) {
			serviceOrderFacade = new ServiceOrderFacade();
		}
		return serviceOrderFacade;
	}

	public void setServiceOrderFacade(ServiceOrderFacade serviceOrderFacade) {
		this.serviceOrderFacade = serviceOrderFacade;
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

	public Employee getEntitySelectedSearch() {
		return entitySelectedSearch;
	}

	public void setEntitySelectedSearch(Employee entitySelectedSearch) {
		this.entitySelectedSearch = entitySelectedSearch;
	}

	public ServiceOrder getEntitySelectedSearch2() {
		return entitySelectedSearch2;
	}

	public void setEntitySelectedSearch2(ServiceOrder entitySelectedSearch2) {
		this.entitySelectedSearch2 = entitySelectedSearch2;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getServiceOrderNumber() {
		return serviceOrderNumber;
	}

	public void setServiceOrderNumber(Long serviceOrderNumber) {
		this.serviceOrderNumber = serviceOrderNumber;
	}

	public Notation getEntity() {
		if (entity == null) {
			entity = new Notation();
		}
		return entity;
	}

	public void setEntity(Notation entity) {
		this.entity = entity;
	}

	public NotationFacade getNotationFacade() {
		if (notationFacade == null) {
			notationFacade = new NotationFacade();
		}
		return notationFacade;
	}

	public void setNotationFacade(NotationFacade notationFacade) {
		this.notationFacade = notationFacade;
	}

	public String save() {
		if (getEmployeeFacade().getCurrentLogged().getProfile().equals(ProfileEnum.OPERATOR)) {
			entity.setEmployee(getEmployeeFacade().getCurrentLogged());
		} else {
			if (employeeName != null && employeeName != "") {
				Employee employee = new Employee();
				employee = getEmployeeFacade().retrieveByExactName(employeeName);
				entity.setEmployee(employee);
			}
		}
		if (serviceOrderNumber != null) {
			ServiceOrder serviceOrder = new ServiceOrder();
			serviceOrder = getServiceOrderFacade().retrieve(serviceOrderNumber);
			entity.setServiceOrder(serviceOrder);
		}
		if (NotationValidator.validate(entity, false)) {
			getNotationFacade().save(entity);
			entity = new Notation();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Apontamento", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "notations";

	}

	public String remove() {
		getNotationFacade().remove(entitySelected);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Apontamento", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);

		return "notations";

	}

	public Notation retrieve(Long id) {
		return getNotationFacade().retrieve(id);
	}

	public void update() throws ParseException {
		if (getEmployeeFacade().getCurrentLogged().getProfile().equals(ProfileEnum.OPERATOR)) {
			entitySelected.setEmployee(getEmployeeFacade().getCurrentLogged());
		} else {
			if (employeeName != null && employeeName != "") {
				Employee employee = new Employee();
				employee = getEmployeeFacade().retrieveByExactName(employeeName);
				entitySelected.setEmployee(employee);
			}
		}

		if (serviceOrderNumber != null) {
			ServiceOrder serviceOrder = new ServiceOrder();
			serviceOrder = getServiceOrderFacade().retrieve(serviceOrderNumber);
			entitySelected.setServiceOrder(serviceOrder);
		}
		if (NotationValidator.validate(entitySelected, true)) {
			getNotationFacade().update(entitySelected);
			entitySelected = new Notation();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Apontamento", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public List<Notation> getAll() {
		return getNotationFacade().getAll();
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public NotationBean() {
		createPieModel();
		if (getEmployeeFacade().getCurrentLogged().getProfile().equals(ProfileEnum.OPERATOR)) {
			notationDataModel = new NotationDataModel(getMyAllNotations());
		} else {
			notationDataModel = new NotationDataModel(getAll());
		}

		pauseReasonOptions = createFilterOptions(reasonEnum, reasons);
	}

	private void createPieModel() {
		pieModel = new PieChartModel();

		pieModel.set("Falta de Material", countBySituation(PauseReasonNotationEnum.LACK_OF_STUFF));
		pieModel.set("Queda de Energia", countBySituation(PauseReasonNotationEnum.ENERGY_BLACKOUT));
		pieModel.set("Problema Mec\u00E2nico", countBySituation(PauseReasonNotationEnum.MECHANICAL_ISSUE));
		pieModel.set("Outra Prioridade", countBySituation(PauseReasonNotationEnum.OTHERS_PRIORITIES));
		pieModel.set("Refei\u00E7\u00E3o", countBySituation(PauseReasonNotationEnum.MEAL));
		pieModel.set("Reuni\u00E3o", countBySituation(PauseReasonNotationEnum.REUNION));
	}

	public int countBySituation(PauseReasonNotationEnum situation) {
		return getNotationFacade().countBySituation(situation);
	}

	public NotationDataModel getNotationDataModel() {
		return notationDataModel;
	}

	public void setNotationDataModel(NotationDataModel notationDataModel) {
		this.notationDataModel = notationDataModel;
	}

	public Notation getEntitySelected() {
		if (entitySelected == null) {
			entitySelected = new Notation();
		}
		return entitySelected;
	}

	public void setEntitySelected(Notation entitySelected) {
		this.entitySelected = entitySelected;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public void edit() {
		employeeName = getEntitySelected().getEmployee().getName();
		serviceOrderNumber = getEntitySelected().getServiceOrder().getId();
	}

	public void applyEmployee() {
		employeeName = getEntitySelectedSearch().getName();
	}

	public void applyServiceOrder() {
		serviceOrderNumber = getEntitySelectedSearch2().getId();
	}

	public List<Notation> getMyAllNotations() {
		Employee employee = getEmployeeFacade().getCurrentLogged();
		return getNotationFacade().retrieveByEmployee(employee);

	}

	public String extractReport() throws IOException {
		Util.extractReport("RelatorioApontamentos-", getFilteredNotations() == null ? getEmployeeFacade().getCurrentLogged().getProfile().equals(ProfileEnum.OPERATOR) ? getMyAllNotations() : getAll()
				: getFilteredNotations(), "notationReport.jrxml");
		return "";
	}

	private SelectItem[] createFilterOptions(String[] reasonEnum, String[] reasons) {
		SelectItem[] options = new SelectItem[reasonEnum.length + 1];

		options[0] = new SelectItem("", "Selecione");
		for (int i = 0; i < reasonEnum.length; i++) {
			options[i + 1] = new SelectItem(reasonEnum[i], reasons[i]);
		}

		return options;

	}

	public String getTotalTime() throws ParseException {
		if (entitySelected.getId() != null) {
			if(entitySelected.getEndDate() == null){
				return "N\u00E3o h\u00E1 data final, provavelmente a Ordem de Servi\u00E7o est\u00E1 sendo executada.";
				
			}
			return Util.calculateDiferenceBetweenTwoDates(entitySelected.getStartDate().toString(), entitySelected.getEndDate().toString());
			//return Util.calculateDiferenceBetweenTwoDates(entitySelected.getStartDate().toString(), entitySelected.getEndDate().toString());
		}
		return "";
	}
	
	public String extractInformation() throws IOException {
		List<Notation> notations = new ArrayList<Notation>();
		notations.add(getEntitySelected());
		Util.extractReport("Apontamento-"+notations.get(0).getId()+"-", notations, "notationInformation.jrxml");
		return "";
	}

}

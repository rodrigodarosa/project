package com.tooling.project.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.PieChartModel;

import com.tooling.project.datamodel.ServiceOrderDataModel;
import com.tooling.project.enums.MachineSituationEnum;
import com.tooling.project.enums.ManufacturedProductSituationEnum;
import com.tooling.project.enums.PauseReasonNotationEnum;
import com.tooling.project.enums.ServiceOrderSituationEnum;
import com.tooling.project.facade.EmployeeFacade;
import com.tooling.project.facade.MachineFacade;
import com.tooling.project.facade.ManufacturedProductFacade;
import com.tooling.project.facade.NotationFacade;
import com.tooling.project.facade.ServiceOrderFacade;
import com.tooling.project.model.Employee;
import com.tooling.project.model.Machine;
import com.tooling.project.model.ManufacturedProduct;
import com.tooling.project.model.Notation;
import com.tooling.project.model.ServiceOrder;
import com.tooling.project.util.Util;
import com.tooling.project.validators.ServiceOrderValidator;

@ManagedBean
@ViewScoped
public class ServiceOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ServiceOrder entity;
	private ServiceOrderFacade serviceOrderFacade;
	private PieChartModel pieModel;
	private ServiceOrder entitySelected;
	private Employee entitySelectedSearch;
	private Machine entitySelectedSearch2;
	private ManufacturedProduct entitySelectedSearch3;
	private ServiceOrderDataModel serviceOrderDataModel;
	private String employeeName;
	private String machineName;
	private String manufacturedProductName;
	private EmployeeFacade employeeFacade;
	private MachineFacade machineFacade;
	private NotationFacade notationFacade;
	private SelectItem[] serviceOrderSituationOptions;
	private final static String[] situationsEnum;
	private final static String[] situations;
	private String observationNotation;
	private Integer walkThrough;
	private List<ServiceOrder>  filteredServiceOrders;
	private PauseReasonNotationEnum pauseReason;
	private ManufacturedProductFacade manufacturedProductFacade;
	private StreamedContent file;
	private final static String DIRECTORY="C:/tooling/serviceOrders/";
	
	
	public ManufacturedProduct getEntitySelectedSearch3() {
		if(entitySelectedSearch3 == null){
			entitySelectedSearch3 = new ManufacturedProduct();
		}
		return entitySelectedSearch3;
	}

	public void setEntitySelectedSearch3(ManufacturedProduct entitySelectedSearch3) {
		this.entitySelectedSearch3 = entitySelectedSearch3;
	}

	public ManufacturedProductFacade getManufacturedProductFacade() {
		if(manufacturedProductFacade == null){
			manufacturedProductFacade = new ManufacturedProductFacade();
		}
		return manufacturedProductFacade;
	}

	public void setManufacturedProductFacade(ManufacturedProductFacade manufacturedProductFacade) {
		this.manufacturedProductFacade = manufacturedProductFacade;
	}

	public PauseReasonNotationEnum getPauseReason() {
		return pauseReason;
	}

	public void setPauseReason(PauseReasonNotationEnum pauseReason) {
		this.pauseReason = pauseReason;
	}

	public List<ServiceOrder> getFilteredServiceOrders() {
		return filteredServiceOrders;
	}

	public void setFilteredServiceOrders(List<ServiceOrder> filteredServiceOrders) {
		this.filteredServiceOrders = filteredServiceOrders;
	}

	static {

		situationsEnum = new String[4];
		situationsEnum[0] = "ASSEMBLING";
		situationsEnum[1] = "NOT_INITIATED";
		situationsEnum[2] = "FINALIZED";
		situationsEnum[3] = "STAND_BY";

		situations = new String[4];
		situations[0] = "Em Execu\u00E7\u00E3o";
		situations[1] = "N\u00E3o Inicializado";
		situations[2] = "Finalizado";
		situations[3] = "Em Pausa";
		

	}

	public Integer getWalkThrough() {
		if (walkThrough == null) {
			walkThrough = 0;
		}
		return walkThrough;
	}

	public void setWalkThrough(Integer walkThrough) {
		this.walkThrough = walkThrough;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
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

	public MachineFacade getMachineFacade() {
		if (machineFacade == null) {
			machineFacade = new MachineFacade();
		}
		return machineFacade;
	}

	public void setMachineFacade(MachineFacade machineFacade) {
		this.machineFacade = machineFacade;
	}

	public ServiceOrder getEntity() {
		if (entity == null) {
			entity = new ServiceOrder();
		}
		return entity;
	}

	public void setEntity(ServiceOrder entity) {
		this.entity = entity;
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

	public ServiceOrder getEntitySelected() {
		return entitySelected;
	}

	public void setEntitySelected(ServiceOrder entitySelected) {
		this.entitySelected = entitySelected;
	}

	public ServiceOrderDataModel getServiceOrderDataModel() {
		return serviceOrderDataModel;
	}

	public void setServiceOrderDataModel(ServiceOrderDataModel serviceOrderDataModel) {
		this.serviceOrderDataModel = serviceOrderDataModel;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}
	
	public String getManufacturedProductName() {
		return manufacturedProductName;
	}

	public void setManufacturedProductName(String manufacturedProductName) {
		this.manufacturedProductName = manufacturedProductName;
	}

	public String save() {
		if(entity.getWalkThrough() ==null){
			entity.setWalkThrough(0);
		}
		if (employeeName != null && employeeName != "") {
			Employee employee = new Employee();
			employee = getEmployeeFacade().retrieveByExactName(employeeName);
			entity.setEmployee(employee);
		}
		if (machineName != null && machineName != "") {
			Machine machine = new Machine();
			machine = getMachineFacade().retrieveByExactName(machineName);
			entity.setMachine(machine);
		}
		if (manufacturedProductName != null && manufacturedProductName != "") {
			ManufacturedProduct manufacturedProduct = new ManufacturedProduct();
		    manufacturedProduct = getManufacturedProductFacade().retrieveByExactName(manufacturedProductName);
			entity.setManufacturedProduct(manufacturedProduct);
		}

		if (ServiceOrderValidator.validate(entity)) {
			getServiceOrderFacade().save(entity);
			entity = new ServiceOrder();
			employeeName = "";
			machineName = "";

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ordem de Servi\u00E7o", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "serviceOrders";

	}

	public String remove() {
		getServiceOrderFacade().remove(entitySelected);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ordem de Servi\u00E7o", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);

		return "serviceOrders";
	}

	public ServiceOrder retrieve(Long id) {
		return getServiceOrderFacade().retrieve(id);
	}

	public void update() {
		if (employeeName != null && employeeName != "") {
			Employee employee = new Employee();
			employee = getEmployeeFacade().retrieveByExactName(employeeName);
			entitySelected.setEmployee(employee);
		}

		if (machineName != null && machineName != "") {
			Machine machine = new Machine();
			machine = getMachineFacade().retrieveByExactName(machineName);
			entitySelected.setMachine(machine);
		}
		
		if (manufacturedProductName != null && manufacturedProductName != "") {
			ManufacturedProduct manufacturedProduct = new ManufacturedProduct();
		    manufacturedProduct = getManufacturedProductFacade().retrieveByExactName(manufacturedProductName);
		    entitySelected.setManufacturedProduct(manufacturedProduct);
		}
		
		if (ServiceOrderValidator.validate(entitySelected)) {
			getServiceOrderFacade().save(entitySelected);
			entitySelected = new ServiceOrder();
			employeeName = "";
			machineName = "";

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ordem de Servi\u00E7o", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public List<ServiceOrder> getAll() {
		return getServiceOrderFacade().getAll();
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public ServiceOrderBean() {
		createPieModel();
		serviceOrderSituationOptions = createFilterOptions(situationsEnum, situations);
		serviceOrderDataModel = new ServiceOrderDataModel(getAll());
	}

	private void createPieModel() {
		pieModel = new PieChartModel();

		pieModel.set("Em Execu\u00E7\u00E3o", countBySituation(ServiceOrderSituationEnum.ASSEMBLING));
		pieModel.set("Pausados", countBySituation(ServiceOrderSituationEnum.STAND_BY));
		pieModel.set("N\u00E3o Iniciados", countBySituation(ServiceOrderSituationEnum.NOT_INITIATED));
		pieModel.set("Conclu\u00EDdos", countBySituation(ServiceOrderSituationEnum.FINALIZED));
	}

	public int countBySituation(ServiceOrderSituationEnum situation) {
		return getServiceOrderFacade().getCountBySituation(situation);
	}

	public void edit() {
		employeeName = getEntitySelected().getEmployee().getName();
		machineName = getEntitySelected().getMachine().getName();
		manufacturedProductName = getEntitySelected().getManufacturedProduct().getName();
	}

	public Employee getEntitySelectedSearch() {
		if (entitySelectedSearch == null) {
			entitySelectedSearch = new Employee();
		}
		return entitySelectedSearch;
	}

	public void setEntitySelectedSearch(Employee entitySelectedSearch) {
		this.entitySelectedSearch = entitySelectedSearch;
	}

	public Machine getEntitySelectedSearch2() {
		return entitySelectedSearch2;
	}

	public void setEntitySelectedSearch2(Machine entitySelectedSearch2) {
		this.entitySelectedSearch2 = entitySelectedSearch2;
	}

	public void applyEmployee() {
		employeeName = getEntitySelectedSearch().getName();
	}

	public void applyMachine() {
		machineName = getEntitySelectedSearch2().getName();
	}
	public void applyManufacturedProduct() {
		manufacturedProductName = getEntitySelectedSearch3().getName();
	}

	private SelectItem[] createFilterOptions(String[] situationsEnum, String[] situations) {
		SelectItem[] options = new SelectItem[situationsEnum.length + 1];

		options[0] = new SelectItem("", "Selecione");
		for (int i = 0; i < situationsEnum.length; i++) {
			options[i + 1] = new SelectItem(situationsEnum[i], situations[i]);
		}

		return options;
	}

	public SelectItem[] getServiceOrderSituationOptions() {
		return serviceOrderSituationOptions;
	}

	public void setServiceOrderSituationOptions(SelectItem[] serviceOrderSituationOptions) {
		this.serviceOrderSituationOptions = serviceOrderSituationOptions;
	}

	public String getObservationNotation() {
		return observationNotation;
	}

	public void setObservationNotation(String observationNotation) {
		this.observationNotation = observationNotation;
	}

	public ServiceOrder getActuallyExecuting() {
		ServiceOrder serviceOrder = getServiceOrderFacade().getActuallyExecuting(getEmployeeFacade().getCurrentLogged());
		return serviceOrder;
	}

	public List<ServiceOrder> getPendings() {
		List<ServiceOrder> serviceOrders = getServiceOrderFacade().getServiceOrderBySituation(getEmployeeFacade().getCurrentLogged(), ServiceOrderSituationEnum.NOT_INITIATED);
		return serviceOrders;
	}

	public List<ServiceOrder> getStandBy() {
		List<ServiceOrder> serviceOrders = getServiceOrderFacade().getServiceOrderBySituation(getEmployeeFacade().getCurrentLogged(), ServiceOrderSituationEnum.STAND_BY);
		return serviceOrders;
	}

	public List<ServiceOrder> getCompletes() {
		List<ServiceOrder> serviceOrders = getServiceOrderFacade().getServiceOrderBySituation(getEmployeeFacade().getCurrentLogged(), ServiceOrderSituationEnum.FINALIZED);
		return serviceOrders;
	}

	public List<ServiceOrder> getAllByEmployee() {
		List<ServiceOrder> serviceOrders = getServiceOrderFacade().getAllByEmployee(getEmployeeFacade().getCurrentLogged());
		return serviceOrders;
	}

	public void pauseServiceOrder() {
		ServiceOrder serviceOrder = getActuallyExecuting();
		if(serviceOrder != null){
			serviceOrder.setSituation(ServiceOrderSituationEnum.STAND_BY);
			serviceOrder.setWalkThrough(this.walkThrough == null ? serviceOrder.getWalkThrough() : this.walkThrough);
			getServiceOrderFacade().update(serviceOrder);
			
			Machine machine = getMachineFacade().retrieve(serviceOrder.getMachine().getId());
			machine.setSituation(MachineSituationEnum.AVAILABLE);
			getMachineFacade().update(machine);
			
			ManufacturedProduct manufacturedProduct = getManufacturedProductFacade().retrieve(serviceOrder.getManufacturedProduct().getId());
			manufacturedProduct.setSituation(ManufacturedProductSituationEnum.STAND_BY);
			getManufacturedProductFacade().update(manufacturedProduct);
	
			Notation notation = getNotationFacade().retrieveLastNotationByEmployeeAndServiceOrder(getEmployeeFacade().getCurrentLogged(), serviceOrder);
			notation.setObservation(this.observationNotation);
			notation.setEndDate(new Date());
			notation.setPauseReason(pauseReason);
			getNotationFacade().update(notation);
		}
		this.observationNotation = "";
		this.walkThrough = null;
		this.pauseReason = null;
	}

	public void startOrResumeServiceOrder() {
		ServiceOrder serviceOrder = getEntitySelected();
		ServiceOrder actually = getActuallyExecuting();
		if (actually != null) {
			pauseServiceOrder();
		}
		serviceOrder.setSituation(ServiceOrderSituationEnum.ASSEMBLING);
		getServiceOrderFacade().update(serviceOrder);
		
		Machine machine = getMachineFacade().retrieve(serviceOrder.getMachine().getId());
		machine.setSituation(MachineSituationEnum.UNAVAILABLE);
		getMachineFacade().update(machine);
		
		ManufacturedProduct manufacturedProduct = getManufacturedProductFacade().retrieve(serviceOrder.getManufacturedProduct().getId());
		manufacturedProduct.setSituation(ManufacturedProductSituationEnum.ASSEMBLING);
		getManufacturedProductFacade().update(manufacturedProduct);

		Notation notation = new Notation();
		notation.setServiceOrder(serviceOrder);
		notation.setStartDate(new Date());
		notation.setEmployee(getEmployeeFacade().getCurrentLogged());
		getNotationFacade().save(notation);

	}

	public void finishServiceOrder() {
		ServiceOrder serviceOrder = getActuallyExecuting();
		serviceOrder.setSituation(ServiceOrderSituationEnum.FINALIZED);
		serviceOrder.setWalkThrough(100);
		getServiceOrderFacade().update(serviceOrder);
		
		Machine machine = getMachineFacade().retrieve(serviceOrder.getMachine().getId());
		machine.setSituation(MachineSituationEnum.AVAILABLE);
		getMachineFacade().update(machine);
		
		ManufacturedProduct manufacturedProduct = getManufacturedProductFacade().retrieve(serviceOrder.getManufacturedProduct().getId());
		manufacturedProduct.setSituation(ManufacturedProductSituationEnum.FINALIZED);
		getManufacturedProductFacade().update(manufacturedProduct);

		Notation notation = getNotationFacade().retrieveLastNotationByEmployeeAndServiceOrder(getEmployeeFacade().getCurrentLogged(), serviceOrder);
		notation.setObservation(this.observationNotation);
		notation.setEndDate(new Date());
		getNotationFacade().update(notation);
		this.observationNotation = "";
	}
	
	public String extractReport() throws IOException {
		Util.extractReport("RelatorioOrdensDeServico-", getFilteredServiceOrders() == null ? getAll() : getFilteredServiceOrders(), "serviceOrderReport.jrxml");
		return "";
	}
	
	public String extractReportAllEmployee() throws IOException {
		Util.extractReport("RelatorioOrdensDeServico-", getAllByEmployee(), "serviceOrderReport.jrxml");
		return "";
	}
	
	public void upload(FileUploadEvent event) {
		try {
			File targetFolder = new File(DIRECTORY);
			if(!targetFolder.exists()){
				targetFolder.mkdirs();
			}
			Date date = new Date();
			SimpleDateFormat sdf =  new SimpleDateFormat("-(dd-MM-yyyy-hh-mm-ss)");
			InputStream inputStream = event.getFile().getInputstream();
			String file[] = new String[2];
			file = event.getFile().getFileName().split("\\.");
			
			OutputStream out = new FileOutputStream(new File(targetFolder, file[0] + sdf.format(date) + "."+file[1]));

			if (entitySelected != null && entitySelected.getId() != null) {
				this.entitySelected.setFileName( file[0] + sdf.format(date) + "."+file[1]);
			} 
			if (entity != null && entity.getId() == null) {
				this.entity.setFileName( file[0] + sdf.format(date) + "."+file[1]);
			}
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


    public StreamedContent getFile() throws FileNotFoundException {
    	InputStream stream = new FileInputStream(DIRECTORY+this.entitySelected.getFileName());
    	file = new DefaultStreamedContent(stream, null, this.entitySelected.getFileName());
    	
        return file;
    }  
    
	public String extractReportNotationsByServiceOrder() throws IOException {
		List<Notation> notations = notationsByServiceOrder();
		if(!notations.isEmpty()){
			Util.extractReport("RelatorioPorOrdemDeServico-", notationsByServiceOrder(), "notationReport.jrxml");
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ordem de Servi\u00E7o", "N\u00E3o h\u00E1 apontamentos nesta Ordem de Servi\u00E7o");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "";
	}
	
	public List<Notation> notationsByServiceOrder(){
		List<Notation> notations= getNotationFacade().retrieveByServiceOrder(getEntitySelected());
		return notations;
	}
	
	public String extractInformation() throws IOException {
		List<ServiceOrder> serviceOrders = new ArrayList<ServiceOrder>();
		serviceOrders.add(getEntitySelected());
		Util.extractReport("OrdemServico-"+serviceOrders.get(0).getId()+"-",serviceOrders, "serviceOrderInformation.jrxml");
		return "";
	}

}

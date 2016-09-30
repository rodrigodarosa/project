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

import com.tooling.project.datamodel.InputBudgetDataModel;
import com.tooling.project.enums.InputBudgetSituationEnum;
import com.tooling.project.facade.CustomerFacade;
import com.tooling.project.facade.InputBudgetFacade;
import com.tooling.project.model.Customer;
import com.tooling.project.model.InputBudget;
import com.tooling.project.util.Util;
import com.tooling.project.validators.InputBudgetValidator;

@ManagedBean
@ViewScoped
public class InputBudgetBean implements Serializable {

	private static final long serialVersionUID = 2464418641496983053L;
	private InputBudgetFacade inputBudgetFacade;
	private InputBudget entity;
	private PieChartModel pieModel;
	private InputBudget entitySelected;
	private Customer entitySelectedSearch;
	private InputBudgetDataModel inputBudgetDataModel;
	private InputBudgetDataModel inputBudgetPendingDataModel;
	private InputBudgetDataModel inputBudgetNotAcceptedDataModel;
	private InputBudgetDataModel inputBudgetAcceptedDataModel;
	private String customerName;
	private CustomerFacade customerFacade;
	private List<InputBudget> filteredInputBudgets;
	private final static String[] situationsEnum;
	private final static String[] situations;
	private SelectItem[] inputBudgetSituationOptions;
	private StreamedContent file;
	private final static String DIRECTORY="C:/tooling/inputBudgets/";
     
	static {

		situationsEnum = new String[3];
		situationsEnum[0] = "PENDING";
		situationsEnum[1] = "ACCEPTED";
		situationsEnum[2] = "NOT_ACCEPTED";

		situations = new String[3];
		situations[0] = "Pendente";
		situations[1] = "Deferido";
		situations[2] = "Indeferido";
	}

	public InputBudgetDataModel getInputBudgetPendingDataModel() {
		return inputBudgetPendingDataModel;
	}

	public void setInputBudgetPendingDataModel(InputBudgetDataModel inputBudgetPendingDataModel) {
		this.inputBudgetPendingDataModel = inputBudgetPendingDataModel;
	}

	public InputBudgetDataModel getInputBudgetNotAcceptedDataModel() {
		return inputBudgetNotAcceptedDataModel;
	}

	public void setInputBudgetNotAcceptedDataModel(InputBudgetDataModel inputBudgetNotAcceptedDataModel) {
		this.inputBudgetNotAcceptedDataModel = inputBudgetNotAcceptedDataModel;
	}

	public InputBudgetDataModel getInputBudgetAcceptedDataModel() {
		return inputBudgetAcceptedDataModel;
	}

	public void setInputBudgetAcceptedDataModel(InputBudgetDataModel inputBudgetAcceptedDataModel) {
		this.inputBudgetAcceptedDataModel = inputBudgetAcceptedDataModel;
	}

	public SelectItem[] getInputBudgetSituationOptions() {
		return inputBudgetSituationOptions;
	}

	public void setInputBudgetSituationOptions(SelectItem[] inputBudgetSituationOptions) {
		this.inputBudgetSituationOptions = inputBudgetSituationOptions;
	}

	public List<InputBudget> getFilteredInputBudgets() {
		return filteredInputBudgets;
	}

	public void setFilteredInputBudgets(List<InputBudget> filteredInputBudgets) {
		this.filteredInputBudgets = filteredInputBudgets;
	}

	public InputBudget getEntity() {
		if (entity == null) {
			entity = new InputBudget();
		}
		return entity;
	}

	public void setEntity(InputBudget entity) {
		this.entity = entity;
	}

	public InputBudgetFacade getInputBudgetFacade() {
		if (inputBudgetFacade == null) {
			inputBudgetFacade = new InputBudgetFacade();
		}
		return inputBudgetFacade;
	}

	public void setInputBudgetFacade(InputBudgetFacade inputBudgetFacade) {
		this.inputBudgetFacade = inputBudgetFacade;
	}

	public InputBudget getEntitySelected() {
		return entitySelected;
	}

	public void setEntitySelected(InputBudget entitySelected) {
		this.entitySelected = entitySelected;
	}

	public InputBudgetDataModel getInputBudgetDataModel() {
		return inputBudgetDataModel;
	}

	public void setInputBudgetDataModel(InputBudgetDataModel inputBudgetDataModel) {
		this.inputBudgetDataModel = inputBudgetDataModel;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public String save() {
		Customer logged = getCustomerFacade().getCurrentLogged();
		if (customerName != null && customerName != "") {
			Customer customer = new Customer();
			customer = getCustomerFacade().retrieveByExactName(customerName);
			entity.setCustomer(customer);
		}else{
			if(logged != null){
				entity.setCustomer(logged);
			}
		}

		if (entity.getSituation() == null) {
			entity.setSituation(InputBudgetSituationEnum.PENDING);
		}

		if (InputBudgetValidator.validate(entity)) {
			getInputBudgetFacade().save(entity);
			entity = new InputBudget();
			customerName = "";

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Or\u00E7amento de Entrada", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		if(logged != null){
			return "homeCustomer";
		}

		return "inputBudgets";
	}

	public String remove() {
		getInputBudgetFacade().remove(entitySelected);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Or\u00E7amento de Entrada", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);

		return "inputBudgets";

	}

	public InputBudget retrieve(Long id) {
		return getInputBudgetFacade().retrieve(id);
	}

	public void update() {
		if (customerName != null && customerName != "") {
			Customer customer = new Customer();
			customer = getCustomerFacade().retrieveByExactName(customerName);
			entitySelected.setCustomer(customer);
		}
		if (InputBudgetValidator.validate(entitySelected)) {
			getInputBudgetFacade().update(entitySelected);
			entitySelected = new InputBudget();
			customerName = "";

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Or\u00E7amento de Entrada", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public List<InputBudget> getAll() {
		return getInputBudgetFacade().getAll();
	}


	public PieChartModel getPieModel() {
		return pieModel;
	}

	public InputBudgetBean() {
		createPieModel();
		inputBudgetDataModel = new InputBudgetDataModel(getAll());

		Customer logged = getCustomerFacade().getCurrentLogged();
		inputBudgetNotAcceptedDataModel = new InputBudgetDataModel(retrieveBySituationAndCustomer(InputBudgetSituationEnum.NOT_ACCEPTED, logged));
		inputBudgetAcceptedDataModel = new InputBudgetDataModel(retrieveBySituationAndCustomer(InputBudgetSituationEnum.ACCEPTED, logged));
		inputBudgetPendingDataModel = new InputBudgetDataModel(retrieveBySituationAndCustomer(InputBudgetSituationEnum.PENDING, logged));

		inputBudgetSituationOptions = createFilterOptions(situationsEnum, situations);
	}

	private void createPieModel() {
		pieModel = new PieChartModel();
		pieModel.set("Deferidos", getInputBudgetFacade().getCountBySituation(InputBudgetSituationEnum.ACCEPTED));
		pieModel.set("Indeferidos", getInputBudgetFacade().getCountBySituation(InputBudgetSituationEnum.NOT_ACCEPTED));
		pieModel.set("Pendentes", getInputBudgetFacade().getCountBySituation(InputBudgetSituationEnum.PENDING));
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public void edit() {
		customerName = getEntitySelected().getCustomer().getName();
	}

	public Customer getEntitySelectedSearch() {
		if (entitySelectedSearch == null) {
			entitySelectedSearch = new Customer();
		}
		return entitySelectedSearch;
	}

	public void setEntitySelectedSearch(Customer entitySelectedSearch) {
		this.entitySelectedSearch = entitySelectedSearch;
	}

	public void applyCustomer() {
		customerName = getEntitySelectedSearch().getName();
	}

	public String extractReport() throws IOException {
		Customer customer = getCustomerFacade().getCurrentLogged();
		if (customer != null) {
			List<InputBudget> allMine = new ArrayList<InputBudget>();
			allMine.addAll(retrieveBySituationAndCustomer(InputBudgetSituationEnum.PENDING, customer));
			allMine.addAll(retrieveBySituationAndCustomer(InputBudgetSituationEnum.ACCEPTED, customer));
			allMine.addAll(retrieveBySituationAndCustomer(InputBudgetSituationEnum.NOT_ACCEPTED, customer));
			Util.extractReport("RelatorioOrcamentoEntrada-", allMine, "inputBudgetReport.jrxml");
		}else{
			Util.extractReport("RelatorioOrcamentoEntrada-", getFilteredInputBudgets() == null ? getAll() : getFilteredInputBudgets(), "inputBudgetReport.jrxml");
		}
		return "";
	}

	private SelectItem[] createFilterOptions(String[] situationsEnum, String[] situations) {
		SelectItem[] options = new SelectItem[situationsEnum.length + 1];

		options[0] = new SelectItem("", "Selecione");
		for (int i = 0; i < situationsEnum.length; i++) {
			options[i + 1] = new SelectItem(situationsEnum[i], situations[i]);
		}

		return options;
	}

	public List<InputBudget> retrieveBySituationAndCustomer(InputBudgetSituationEnum situation, Customer customer) {
		return getInputBudgetFacade().retrieveBySituationAndCustomer(situation, customer);
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
	
	public String extractInformation() throws IOException {
		List<InputBudget> inputBudgets = new ArrayList<InputBudget>();
		inputBudgets.add(getEntitySelected());
		Util.extractReport("OrcamentoEntrada-"+inputBudgets.get(0).getId()+"-", inputBudgets, "inputBudgetInformation.jrxml");
		return "";
	}
}

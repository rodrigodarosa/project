package com.tooling.project.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.chart.PieChartModel;

import com.tooling.project.datamodel.OutputBudgetDataModel;
import com.tooling.project.facade.EmployeeFacade;
import com.tooling.project.facade.OutputBudgetFacade;
import com.tooling.project.facade.ProviderFacade;
import com.tooling.project.model.OutputBudget;
import com.tooling.project.model.Provider;
import com.tooling.project.util.Mail;
import com.tooling.project.util.Util;
import com.tooling.project.validators.OutBudgetValidator;

@ManagedBean
@ViewScoped
public class OutputBudgetBean implements Serializable {

	private static final long serialVersionUID = 2464418641496983053L;
	private OutputBudgetFacade outputBudgetFacade;
	private OutputBudget entity;
	private PieChartModel pieModel;
	private OutputBudgetDataModel outputBudgetDataModel;
	private OutputBudget entitySelected;
	private Provider entitySelectedSearch;
	private String providerName;
	private ProviderFacade providerFacade;
	private List<OutputBudget> filteredOutputBudgets;
	private EmployeeFacade employeeFacade;
	
	public EmployeeFacade getEmployeeFacade() {
		if(employeeFacade == null){
			employeeFacade = new EmployeeFacade();
		}
		return employeeFacade;
	}

	public void setEmployeeFacade(EmployeeFacade employeeFacade) {
		this.employeeFacade = employeeFacade;
	}

	public List<OutputBudget> getFilteredOutputBudgets() {
		return filteredOutputBudgets;
	}

	public void setFilteredOutputBudgets(List<OutputBudget> filteredOutputBudgets) {
		this.filteredOutputBudgets = filteredOutputBudgets;
	}

	public OutputBudget getEntity() {
		if (entity == null) {
			entity = new OutputBudget();
		}
		return entity;
	}

	public void setEntity(OutputBudget entity) {
		this.entity = entity;
	}

	public OutputBudgetFacade getOutputBudgetFacade() {
		if (outputBudgetFacade == null) {
			outputBudgetFacade = new OutputBudgetFacade();
		}
		return outputBudgetFacade;
	}

	public void setOutputBudgetFacade(OutputBudgetFacade outputBudgetFacade) {
		this.outputBudgetFacade = outputBudgetFacade;
	}

	public OutputBudgetDataModel getOutputBudgetDataModel() {
		return outputBudgetDataModel;
	}

	public void setOutputBudgetDataModel(OutputBudgetDataModel outputBudgetDataModel) {
		this.outputBudgetDataModel = outputBudgetDataModel;
	}

	public OutputBudget getEntitySelected() {
		return entitySelected;
	}

	public void setEntitySelected(OutputBudget entitySelected) {
		this.entitySelected = entitySelected;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public String save() {
		entity.setEmployee(getEmployeeFacade().getCurrentLogged());
		if (providerName != null && providerName != "") {
			Provider provider = new Provider();
			provider = getProviderFacade().retrieveByExactName(providerName);
			entity.setProvider(provider);
		}

		if (OutBudgetValidator.validate(entity)) {
			getOutputBudgetFacade().save(entity);
			entity = new OutputBudget();
			providerName = "";
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Or\u00E7amento de Sa\u00EDda", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return "outputBudgets";
	}

	public String remove() {
		getOutputBudgetFacade().remove(entitySelected);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Or\u00E7amento de Sa\u00EDda", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "outputBudgets";

	}

	public OutputBudget retrieve(Long id) {
		return getOutputBudgetFacade().retrieve(id);
	}

	public void update() {
		if (providerName != null && providerName != "") {
			Provider provider = new Provider();
			provider = getProviderFacade().retrieveByExactName(providerName);
			entitySelected.setProvider(provider);
		}
		if (OutBudgetValidator.validate(entitySelected)) {
			getOutputBudgetFacade().update(entitySelected);
			entitySelected = new OutputBudget();
			providerName = "";
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Or\u00E7amento de Sa\u00EDda", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public List<OutputBudget> getAll() {
		return getOutputBudgetFacade().getAll();
	}



	public PieChartModel getPieModel() {
		return pieModel;
	}

	public OutputBudgetBean() {
		outputBudgetDataModel = new OutputBudgetDataModel(getAll());
	}


	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
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

	public void edit() {
		providerName = getEntitySelected().getProvider().getName();
	}

	public Provider getEntitySelectedSearch() {
		if (entitySelectedSearch == null) {
			entitySelectedSearch = new Provider();
		}
		return entitySelectedSearch;
	}

	public void setEntitySelectedSearch(Provider entitySelectedSearch) {
		this.entitySelectedSearch = entitySelectedSearch;
	}

	public void applyProvider() {
		providerName = getEntitySelectedSearch().getName();
	}

	public String extractReport() throws IOException {
		Util.extractReport("RelatorioOrcamentoSaida-", getFilteredOutputBudgets() == null ? getAll() : getFilteredOutputBudgets(), "outputBudgetReport.jrxml");
		return "";
	}
	
	public String extractInformation() throws IOException {
		List<OutputBudget> outputBudgets = new ArrayList<OutputBudget>();
		outputBudgets.add(getEntitySelected());
		Util.extractReport("OrcamentoSaida-"+outputBudgets.get(0).getId()+"-", outputBudgets, "outputBudgetInformation.jrxml");
		return "";
	}
	
	public String sendMail(){
		try {
			Mail.sendMail(getEntitySelected());
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicita\u00E7\u00E3o de Or\u00E7amento", "Or\u00E7amento enviado ao Fornecedor!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}

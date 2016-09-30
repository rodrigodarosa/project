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

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.chart.PieChartModel;

import com.tooling.project.datamodel.ReceivableDataModel;
import com.tooling.project.enums.DischargeSituationEnum;
import com.tooling.project.enums.PaymentTypeEnum;
import com.tooling.project.facade.CustomerFacade;
import com.tooling.project.facade.ReceivableFacade;
import com.tooling.project.facade.SaleFacade;
import com.tooling.project.model.Customer;
import com.tooling.project.model.Receivable;
import com.tooling.project.model.Sale;
import com.tooling.project.util.Util;
import com.tooling.project.validators.ReceivableValidator;

@ManagedBean
@ViewScoped
public class ReceivableBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Receivable entity;
	private ReceivableFacade receivableFacade;
	private PieChartModel pieModel;
	private Receivable entitySelected;
	private ReceivableDataModel receivableDataModel;
	private SelectItem[] paymentTypeOptions;
	private SelectItem[] dischargeSituationOptions;
	private final static String[] paymentTypeEnum;
	private final static String[] paymentTypes;
	private final static String[] situationsEnum;
	private final static String[] situations;
	private boolean times;
	private boolean payd;
	private String customerName;
	private Long saleNumber;
	private CustomerFacade customerFacade;
	private SaleFacade saleFacade;
	private Sale entitySelectedSearch;
	private List<Receivable> filteredReceivables;
	
	public List<Receivable> getFilteredReceivables() {
		return filteredReceivables;
	}

	public void setFilteredReceivables(List<Receivable> filteredReceivables) {
		this.filteredReceivables = filteredReceivables;
	}

	static {

		situationsEnum = new String[2];
		situationsEnum[0] = "COMPLETED";
		situationsEnum[1] = "PENDING";

		situations = new String[2];
		situations[0] = "Completo";
		situations[1] = "Pendente";

		paymentTypeEnum = new String[3];
		paymentTypeEnum[0] = "CARD";
		paymentTypeEnum[1] = "DEPOSIT";
		paymentTypeEnum[2] = "MONEY";

		paymentTypes = new String[3];
		paymentTypes[0] = "Cart\u00E3o";
		paymentTypes[1] = "Dep\u00F3sito";
		paymentTypes[2] = "Dinheiro";
	}

	public SaleFacade getSaleFacade() {
		if (saleFacade == null) {
			saleFacade = new SaleFacade();
		}
		return saleFacade;
	}

	public void setSaleFacade(SaleFacade saleFacade) {
		this.saleFacade = saleFacade;
	}

	public Receivable getEntity() {
		if (entity == null) {
			entity = new Receivable();
		}
		return entity;
	}

	public void setEntity(Receivable entity) {
		this.entity = entity;
	}

	public ReceivableFacade getReceivableFacade() {
		if (receivableFacade == null) {
			receivableFacade = new ReceivableFacade();
		}
		return receivableFacade;
	}

	public void setReceivableFacade(ReceivableFacade receivableFacade) {
		this.receivableFacade = receivableFacade;
	}

	public Sale getEntitySelectedSearch() {
		if (entitySelectedSearch == null) {
			entitySelectedSearch = new Sale();
		}
		return entitySelectedSearch;
	}

	public void setEntitySelectedSearch(Sale entitySelectedSearch) {
		this.entitySelectedSearch = entitySelectedSearch;
	}

	public String save() {
		if (getEntity().getDischargeSituation() != null && !getEntity().getDischargeSituation().equals(DischargeSituationEnum.COMPLETED)) {
			getEntity().setPaymentDate(null);
		}
		if (getEntity().getPaymentType() != null && !getEntity().getPaymentType().equals(PaymentTypeEnum.CARD)) {
			getEntity().setPaymentTimes(null);
		}

		if (customerName != null && customerName != "") {
			Customer customer = new Customer();
			customer = getCustomerFacade().retrieveByExactName(customerName);
			entity.setCustomer(customer);
		}

		if (saleNumber != null) {
			Sale sale = new Sale();
			sale = getSaleFacade().retrieve(saleNumber);
			entity.setSale(sale);
		}

		if (ReceivableValidator.validate(entity)) {
			getReceivableFacade().save(entity);
			entity = new Receivable();
			saleNumber = new Long(0);
			customerName = "";
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contas a Receber", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return "receivables";

	}

	public String remove() {
		getReceivableFacade().remove(entitySelected);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contas a Receber", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "receivables";

	}

	public Receivable retrieve(Long id) {
		return getReceivableFacade().retrieve(id);
	}

	public String update() {
		if (!getEntitySelected().getDischargeSituation().equals(DischargeSituationEnum.COMPLETED)) {
			getEntitySelected().setPaymentDate(null);
		}
		if (!getEntitySelected().getPaymentType().equals(PaymentTypeEnum.CARD)) {
			getEntitySelected().setPaymentTimes(null);
		}

		if (customerName != null && customerName != "") {
			Customer customer = new Customer();
			customer = getCustomerFacade().retrieveByExactName(customerName);
			entitySelected.setCustomer(customer);
		}

		if (saleNumber != null) {
			Sale sale = new Sale();
			sale = getSaleFacade().retrieve(saleNumber);
			entitySelected.setSale(sale);
		}

		if (ReceivableValidator.validate(entitySelected)) {
			getReceivableFacade().update(entitySelected);
			entitySelected = new Receivable();
			saleNumber = new Long(0);
			customerName = "";
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compras", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return "";

	}

	public Receivable getEntitySelected() {
		if (entitySelected == null) {
			entitySelected = new Receivable();
		}
		return entitySelected;
	}

	public void setEntitySelected(Receivable entitySelected) {
		this.entitySelected = entitySelected;
	}

	public ReceivableDataModel getReceivableDataModel() {
		return receivableDataModel;
	}

	public void setReceivableDataModel(ReceivableDataModel receivableDataModel) {
		this.receivableDataModel = receivableDataModel;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public List<Receivable> getAll() {

		return getReceivableFacade().getAll();
	}



	public PieChartModel getPieModel() {
		return pieModel;
	}

	public ReceivableBean() {
		createPieModel();
		paymentTypeOptions = createFilterOptions(paymentTypeEnum, paymentTypes);
		dischargeSituationOptions = createFilterOptions(situationsEnum, situations);
		receivableDataModel = new ReceivableDataModel(getAll());
	}


	private void createPieModel() {
		pieModel = new PieChartModel();

		pieModel.set("Completo", getReceivableFacade().getCountBySituation(DischargeSituationEnum.COMPLETED));
		pieModel.set("Pendente", getReceivableFacade().getCountBySituation(DischargeSituationEnum.PENDING));
	}

	private SelectItem[] createFilterOptions(String[] first, String[] second) {
		SelectItem[] options = new SelectItem[first.length + 1];

		options[0] = new SelectItem("", "Selecione");
		for (int i = 0; i < first.length; i++) {
			options[i + 1] = new SelectItem(first[i], second[i]);
		}

		return options;
	}

	public SelectItem[] getPaymentTypeOptions() {
		return paymentTypeOptions;
	}

	public void setPaymentTypeOptions(SelectItem[] paymentTypeOptions) {
		this.paymentTypeOptions = paymentTypeOptions;
	}

	public SelectItem[] getDischargeSituationOptions() {
		return dischargeSituationOptions;
	}

	public void setDischargeSituationOptions(SelectItem[] dischargeSituationOptions) {
		this.dischargeSituationOptions = dischargeSituationOptions;
	}

	public boolean isTimes() {
		return times;
	}

	public void setTimes(boolean times) {
		this.times = times;
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

	public boolean isPayd() {
		return payd;
	}

	public void setPayd(boolean payd) {
		this.payd = payd;
	}

	public void changePaymentType() {
		if (getEntity().getPaymentType() != null && getEntity().getPaymentType().equals(PaymentTypeEnum.CARD)) {
			times = true;
		} else {
			times = false;
		}
	}

	public void changePaymentTypeEdit() {
		if (getEntitySelected().getPaymentType() != null && getEntitySelected().getPaymentType().equals(PaymentTypeEnum.CARD)) {
			times = true;
		} else {
			times = false;
		}
	}

	public void changeDischargeSituation() {

		if (getEntity().getDischargeSituation() != null && getEntity().getDischargeSituation().equals(DischargeSituationEnum.COMPLETED)) {
			payd = true;
		} else {
			payd = false;
		}
	}

	public void changeDischargeSituationEdit() {

		if (getEntitySelected().getDischargeSituation() != null && getEntitySelected().getDischargeSituation().equals(DischargeSituationEnum.COMPLETED)) {
			payd = true;
		} else {
			payd = false;
		}
	}

	public String edit() {
		customerName = getEntitySelected().getCustomer().getName();
		saleNumber = getEntitySelected().getSale().getId();
		changeDischargeSituationEdit();
		changePaymentTypeEdit();

		return "";
	}

	public Long getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(Long saleNumber) {
		this.saleNumber = saleNumber;
	}

	public void applySale() {
		saleNumber = getEntitySelectedSearch().getId();
		customerName = getEntitySelectedSearch().getCustomer().getName();
	}
	
	public String extractReport() throws IOException {
		Util.extractReport("RelatorioContasAReceber-", getFilteredReceivables() == null ? getAll() : getFilteredReceivables(), "receivableReport.jrxml");
		return "";
	}
	
	public String extractInformation() throws IOException {
		List<Receivable> receivables = new ArrayList<Receivable>();
		receivables.add(getEntitySelected());
		Util.extractReport("ContasAReceber-"+receivables.get(0).getId()+"-", receivables, "receivableInformation.jrxml");
		return "";
	}

}

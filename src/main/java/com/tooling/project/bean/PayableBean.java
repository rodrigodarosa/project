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
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.tooling.project.datamodel.PayableDataModel;
import com.tooling.project.enums.DischargeSituationEnum;
import com.tooling.project.enums.PaymentTypeEnum;
import com.tooling.project.facade.PayableFacade;
import com.tooling.project.facade.ProviderFacade;
import com.tooling.project.facade.PurchaseFacade;
import com.tooling.project.model.Payable;
import com.tooling.project.model.Provider;
import com.tooling.project.model.Purchase;
import com.tooling.project.util.Util;
import com.tooling.project.validators.PayableValidator;

@ManagedBean
@ViewScoped
public class PayableBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Payable entity;
	private PayableFacade payableFacade;
	private ProviderFacade providerFacade;
	private PurchaseFacade purchaseFacade;
	private CartesianChartModel categoryModel;
	private PieChartModel pieModel;
	private Payable entitySelected;
	private Purchase entitySelectedSearch;
	private PayableDataModel payableDataModel;
	private SelectItem[] paymentTypeOptions;
	private SelectItem[] dischargeSituationOptions;
	private final static String[] paymentTypeEnum;
	private final static String[] paymentTypes;
	private final static String[] situationsEnum;
	private final static String[] situations;
	private boolean times;
	private boolean paid;
	private String providerName;
	private Long purchaseNumber;
	private List<Payable> filteredPayables;
	
	public List<Payable> getFilteredPayables() {
		return filteredPayables;
	}

	public void setFilteredPayables(List<Payable> filteredPayables) {
		this.filteredPayables = filteredPayables;
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

	public PurchaseFacade getPurchaseFacade() {
		if (purchaseFacade == null) {
			purchaseFacade = new PurchaseFacade();
		}
		return purchaseFacade;
	}

	public void setPurchaseFacade(PurchaseFacade purchaseFacade) {
		this.purchaseFacade = purchaseFacade;
	}

	public Payable getEntity() {
		if (entity == null) {
			entity = new Payable();
		}
		return entity;
	}

	public void setEntity(Payable entity) {
		this.entity = entity;
	}

	public PayableFacade getPayableFacade() {
		if (payableFacade == null) {
			payableFacade = new PayableFacade();
		}
		return payableFacade;
	}

	public void setPayableFacade(PayableFacade payableFacade) {
		this.payableFacade = payableFacade;
	}

	public String save() {
		if (getEntity().getDischargeSituation() != null && !getEntity().getDischargeSituation().equals(DischargeSituationEnum.COMPLETED)) {
			getEntity().setPaymentDate(null);
		}
		if (getEntity().getPaymentType() != null && !getEntity().getPaymentType().equals(PaymentTypeEnum.CARD)) {
			getEntity().setPaymentTimes(null);
		}
		if (providerName != null && providerName != "") {
			Provider provider = new Provider();
			provider = getProviderFacade().retrieveByExactName(providerName);
			entity.setProvider(provider);
		}

		if (purchaseNumber != null) {
			Purchase purchase = new Purchase();
			purchase = getPurchaseFacade().retrieve(purchaseNumber);
			entity.setPurchase(purchase);
		}

		if (PayableValidator.validate(entity)) {
			getPayableFacade().save(entity);
			entity = new Payable();
			providerName = "";
			purchaseNumber = new Long(0);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contas a Pagar", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		}
		
		

		return "payables";

	}

	public String remove() {
		getPayableFacade().remove(entitySelected);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contas a Pagar", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "payables";
	}

	public Payable retrieve(Long id) {
		return getPayableFacade().retrieve(id);
	}

	public String update() {
		if (getEntitySelected().getDischargeSituation() != null && !getEntitySelected().getDischargeSituation().equals(DischargeSituationEnum.COMPLETED)) {
			getEntitySelected().setPaymentDate(null);
		}
		if (getEntitySelected().getPaymentType() != null && !getEntitySelected().getPaymentType().equals(PaymentTypeEnum.CARD)) {
			getEntitySelected().setPaymentTimes(null);
		}

		if (providerName != null && providerName != "") {
			Provider provider = new Provider();
			provider = getProviderFacade().retrieveByExactName(providerName);
			entitySelected.setProvider(provider);
		}

		if (purchaseNumber != null) {
			Purchase purchase = new Purchase();
			purchase = getPurchaseFacade().retrieve(purchaseNumber);
			entitySelected.setPurchase(purchase);
		}

		if (PayableValidator.validate(entitySelected)) {
			getPayableFacade().update(entitySelected);
			entitySelected = new Payable();
			providerName = "";
			purchaseNumber = new Long(0);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contas a Pagar", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return "";

	}

	public Payable getEntitySelected() {
		if (entitySelected == null) {
			entitySelected = new Payable();
		}
		return entitySelected;
	}

	public void setEntitySelected(Payable entitySelected) {
		this.entitySelected = entitySelected;
	}

	public PayableDataModel getPayableDataModel() {
		return payableDataModel;
	}

	public void setPayableDataModel(PayableDataModel payableDataModel) {
		this.payableDataModel = payableDataModel;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public List<Payable> getAll() {
		return getPayableFacade().getAll();
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public PayableBean() {
		createPieModel();
		paymentTypeOptions = createFilterOptions(paymentTypeEnum, paymentTypes);
		dischargeSituationOptions = createFilterOptions(situationsEnum, situations);
		payableDataModel = new PayableDataModel(getAll());
	}

	private void createPieModel() {
		pieModel = new PieChartModel();
		pieModel.set("Completo", getPayableFacade().getCountBySituation(DischargeSituationEnum.COMPLETED));
		pieModel.set("Pendente", getPayableFacade().getCountBySituation(DischargeSituationEnum.PENDING));
	}

	public SelectItem[] getPaymentTypeOptions() {
		return paymentTypeOptions;
	}

	public void setPaymentTypeOptions(SelectItem[] paymentTypeOptions) {
		this.paymentTypeOptions = paymentTypeOptions;
	}

	private SelectItem[] createFilterOptions(String[] first, String[] second) {
		SelectItem[] options = new SelectItem[first.length + 1];

		options[0] = new SelectItem("", "Selecione");
		for (int i = 0; i < first.length; i++) {
			options[i + 1] = new SelectItem(first[i], second[i]);
		}

		return options;
	}

	public SelectItem[] getDischargeSituationOptions() {
		return dischargeSituationOptions;
	}

	public void setDischargeSituationOptions(SelectItem[] dischargeSituationOptions) {
		this.dischargeSituationOptions = dischargeSituationOptions;
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

	public boolean isTimes() {
		return times;
	}

	public void setTimes(boolean times) {
		this.times = times;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public void changeDischargeSituation() {

		if (getEntity().getDischargeSituation() != null && getEntity().getDischargeSituation().equals(DischargeSituationEnum.COMPLETED)) {
			paid = true;
		} else {
			paid = false;
		}
	}

	public void changeDischargeSituationEdit() {

		if (getEntitySelected().getDischargeSituation() != null && getEntitySelected().getDischargeSituation().equals(DischargeSituationEnum.COMPLETED)) {
			paid = true;
		} else {
			paid = false;
		}
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
		purchaseNumber = getEntitySelected().getPurchase().getId();
		changeDischargeSituationEdit();
		changePaymentTypeEdit();
	}

	public Long getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(Long purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public Purchase getEntitySelectedSearch() {
		if (entitySelectedSearch == null) {
			entitySelectedSearch = new Purchase();
		}
		return entitySelectedSearch;
	}

	public void setEntitySelectedSearch(Purchase entitySelectedSearch) {
		this.entitySelectedSearch = entitySelectedSearch;
	}

	public void applyPurchase() {
		purchaseNumber = getEntitySelectedSearch().getId();
		providerName = getEntitySelectedSearch().getProvider().getName();
	}
	
	public String extractReport() throws IOException {
		Util.extractReport("RelatorioContasAPagar-", getFilteredPayables() == null ? getAll() : getFilteredPayables(), "payableReport.jrxml");
		return "";
	}
	
	public String extractInformation() throws IOException {
		List<Payable> payables = new ArrayList<Payable>();
		payables.add(getEntitySelected());
		Util.extractReport("ContasAPagar-"+payables.get(0).getId()+"-", payables, "payableInformation.jrxml");
		return "";
	}

}

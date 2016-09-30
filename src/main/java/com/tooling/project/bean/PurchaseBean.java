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
import org.primefaces.model.DualListModel;

import com.tooling.project.datamodel.PurchaseDataModel;
import com.tooling.project.facade.EmployeeFacade;
import com.tooling.project.facade.ProductFacade;
import com.tooling.project.facade.ProviderFacade;
import com.tooling.project.facade.PurchaseFacade;
import com.tooling.project.model.Employee;
import com.tooling.project.model.Product;
import com.tooling.project.model.Provider;
import com.tooling.project.model.Purchase;
import com.tooling.project.util.Util;
import com.tooling.project.validators.PurchaseValidator;

@ManagedBean
@ViewScoped
public class PurchaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Purchase entity;
	private Purchase entitySelected;
	private Provider entitySelectedSearch;
	private Employee entitySelectedSearch2;
	private PurchaseFacade purchaseFacade;
	private ProductFacade productFacade;
	private EmployeeFacade employeeFacade;
	private PurchaseDataModel purchaseDataModel;
	private String providerName;
	private String buyerName;
	private ProviderFacade providerFacade;
	private List<Purchase> filteredPurchases;
	private List<Product> source;
	private List<Product> target;
	private DualListModel<Product> allProducts;

	public DualListModel<Product> getAllProducts() {
		if (allProducts == null) {
			allProducts = new DualListModel<Product>(getSource(), getTarget());
		}
		return allProducts;
	}

	public void setAllProducts(DualListModel<Product> allProducts) {
		this.allProducts = allProducts;
	}

	public List<Product> getSource() {
		source = getProductFacade().getAll();
		source.removeAll(getProductFacade().retrieveProductsNoInPurchase());
		return source;
	}

	public void setSource(List<Product> source) {
		this.source = source;
	}

	public List<Product> getTarget() {
		if (target == null) {
			target = new ArrayList<Product>();
		}
		return target;
	}

	public void setTarget(List<Product> target) {
		this.target = target;
	}

	public List<Purchase> getFilteredPurchases() {
		return filteredPurchases;
	}

	public void setFilteredPurchases(List<Purchase> filteredPurchases) {
		this.filteredPurchases = filteredPurchases;
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

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public ProductFacade getProductFacade() {
		if (productFacade == null) {
			productFacade = new ProductFacade();
		}
		return productFacade;
	}

	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
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

	public Purchase getEntity() {
		if (entity == null) {
			entity = new Purchase();
		}
		return entity;
	}

	public void setEntity(Purchase entity) {
		this.entity = entity;
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

	public String save() {

		entity.setProducts(allProducts.getTarget());
		if (providerName != null && providerName != "") {
			Provider provider = new Provider();
			provider = getProviderFacade().retrieveByExactName(providerName);
			entity.setProvider(provider);
		}
		if (buyerName != null && buyerName != "") {
			Employee buyer = new Employee();
			buyer = getEmployeeFacade().retrieveByExactName(buyerName);
			entity.setBuyer(buyer);
		}

		if (PurchaseValidator.validate(entity)) {
			getPurchaseFacade().save(entity);
			entity = new Purchase();
			providerName = "";

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return "purchases";
	}

	public String remove() {
		getPurchaseFacade().remove(entitySelected);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra", "Removido com Sucesso!");
		FacesContext.getCurrentInstance().addMessage(null, message);

		return "purchases";
	}

	public Purchase retrieve(Long id) {
		return getPurchaseFacade().retrieve(id);
	}

	public void update() {
		entitySelected.setProducts(allProducts.getTarget());

		if (providerName != null && providerName != "") {
			Provider provider = new Provider();
			provider = getProviderFacade().retrieveByExactName(providerName);
			getEntitySelected().setProvider(provider);
		}
		if (buyerName != null && buyerName != "") {
			Employee buyer = new Employee();
			buyer = getEmployeeFacade().retrieveByExactName(buyerName);
			getEntitySelected().setBuyer(buyer);
		}

		if (PurchaseValidator.validate(getEntitySelected())) {
			getPurchaseFacade().update(getEntitySelected());
			entitySelected = new Purchase();
			providerName = "";

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public List<Purchase> getAll() {
		return getPurchaseFacade().getAll();
	}

	public Purchase getEntitySelected() {
		if (entitySelected == null) {
			entitySelected = new Purchase();
		}
		return entitySelected;
	}

	public void setEntitySelected(Purchase entitySelected) {
		this.entitySelected = entitySelected;
	}

	public PurchaseDataModel getPurchaseDataModel() {
		return purchaseDataModel;
	}

	public void setPurchaseDataModel(PurchaseDataModel purchaseDataModel) {
		this.purchaseDataModel = purchaseDataModel;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public PurchaseBean() {
		purchaseDataModel = new PurchaseDataModel(getAll());
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public void edit() {
		providerName = getEntitySelected().getProvider().getName();
		buyerName = getEntitySelected().getBuyer().getName();

		setTarget(entitySelected.getProducts());
		allProducts = new DualListModel<Product>(getSource(), target);

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

	public Employee getEntitySelectedSearch2() {
		if (entitySelectedSearch2 == null) {
			entitySelectedSearch2 = new Employee();
		}
		return entitySelectedSearch2;
	}

	public void setEntitySelectedSearch2(Employee entitySelectedSearch2) {
		this.entitySelectedSearch2 = entitySelectedSearch2;
	}

	public void applyProvider() {
		providerName = getEntitySelectedSearch().getName();
	}

	public void applyEmployee() {
		buyerName = getEntitySelectedSearch2().getName();
	}

	public String extractReport() throws IOException {
		Util.extractReport("RelatorioCompras-", getFilteredPurchases() == null ? getAll() : getFilteredPurchases(), "purchaseReport.jrxml");
		return "";
	}
	
	public String extractInformation() throws IOException {
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchases.add(getEntitySelected());
		Util.extractReport("Compra-"+purchases.get(0).getId()+"-", purchases, "purchaseInformation.jrxml");
		return "";
	}

}

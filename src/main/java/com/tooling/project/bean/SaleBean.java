package com.tooling.project.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import com.tooling.project.datamodel.SaleDataModel;
import com.tooling.project.facade.CustomerFacade;
import com.tooling.project.facade.EmployeeFacade;
import com.tooling.project.facade.ManufacturedProductFacade;
import com.tooling.project.facade.SaleFacade;
import com.tooling.project.model.Customer;
import com.tooling.project.model.Employee;
import com.tooling.project.model.ManufacturedProduct;
import com.tooling.project.model.Sale;
import com.tooling.project.util.Util;
import com.tooling.project.validators.SaleValidator;

@ManagedBean
@ViewScoped
public class SaleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Sale entity;
	private SaleFacade saleFacade;
	private SaleDataModel saleDataModel;
	private String customerName;
	private String sellerName;
	private CustomerFacade customerFacade;
	private EmployeeFacade employeeFacade;
	private Sale entitySelected;
	private Customer entitySelectedSearch;
	private Employee entitySelectedSearch2;
	private List<Sale> filteredSales;
	private List<ManufacturedProduct> source;
	private List<ManufacturedProduct> target;
	private DualListModel<ManufacturedProduct> allManufacturedProducts;
	private ManufacturedProductFacade manufacturedProductFacade;
	
	public ManufacturedProductFacade getManufacturedProductFacade() {
		if(manufacturedProductFacade == null){
			manufacturedProductFacade = new ManufacturedProductFacade();
		}
		return manufacturedProductFacade;
	}

	public void setManufacturedProductFacade(ManufacturedProductFacade manufacturedProductFacade) {
		this.manufacturedProductFacade = manufacturedProductFacade;
	}

	public DualListModel<ManufacturedProduct> getAllManufacturedProducts() {
		if(allManufacturedProducts == null){
			allManufacturedProducts = new DualListModel<ManufacturedProduct>(getSource(), getTarget());
		}
		return allManufacturedProducts;
	}

	public void setAllManufacturedProducts(DualListModel<ManufacturedProduct> allManufacturedProducts) {
		this.allManufacturedProducts = allManufacturedProducts;
	}

	public List<ManufacturedProduct> getSource() {
			source = getManufacturedProductFacade().getAll();
			source.removeAll(getManufacturedProductFacade().retrieveManufacturedProductsNoInPurchase());
		return source;
	}

	public void setSource(List<ManufacturedProduct> source) {
		this.source = source;
	}

	public List<ManufacturedProduct> getTarget() {
		if(target == null){
			target = new ArrayList<ManufacturedProduct>();
		}
		return target;
	}
	
	public void setTarget(List<ManufacturedProduct> target){
		this.target = target;
	}
	
	public List<Sale> getFilteredSales() {
		return filteredSales;
	}

	public void setFilteredSales(List<Sale> filteredSales) {
		this.filteredSales = filteredSales;
	}

	public Sale getEntitySelected() {
		if (entitySelected == null) {
			entitySelected = new Sale();
		}
		return entitySelected;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
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

	public void setEntitySelected(Sale entitySelected) {
		this.entitySelected = entitySelected;
	}

	public SaleDataModel getSaleDataModel() {
		return saleDataModel;
	}

	public void setSaleDataModel(SaleDataModel saleDataModel) {
		this.saleDataModel = saleDataModel;
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

	public SaleBean() {
		saleDataModel = new SaleDataModel(getAll());
	}

	public Sale getEntity() {
		if (entity == null) {
			entity = new Sale();
		}
		return entity;
	}

	public void setEntity(Sale entity) {
		this.entity = entity;
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

	public String save() {
		
		entity.setManufacturedProducts(allManufacturedProducts.getTarget());
		
		if (customerName != null && customerName != "") {
			Customer customer = new Customer();
			customer = getCustomerFacade().retrieveByExactName(customerName);
			entity.setCustomer(customer);
		}

		if (sellerName != null && sellerName != "") {
			Employee seller = new Employee();
			seller = getEmployeeFacade().retrieveByExactName(sellerName);
			entity.setSeller(seller);
		}

		if (SaleValidator.validate(entity)) {
			getSaleFacade().save(entity);
			entity = new Sale();
			customerName = "";
			sellerName = "";
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venda", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "sales";

	}

	public String remove() {
		try{
			getSaleFacade().remove(entitySelected);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venda", "Removido com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (ConstraintViolationException e){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "N\u00E3o foi poss\u00EDvel excluir!", "Registro sendo usado.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return "sales";

	}

	public Sale retrieve(Long id) {
		return getSaleFacade().retrieve(id);
	}

	public void update() {
		entitySelected.setManufacturedProducts(allManufacturedProducts.getTarget());
		
		if (customerName != null && customerName != "") {
			Customer customer = new Customer();
			customer = getCustomerFacade().retrieveByExactName(customerName);
			entitySelected.setCustomer(customer);
		}
		
		if (sellerName != null && sellerName != "") {
			Employee seller = new Employee();
			seller = getEmployeeFacade().retrieveByExactName(sellerName);
			entitySelected.setSeller(seller);
		}

		if (SaleValidator.validate(entitySelected)) {
			getSaleFacade().update(entitySelected);
			entitySelected = new Sale();
			customerName = "";
			sellerName = "";
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venda", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public List<Sale> getAll() {
		return getSaleFacade().getAll();
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public void edit() {
		customerName = getEntitySelected().getCustomer().getName();
		sellerName = getEntitySelected().getSeller().getName();
		
		setTarget(entitySelected.getManufacturedProducts());
		allManufacturedProducts= new DualListModel<ManufacturedProduct>(getSource(), target);
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

	public Employee getEntitySelectedSearch2() {
		if (entitySelectedSearch2 == null) {
			entitySelectedSearch2 = new Employee();
		}
		return entitySelectedSearch2;
	}

	public void setEntitySelectedSearch2(Employee entitySelectedSearch2) {
		this.entitySelectedSearch2 = entitySelectedSearch2;
	}

	public void applyCustomer() {
		customerName = getEntitySelectedSearch().getName();
	}

	public void applyEmployee() {
		sellerName = getEntitySelectedSearch2().getName();
	}

	public String extractReport() throws IOException {
		Util.extractReport("RelatorioVendas-", getFilteredSales() == null ? getAll() : getFilteredSales(), "saleReport.jrxml");
		return "";
	}
	
	public String extractInformation() throws IOException {
		List<Sale> sales = new ArrayList<Sale>();
		sales.add(getEntitySelected());
		Util.extractReport("Venda-"+sales.get(0).getId()+"-", sales, "saleInformation.jrxml");
		return "";
	}
}

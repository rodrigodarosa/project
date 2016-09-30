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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.tooling.project.datamodel.ProductDataModel;
import com.tooling.project.facade.ProductFacade;
import com.tooling.project.model.Product;
import com.tooling.project.util.Util;
import com.tooling.project.validators.ProductValidator;

@ManagedBean
@ViewScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Product entity;
	private ProductFacade productFacade;
	private Product entitySelected;
	private ProductDataModel productDataModel;
	private SelectItem[] productTypeOptions;
	private final static String[] typesEnum;
	private final static String[] types;
	private List<Product> filteredProducts;
	
	public List<Product> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(List<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

	static {

		typesEnum = new String[4];
		typesEnum[0] = "TOOL";
		typesEnum[1] = "IT";
		typesEnum[2] = "INFRASTRUCTURE";
		typesEnum[3] = "OTHERS";

		types = new String[4];
		types[0] = "Ferramenta";
		types[1] = "T.I.";
		types[2] = "Infraestrutura";
		types[3] = "Outros";
	}

	public Product getEntity() {
		if (entity == null) {
			entity = new Product();
		}
		return entity;
	}

	public void setEntity(Product entity) {
		this.entity = entity;
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

	public Product getEntitySelected() {
		if (entitySelected == null) {
			entitySelected = new Product();
		}
		return entitySelected;

	}

	public void setEntitySelected(Product entitySelected) {
		this.entitySelected = entitySelected;
	}

	public ProductDataModel getProductDataModel() {
		return productDataModel;
	}

	public void setProductDataModel(ProductDataModel productDataModel) {
		this.productDataModel = productDataModel;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public ProductBean() {
		
		getProductFacade().retrieveProductsNoInPurchase();
		productTypeOptions = createFilterOptions(typesEnum, types);
		productDataModel = new ProductDataModel(getAll());
	}

	public String save() {

		if (ProductValidator.validate(entity)) {
			getProductFacade().save(entity);
			entity = new Product();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "products";
	}

	public String remove() {
		try {
			getProductFacade().remove(entitySelected);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto", "Removido com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ConstraintViolationException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "N\u00E3o foi poss\u00EDvel excluir!", "Registro sendo usado.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

		return "products";
	}

	public Product retrieve(Long id) {
		return getProductFacade().retrieve(id);
	}

	public void update() {
		
		if (ProductValidator.validate(entitySelected)) {
			getProductFacade().update(entitySelected);
			entitySelected = new Product();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public List<Product> getAll() {
		return getProductFacade().getAll();
	}

	public SelectItem[] getProductTypeOptions() {
		return productTypeOptions;
	}

	public void setProductTypeOptions(SelectItem[] productTypeOptions) {
		this.productTypeOptions = productTypeOptions;
	}

	private SelectItem[] createFilterOptions(String[] typesEnum, String[] types) {
		SelectItem[] options = new SelectItem[typesEnum.length + 1];

		options[0] = new SelectItem("", "Selecione");
		for (int i = 0; i < typesEnum.length; i++) {
			options[i + 1] = new SelectItem(typesEnum[i], types[i]);
		}

		return options;
	}

	public List<Product> complete(String complete) {
		List<Product> results = getProductFacade().retrieveByName(complete);
		return results;

	}


	public void edit() {
	}

	public String extractReport() throws IOException {
		Util.extractReport("RelatorioProdutos-", getFilteredProducts() == null ? getAll() : getFilteredProducts(), "productReport.jrxml");
		return "";
	}
	
	public String extractInformation() throws IOException {
		List<Product> products = new ArrayList<Product>();
		products.add(getEntitySelected());
		Util.extractReport("Produto-"+products.get(0).getId()+"-", products, "productInformation.jrxml");
		return "";
	}

}

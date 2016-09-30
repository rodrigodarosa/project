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

import com.tooling.project.datamodel.ManufacturedProductDataModel;
import com.tooling.project.facade.ManufacturedProductFacade;
import com.tooling.project.model.ManufacturedProduct;
import com.tooling.project.util.Util;
import com.tooling.project.validators.ManufacturedProductValidator;

@ManagedBean
@ViewScoped
public class ManufacturedProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ManufacturedProduct entity;
	private ManufacturedProductFacade manufacturedProductFacade;
	private ManufacturedProductDataModel manufacturedProductDataModel;
	private SelectItem[] manufacturedProductsSituationOptions;
	private ManufacturedProduct entitySelected;
	private final static String[] situationsEnum;
	private final static String[] situations;
	private List<ManufacturedProduct> filteredManufacturedProducts;

	public List<ManufacturedProduct> getFilteredManufacturedProducts() {
		return filteredManufacturedProducts;
	}

	public void setFilteredManufacturedProducts(List<ManufacturedProduct> filteredManufacturedProducts) {
		this.filteredManufacturedProducts = filteredManufacturedProducts;
	}

	static {

		situationsEnum = new String[4];
		situationsEnum[0] = "ASSEMBLING";
		situationsEnum[1] = "NOT_INITIATED";
		situationsEnum[2] = "FINALIZED";
		situationsEnum[3] = "STAND_BY";

		situations = new String[4];
		situations[0] = "Em Produ\u00E7\u00E3o";
		situations[1] = "N\u00E3o Inicializado";
		situations[2] = "Finalizado";
		situations[3] = "Em Pausa";
	}

	public ManufacturedProduct getEntity() {
		if (entity == null) {
			entity = new ManufacturedProduct();
		}
		return entity;
	}

	public void setEntity(ManufacturedProduct entity) {
		this.entity = entity;
	}

	public ManufacturedProductFacade getManufacturedProductFacade() {
		if (manufacturedProductFacade == null) {
			manufacturedProductFacade = new ManufacturedProductFacade();
		}
		return manufacturedProductFacade;
	}

	public void setManufacturedProductFacade(ManufacturedProductFacade manufacturedProductFacade) {
		this.manufacturedProductFacade = manufacturedProductFacade;
	}

	public ManufacturedProductDataModel getManufacturedProductDataModel() {
		return manufacturedProductDataModel;
	}

	public void setManufacturedProductDataModel(ManufacturedProductDataModel manufacturedProductDataModel) {
		this.manufacturedProductDataModel = manufacturedProductDataModel;
	}

	public ManufacturedProduct getEntitySelected() {
		return entitySelected;
	}

	public void setEntitySelected(ManufacturedProduct entitySelected) {
		this.entitySelected = entitySelected;
	}

	public void onRowSelect(SelectEvent event) {
		edit();
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public ManufacturedProductBean() {
		manufacturedProductsSituationOptions = createFilterOptions(situationsEnum, situations);
		manufacturedProductDataModel = new ManufacturedProductDataModel(getAll());
	}

	public String save() {

		if (ManufacturedProductValidator.validate(entity)) {
			getManufacturedProductFacade().save(entity);
			entity = new ManufacturedProduct();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Manufaturado", "Salvo com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "manufacturedProducts";
	}

	public String remove() {
		
		try{
			getManufacturedProductFacade().remove(entitySelected);
			getManufacturedProductFacade().remove(entitySelected);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Manufaturado", "Removido com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (ConstraintViolationException e){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "N\u00E3o foi poss\u00EDvel excluir!", "Registro sendo usado.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}

		return "manufacturedProducts";
	}

	public ManufacturedProduct retrieve(Long id) {
		return getManufacturedProductFacade().retrieve(id);
	}

	public void update() {

		if (ManufacturedProductValidator.validate(entitySelected)) {
			getManufacturedProductFacade().update(entitySelected);
			entitySelected = new ManufacturedProduct();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto Manufaturado", "Atualizado com Sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public List<ManufacturedProduct> getAll() {
		return getManufacturedProductFacade().getAll();
	}

	public SelectItem[] getManufacturedProductsSituationOptions() {
		return manufacturedProductsSituationOptions;
	}

	public void setManufacturedProductsSituationOptions(SelectItem[] manufacturedProductsSituationOptions) {
		this.manufacturedProductsSituationOptions = manufacturedProductsSituationOptions;
	}

	private SelectItem[] createFilterOptions(String[] situationsEnum, String[] situations) {
		SelectItem[] options = new SelectItem[situationsEnum.length + 1];

		options[0] = new SelectItem("", "Selecione");
		for (int i = 0; i < situationsEnum.length; i++) {
			options[i + 1] = new SelectItem(situationsEnum[i], situations[i]);
		}

		return options;
	}



	public void edit() {
	}


	public String extractReport() throws IOException {
		Util.extractReport("RelatorioProdutosManufaturados-", getFilteredManufacturedProducts() == null ? getAll() : getFilteredManufacturedProducts(), "manufacturedProductReport.jrxml");
		return "";
	}
	
	public String extractInformation() throws IOException {
		List<ManufacturedProduct> manufacturedProducts = new ArrayList<ManufacturedProduct>();
		manufacturedProducts.add(getEntitySelected());
		Util.extractReport("ProdutoManufaturado-"+manufacturedProducts.get(0).getId()+"-",manufacturedProducts, "manufacturedProductInformation.jrxml");
		return "";
	}

}

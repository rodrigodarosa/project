package com.tooling.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tooling.project.facade.ManufacturedProductFacade;
import com.tooling.project.model.ManufacturedProduct;

@FacesConverter(value = "manufacturedProductConverter")
public class ManufacturedProductConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		ManufacturedProductFacade jogadorDAO = new ManufacturedProductFacade();
		ManufacturedProduct manufacturedProduct = jogadorDAO.retrieveByExactName(string);
		return manufacturedProduct;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		ManufacturedProduct manufacturedProduct = new ManufacturedProduct();
		manufacturedProduct = (ManufacturedProduct) o;
		return manufacturedProduct.getName();
	}

}
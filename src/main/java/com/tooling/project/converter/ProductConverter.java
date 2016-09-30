package com.tooling.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tooling.project.facade.ProductFacade;
import com.tooling.project.model.Product;

@FacesConverter(value = "productConverter")
public class ProductConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		ProductFacade jogadorDAO = new ProductFacade();
		Product jogador = jogadorDAO.retrieveByExactName(string);
		return jogador;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Product jogador = new Product();
		jogador = (Product) o;
		return jogador.getName();
	}

}
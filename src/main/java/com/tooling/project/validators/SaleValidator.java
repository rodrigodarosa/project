package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.Sale;

public class SaleValidator {

	public static boolean validate(Sale entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigat\u00F3rio";
		String detail2 = "N\u00E3o Encontrado";

		if (entity.getDescription() == null || entity.getDescription().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descri\u00E7\u00E3o", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getCustomer() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cliente", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getManufacturedProducts() == null || entity.getManufacturedProducts().isEmpty()) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Produtos Manufaturados", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getSeller() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Vendedor", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return valid;
	}

}

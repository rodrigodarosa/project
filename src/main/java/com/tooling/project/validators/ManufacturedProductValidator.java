package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.ManufacturedProduct;

public class ManufacturedProductValidator {

	public static boolean validate(ManufacturedProduct entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigat\u00F3rio";

		if (entity.getName() == null || entity.getDescription().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDescription() == null || entity.getDescription().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descri\u00E7\u00E3o", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getSituation() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Situa\u00E7\u00E3o", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return valid;
	}

}

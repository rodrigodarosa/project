package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.Customer;

public class CustomerValidator {

	public static boolean validate(Customer entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigat\u00F3rio";

		if (entity.getName() == null || entity.getName().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getAddress() == null || entity.getAddress().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Endere\u00E7o", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getTelephone() == null || entity.getTelephone().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefone", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getEmail() == null || entity.getEmail().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return valid;
	}

}

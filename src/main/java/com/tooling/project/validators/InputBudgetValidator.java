package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.InputBudget;

public class InputBudgetValidator {

	public static boolean validate(InputBudget entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigat\u00F3rio";
		String detail2 = "N\u00E3o Encontrado";

		if (entity.getCustomer() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cliente", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDescription() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descri\u00E7\u00E3o", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDeliveryDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de Entrega", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		if (entity.getTechnicalSpecification() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Especifica\u00E7\u00E3o T\u00E9cnica", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getQuantity() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quantidade", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return valid;
	}

}

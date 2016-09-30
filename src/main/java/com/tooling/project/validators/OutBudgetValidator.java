package com.tooling.project.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.OutputBudget;

public class OutBudgetValidator {

	public static boolean validate(OutputBudget entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigat\u00F3rio";
		String detail2 = "N\u00E3o encontrado";
		String detail3 = "Data de entrega deve ser maior que data atual";

		if (entity.getProvider() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fornecedor", detail2);
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
		if (entity.getDeliveryDate() != null && entity.getDeliveryDate().before(new Date())) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inconsist\u00EAncia", detail3);
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

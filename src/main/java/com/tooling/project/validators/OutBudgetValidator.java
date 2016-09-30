package com.tooling.project.validators;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.OutputBudget;

public class OutBudgetValidator {

	public static boolean validate(OutputBudget entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigatório";
		String detail2 = "Não encontrado";
		String detail3 = "Data de entrega deve ser maior que data atual";

		if (entity.getProvider() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fornecedor", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDescription() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descrição", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDeliveryDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de Entrega", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDeliveryDate() != null && entity.getDeliveryDate().before(new Date())) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inconsistência", detail3);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		if (entity.getTechnicalSpecification() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Especificação Técnica", detail);
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

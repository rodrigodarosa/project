package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.Purchase;

public class PurchaseValidator {

	public static boolean validate(Purchase entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigatório";
		String detail2 = "Não Encontrado";

		if (entity.getDescription() == null || entity.getDescription().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descrição", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getProvider() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fornecedor", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getProducts() == null || entity.getProducts().isEmpty()) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Produtos", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getBuyer() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Comprador", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return valid;
	}

}

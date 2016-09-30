package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.Payable;

public class PayableValidator {

	public static boolean validate(Payable entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigatório";
		String detail2 = "Não encontrado";

		if (entity.getProvider() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fornecedor", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDocumentNumber() == 0 || entity.getDocumentNumber() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nº do Documento", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		if (entity.getPurchase() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nº da Compra", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		if (entity.getDocumentDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data do Documento", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getExpirationDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de Vencimento", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getPaymentType() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo de Pagamento", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDischargeSituation() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Situação", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getTotalValue() == 0.0) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Total", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);

		}
		return valid;
	}

}

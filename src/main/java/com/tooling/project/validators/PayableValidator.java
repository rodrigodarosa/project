package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.Payable;

public class PayableValidator {

	public static boolean validate(Payable entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigat\u00F3rio";
		String detail2 = "N\u00E3o encontrado";

		if (entity.getProvider() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fornecedor", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getDocumentNumber() == 0 || entity.getDocumentNumber() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "N\u00BA do Documento", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		if (entity.getPurchase() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "N\u00BA da Compra", detail);
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
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Situa\u00E7\u00E3o", detail);
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

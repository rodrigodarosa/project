package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.ServiceOrder;

public class ServiceOrderValidator {

	public static boolean validate(ServiceOrder entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigat�rio";
		String detail2 = "N�o encontrado";
		String detail3 = "Data final deve ser maior que data inicial";

		if (entity.getDescription() == null || entity.getDescription().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descri��o", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getStartDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data In�cio", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getEndDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Final", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if(entity.getEndDate().before(entity.getStartDate())){
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inconsist�ncia", detail3);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getEmployee() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Funcion�rio", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getMachine() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "M�quina", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getManufacturedProduct() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Produto Manufaturado", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return valid;
	}

}

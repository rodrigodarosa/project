package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.Notation;

public class NotationValidator {

	public static boolean validate(Notation entity, boolean isUpdate) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigat\u00F3rio";
		String detail3 = "Data inicial est\u00E1 inferior a final";

		if (entity.getEmployee() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Funcion\u00E1rio", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getServiceOrder() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ordem de Servi\u00E7o", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getStartDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de In\u00EDcio", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getEndDate() == null && !isUpdate) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de T\u00E9rmino", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		if(entity.getStartDate() != null && entity.getEndDate() != null && entity.getStartDate().after(entity.getEndDate())){
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inconsist\u00EAncia de Per\u00EDodo", detail3 );
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return valid;
	}

}

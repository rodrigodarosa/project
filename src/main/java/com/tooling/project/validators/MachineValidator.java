package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.Machine;

public class MachineValidator {

	public static boolean validate(Machine entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigatório";

		if (entity.getName() == null || entity.getName().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getSituation() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Situação", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return valid;
	}

}

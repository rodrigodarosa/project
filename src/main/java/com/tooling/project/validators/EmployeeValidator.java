package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.Employee;

public class EmployeeValidator {

	
	public static boolean validate(Employee entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigat\u00F3rio";
		
		if (entity.getName() == null || entity.getName().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getCpf()== null || entity.getCpf().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getAddress() == null || entity.getAddress().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Endere\u00E7o", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getCep() == null || entity.getCep().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CEP", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getSex() == null || entity.getSex().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sexo", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getTelephone() == null || entity.getTelephone().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Telefone", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getProfile() == null || entity.getProfile().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Perfil", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return valid;
	}

}

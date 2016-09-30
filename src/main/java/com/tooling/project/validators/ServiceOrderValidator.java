package com.tooling.project.validators;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tooling.project.model.ServiceOrder;

public class ServiceOrderValidator {

	public static boolean validate(ServiceOrder entity) {
		boolean valid = true;
		FacesMessage message = null;
		String detail = "Campo Obrigatório";
		String detail2 = "Não encontrado";
		String detail3 = "Data final deve ser maior que data inicial";

		if (entity.getDescription() == null || entity.getDescription().equals("")) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Descrição", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getStartDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Início", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getEndDate() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Final", detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if(entity.getEndDate().before(entity.getStartDate())){
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inconsistência", detail3);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getEmployee() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Funcionário", detail2);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (entity.getMachine() == null) {
			valid = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Máquina", detail2);
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

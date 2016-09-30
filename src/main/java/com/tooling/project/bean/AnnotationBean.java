package com.tooling.project.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.tooling.project.facade.AnnotationFacade;
import com.tooling.project.facade.EmployeeFacade;
import com.tooling.project.model.Annotation;

@ManagedBean
@ViewScoped
public class AnnotationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Annotation entity;
	private AnnotationFacade annotationFacade;
	private EmployeeFacade employeeFacade;

	public Annotation getEntity() {
		if (entity == null) {
			entity = new Annotation();
		}
		return entity;
	}

	public void setEntity(Annotation entity) {
		this.entity = entity;
	}

	public AnnotationFacade getAnnotationFacade() {
		if (annotationFacade == null) {
			annotationFacade = new AnnotationFacade();
		}
		return annotationFacade;
	}

	public void setAnnotationFacade(AnnotationFacade annotationFacade) {
		this.annotationFacade = annotationFacade;
	}
	
	public EmployeeFacade getEmployeeFacade() {
		if(employeeFacade == null){
			employeeFacade = new EmployeeFacade();
		}
		return employeeFacade;
	}

	public void setEmployeeFacade(EmployeeFacade employeeFacade) {
		this.employeeFacade = employeeFacade;
	}

	public void save() {
		if(entity.getId() != null){
			getAnnotationFacade().update(entity);
		}else{
			entity.setEmployee(getEmployeeFacade().getCurrentLogged());
			getAnnotationFacade().save(entity);
		}
		entity = new Annotation();
	}

	public String remove() {
		getAnnotationFacade().remove(entity);
		entity = new Annotation();
		return "";

	}

	public Annotation retrieve(Long id) {
		return getAnnotationFacade().retrieve(id);
	}

/*	public void update() {
		getAnnotationFacade().update(entity);
		entity = new Annotation();

	}*/
	
	public String cancel(){
		entity= new Annotation();
		return "";
	}

	public List<Annotation> getAll() {
		return getAnnotationFacade().getAll();
	}
	
	public List<Annotation> getLastThree(){
		return getAnnotationFacade().getLastTthree(getEmployeeFacade().getCurrentLogged());
	}
	
}

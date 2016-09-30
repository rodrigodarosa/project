package com.tooling.project.facade;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.EmployeeBusiness;
import com.tooling.project.enums.ProfileEnum;
import com.tooling.project.model.Employee;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class EmployeeFacade implements Business<Employee> {

	private static final long serialVersionUID = 7585932597571221758L;

	private EmployeeBusiness employeeBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public EmployeeBusiness getEmployeeBusiness() {
		if (employeeBusiness == null) {
			employeeBusiness = new EmployeeBusiness(getSession(), Employee.class);
		}
		return employeeBusiness;
	}

	public void setEmployeeBusiness(EmployeeBusiness employeeBusiness) {
		this.employeeBusiness = employeeBusiness;
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateService.getSessionFactory();
		}
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		if (session == null) {
			session = getSessionFactory().openSession();
		} 
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transaction getTransaction() {
		transaction = getSession().beginTransaction();
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public Employee doLogin(String username, String password) {

		Employee employee = getEmployeeBusiness().doLogin(username, password);
		if (employee != null) {
			HttpSession secs = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
			secs.setAttribute("logged", username);
		}

		return employee;
	}
	
	public void doLogoff() {

		HttpSession secs = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		secs.removeAttribute("logged");
	}
	
	public Employee getCurrentLogged() {

		HttpSession secs = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String userName = (String) secs.getAttribute("logged");
		Employee employee = this.getEmployeeBusiness().retrieveByUsername(userName);

		return employee;
	}
	

	@Override
	public void save(Employee entity) {
		getEmployeeBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Employee entity) {
		getEmployeeBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Employee retrieve(Long id) {
		Employee employee = getEmployeeBusiness().retrieve(id);
		getTransaction().commit();

		return employee;
	}

	@Override
	public void update(Employee entity) {
		getEmployeeBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> employees = getEmployeeBusiness().getAll();
		return employees;
	}

	public List<Employee> retrieveByName(String name){
		List<Employee> employees = getEmployeeBusiness().retrieveByName(name);
		return employees;
	}
	
	public Employee retrieveByExactName(String name){
		Employee employee = getEmployeeBusiness().retrieveByExactName(name);
		return employee;
	}

	public List<Employee> retrieveByProfile(ProfileEnum profile) {
		return getEmployeeBusiness().retrieveByProfile(profile);
	}

	public boolean isExistsLogin(Employee employee) {
		return getEmployeeBusiness().isExistsLogin(employee);
	}

}

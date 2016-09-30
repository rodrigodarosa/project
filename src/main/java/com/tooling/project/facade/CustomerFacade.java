package com.tooling.project.facade;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.CustomerBusiness;
import com.tooling.project.model.Customer;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class CustomerFacade implements Business<Customer> {

	private static final long serialVersionUID = 7585932597571221758L;

	private CustomerBusiness customerBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public CustomerBusiness getCustomerBusiness() {
		if (customerBusiness == null) {
			customerBusiness = new CustomerBusiness(getSession(), Customer.class);
		}
		return customerBusiness;
	}

	public void setCustomerBusiness(CustomerBusiness customerBusiness) {
		this.customerBusiness = customerBusiness;
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

	@Override
	public void save(Customer entity) {
		getCustomerBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Customer entity) {
		getCustomerBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Customer retrieve(Long id) {
		Customer customer = getCustomerBusiness().retrieve(id);
		getTransaction().commit();

		return customer;
	}

	@Override
	public void update(Customer entity) {
		getCustomerBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> customers = getCustomerBusiness().getAll();
		return customers;
	}

	public List<Customer> retrieveByName(String name){
		List<Customer> providers = getCustomerBusiness().retrieveByName(name);
		return providers;
	}
	
	public Customer retrieveByExactName(String name){
		Customer customer = getCustomerBusiness().retrieveByExactName(name);
		return customer;
	}
	
	public Customer doLogin(String username, String password) {

		Customer customer = getCustomerBusiness().doLogin(username, password);
		if (customer != null) {
			HttpSession secs = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false);
			secs.setAttribute("logged", username);
		}

		return customer;
	}
	
	public void doLogoff() {

		HttpSession secs = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		secs.removeAttribute("logged");
	}
	
	public Customer getCurrentLogged() {

		HttpSession secs = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String userName = (String) secs.getAttribute("logged");
		Customer customer = this.getCustomerBusiness().retrieveByUsername(userName);

		return customer;
	}

	public boolean isExistsLogin(Customer customer) {
		return getCustomerBusiness().isExistsLogin(customer);
	}

}

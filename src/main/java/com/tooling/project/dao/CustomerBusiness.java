package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.Customer;
import com.tooling.project.util.DAO;

public class CustomerBusiness extends DAO<Customer> {

	public CustomerBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<Customer> retrieveByName(String name) {
		Criteria c = getSession().createCriteria(Customer.class);
		c.add(Restrictions.ilike("name", "%" + name + "%"));
		return c.list();

	}

	public Customer retrieveByExactName(String name) {
		Criteria c = getSession().createCriteria(Customer.class);
		c.add(Restrictions.eq("name", name));
		return (Customer) c.uniqueResult();

	}
	
	public Customer doLogin(String username, String password) {
		Criteria c = getSession().createCriteria(Customer.class);
		c.add(Restrictions.eq("username", username));
		c.add(Restrictions.eq("password", password));
		return (Customer)c.uniqueResult();
	}
	
	public Customer retrieveByUsername(String username) {
		Criteria c = getSession().createCriteria(Customer.class);
		c.add(Restrictions.eq("username", username));
		return (Customer)c.uniqueResult();
	}

	public boolean isExistsLogin(Customer customer) {
		Criteria c = getSession().createCriteria(Customer.class);
		c.add(Restrictions.eq("username", customer.getUsername()));
		if(customer.getId() != null){
			c.add(Restrictions.not(Restrictions.eq("id", customer.getId())));
		}
		
		return (Customer)c.uniqueResult() != null;
	}
	
}
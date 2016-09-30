package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.enums.ProfileEnum;
import com.tooling.project.model.Employee;
import com.tooling.project.util.DAO;

public class EmployeeBusiness extends DAO<Employee> {

	public EmployeeBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	public Employee doLogin(String username, String password) {
		Criteria c = getSession().createCriteria(Employee.class);
		c.add(Restrictions.eq("username", username));
		c.add(Restrictions.eq("password", password));
		return (Employee) c.uniqueResult();
	}

	public Employee retrieveByUsername(String username) {
		Criteria c = getSession().createCriteria(Employee.class);
		c.add(Restrictions.eq("username", username));
		return (Employee) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> retrieveByName(String name) {
		Criteria c = getSession().createCriteria(Employee.class);
		c.add(Restrictions.ilike("name", "%" + name + "%"));
		return c.list();

	}

	public Employee retrieveByExactName(String name) {
		Criteria c = getSession().createCriteria(Employee.class);
		c.add(Restrictions.eq("name", name));
		return (Employee) c.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<Employee> retrieveByProfile(ProfileEnum profile) {
		Criteria c = getSession().createCriteria(Employee.class);
		c.add(Restrictions.eq("profile", profile));
		return c.list();
	}

	public boolean isExistsLogin(Employee employee) {
		Criteria c = getSession().createCriteria(Employee.class);
		c.add(Restrictions.eq("username", employee.getUsername()));
		if(employee.getId() != null){
			c.add(Restrictions.not(Restrictions.eq("id", employee.getId())));
		}
		return (Employee) c.uniqueResult() != null;
	}

}
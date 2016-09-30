package com.tooling.project.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.Sale;
import com.tooling.project.util.DAO;

public class SaleBusiness extends DAO<Sale> {

	public SaleBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@Override
	public Sale retrieve(Long id) {
		Criteria c = getSession().createCriteria(Sale.class);
		c.add(Restrictions.eq("id", id));
		return (Sale) c.uniqueResult();
		
	}
	
}
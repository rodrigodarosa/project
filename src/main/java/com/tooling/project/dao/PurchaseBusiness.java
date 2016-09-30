package com.tooling.project.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.Purchase;
import com.tooling.project.util.DAO;

public class PurchaseBusiness extends DAO<Purchase> {

	public PurchaseBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@Override
	public Purchase retrieve(Long id) {
		Criteria c = getSession().createCriteria(Purchase.class);
		c.add(Restrictions.eq("id", id));
		return (Purchase) c.uniqueResult();

	}

}
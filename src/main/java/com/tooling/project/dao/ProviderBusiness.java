package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.Provider;
import com.tooling.project.util.DAO;

public class ProviderBusiness extends DAO<Provider> {

	public ProviderBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<Provider> retrieveByName(String name) {
			Criteria c = getSession().createCriteria(Provider.class);
			c.add(Restrictions.ilike("name", "%"+name+"%"));
			return c.list();

	}
	
	public Provider retrieveByExactName(String name) {
		Criteria c = getSession().createCriteria(Provider.class);
		c.add(Restrictions.eq("name", name));
		return (Provider) c.uniqueResult();
		
	}

}
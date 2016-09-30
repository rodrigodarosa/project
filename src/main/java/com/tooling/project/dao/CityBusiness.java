package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.City;
import com.tooling.project.model.State;
import com.tooling.project.util.DAO;

public class CityBusiness extends DAO<City> {

	public CityBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<City> retrieveByState(State state) {
		Criteria c = getSession().createCriteria(City.class);
		c.add(Restrictions.eq("state", state));
		return (List<City>) c.list();
	}

	public City retrieveByExactName(String name) {
		Criteria c = getSession().createCriteria(City.class);
		c.add(Restrictions.eq("name", name));
		return (City) c.uniqueResult();

	}

}
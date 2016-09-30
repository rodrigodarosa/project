package com.tooling.project.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.State;
import com.tooling.project.util.DAO;

public class StateBusiness extends DAO<State> {

	public StateBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	public State retrieveByExactName(String name) {
		Criteria c = getSession().createCriteria(State.class);
		c.add(Restrictions.eq("name", name));
		return (State) c.uniqueResult();

	}

}
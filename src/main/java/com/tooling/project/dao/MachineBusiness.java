package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.enums.MachineSituationEnum;
import com.tooling.project.model.Machine;
import com.tooling.project.util.DAO;

public class MachineBusiness extends DAO<Machine> {

	public MachineBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<Machine> retrieveByName(String name) {
		Criteria c = getSession().createCriteria(Machine.class);
		c.add(Restrictions.ilike("name", "%" + name + "%"));
		return c.list();

	}

	public Machine retrieveByExactName(String name) {
		Criteria c = getSession().createCriteria(Machine.class);
		c.add(Restrictions.eq("name", name));
		return (Machine) c.uniqueResult();

	}

	public int countBySituation(MachineSituationEnum situation) {
		Criteria criteria = getSession().createCriteria(Machine.class);
		criteria.add(Restrictions.eq("situation", situation));
		criteria.setProjection(Projections.rowCount());

		return ((Long) criteria.uniqueResult()).intValue();

	}

}
package com.tooling.project.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.Employee;
import com.tooling.project.model.Theme;
import com.tooling.project.util.DAO;

public class ThemeBusiness extends DAO<Theme> {

	public ThemeBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	public Theme retrieveByEmployee(Employee employee) {

		Criteria criteria = getSession().createCriteria(Theme.class);
		criteria.add(Restrictions.eq("employee", employee));
		Theme theme = ((Theme) criteria.uniqueResult());
		return theme;
	}

}
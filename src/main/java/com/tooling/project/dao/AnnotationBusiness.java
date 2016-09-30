package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.Annotation;
import com.tooling.project.model.Employee;
import com.tooling.project.util.DAO;

public class AnnotationBusiness extends DAO<Annotation> {

	public AnnotationBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<Annotation> getLastThree(Employee employee) {
		Criteria criteria = getSession().createCriteria(Annotation.class);
		criteria.add(Restrictions.eq("employee",employee));
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(3);
		return criteria.list() ;
	}

}
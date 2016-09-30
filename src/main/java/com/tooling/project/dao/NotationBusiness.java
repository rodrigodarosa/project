package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.enums.PauseReasonNotationEnum;
import com.tooling.project.model.Employee;
import com.tooling.project.model.Notation;
import com.tooling.project.model.ServiceOrder;
import com.tooling.project.util.DAO;

public class NotationBusiness extends DAO<Notation> {

	public NotationBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<Notation> retrieveByEmployee(Employee employee) {
		Criteria criteria = getSession().createCriteria(Notation.class);
		criteria.add(Restrictions.eq("employee", employee));
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public Notation retrieveLastNotationByEmployeeAndServiceOrder(Employee employee, ServiceOrder serviceOrder) {
		Criteria criteria = getSession().createCriteria(Notation.class);
		criteria.add(Restrictions.eq("employee", employee));
		criteria.add(Restrictions.eq("serviceOrder", serviceOrder));
		criteria.addOrder(Order.asc("startDate"));
		List<Notation> list = criteria.list();
		if (list.size() > 0) {
			return list.get(list.size() - 1);
		}
		return null;
	}

	public int countBySituation(PauseReasonNotationEnum situation) {
		Criteria criteria = getSession().createCriteria(Notation.class);
		criteria.add(Restrictions.eq("pauseReason", situation));
		criteria.setProjection(Projections.rowCount());

		return ((Long) criteria.uniqueResult()).intValue();

	}

	@SuppressWarnings("unchecked")
	public List<Notation> retrieveByServiceOrder(ServiceOrder serviceOrder) {
		Criteria criteria = getSession().createCriteria(Notation.class);
		criteria.add(Restrictions.eq("serviceOrder", serviceOrder));
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}

}
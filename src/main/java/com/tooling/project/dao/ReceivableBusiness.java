package com.tooling.project.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.enums.DischargeSituationEnum;
import com.tooling.project.model.Receivable;
import com.tooling.project.util.DAO;

public class ReceivableBusiness extends DAO<Receivable> {

	public ReceivableBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}
	
	public int getCountBySituation(DischargeSituationEnum situation) {
		Criteria criteria = getSession().createCriteria(Receivable.class);
		criteria.add(Restrictions.eq("dischargeSituation", situation));
		criteria.setProjection(Projections.rowCount());
		
		return ((Long) criteria.uniqueResult()).intValue();
		
	}

	
}
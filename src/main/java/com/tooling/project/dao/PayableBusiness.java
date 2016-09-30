package com.tooling.project.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.enums.DischargeSituationEnum;
import com.tooling.project.model.Payable;
import com.tooling.project.util.DAO;

public class PayableBusiness extends DAO<Payable> {

	public PayableBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}
	
	public int getCountBySituation(DischargeSituationEnum situation) {
		Criteria criteria = getSession().createCriteria(Payable.class);
		criteria.add(Restrictions.eq("dischargeSituation", situation));
		criteria.setProjection(Projections.rowCount());
		
		return ((Long) criteria.uniqueResult()).intValue();
		
	}

}
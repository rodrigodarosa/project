package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.enums.InputBudgetSituationEnum;
import com.tooling.project.model.Customer;
import com.tooling.project.model.InputBudget;
import com.tooling.project.util.DAO;

public class InputBudgetBusiness extends DAO<InputBudget> {

	public InputBudgetBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<InputBudget> retrieveBySituationAndCustomer(InputBudgetSituationEnum situation, Customer customer) {

		Criteria criteria = getSession().createCriteria(InputBudget.class);
		criteria.add(Restrictions.eq("situation", situation));
		criteria.add(Restrictions.eq("customer", customer));
		criteria.addOrder(Order.desc("id"));
		return (List<InputBudget>) criteria.list();
	}
	
	public int getCountBySituation(InputBudgetSituationEnum situation) {
		Criteria criteria = getSession().createCriteria(InputBudget.class);
		criteria.add(Restrictions.eq("situation", situation));
		criteria.setProjection(Projections.rowCount());
		
		return ((Long) criteria.uniqueResult()).intValue();
		
	}

}
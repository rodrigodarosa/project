package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.enums.ServiceOrderSituationEnum;
import com.tooling.project.model.Employee;
import com.tooling.project.model.ServiceOrder;
import com.tooling.project.util.DAO;

public class ServiceOrderBusiness extends DAO<ServiceOrder> {

	public ServiceOrderBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	public ServiceOrder getActuallyExecuting(Employee employee) {
			Criteria criteria = getSession().createCriteria(ServiceOrder.class);
			criteria.add(Restrictions.eq("situation", ServiceOrderSituationEnum.ASSEMBLING));
			criteria.add(Restrictions.eq("employee", employee));
			criteria.addOrder(Order.desc("id"));
		return (ServiceOrder) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<ServiceOrder> getServiceOrderBySituation(Employee employee, ServiceOrderSituationEnum situation) {
		Criteria criteria = getSession().createCriteria(ServiceOrder.class);
		criteria.add(Restrictions.eq("situation", situation));
		criteria.add(Restrictions.eq("employee", employee));
		criteria.addOrder(Order.desc("id"));
		if(criteria.list().isEmpty()){
			return null;
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<ServiceOrder> getAllByEmployee(Employee employee) {
		Criteria criteria = getSession().createCriteria(ServiceOrder.class);
		criteria.add(Restrictions.eq("employee", employee));
		criteria.add(Restrictions.in("situation", new Object[]{ServiceOrderSituationEnum.FINALIZED, ServiceOrderSituationEnum.NOT_INITIATED, ServiceOrderSituationEnum.STAND_BY}));
		criteria.addOrder(Order.desc("id"));
		if(criteria.list().isEmpty()){
			return null;
		}
		return criteria.list();
	}
	
	public int getCountBySituation(ServiceOrderSituationEnum situation) {
		Criteria criteria = getSession().createCriteria(ServiceOrder.class);
		criteria.add(Restrictions.eq("situation", situation));
		criteria.setProjection(Projections.rowCount());
		
		return ((Long) criteria.uniqueResult()).intValue();
		
	}

}
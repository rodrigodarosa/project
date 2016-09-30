package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.ServiceOrderBusiness;
import com.tooling.project.enums.ServiceOrderSituationEnum;
import com.tooling.project.model.Employee;
import com.tooling.project.model.ServiceOrder;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class ServiceOrderFacade implements Business<ServiceOrder> {

	private static final long serialVersionUID = 7585932597571221758L;

	private ServiceOrderBusiness serviceOrderBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public ServiceOrderBusiness getServiceOrderBusiness() {
		if (serviceOrderBusiness == null) {
			serviceOrderBusiness = new ServiceOrderBusiness(getSession(), ServiceOrder.class);
		}
		return serviceOrderBusiness;
	}

	public void setServiceOrderBusiness(ServiceOrderBusiness serviceOrderBusiness) {
		this.serviceOrderBusiness = serviceOrderBusiness;
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateService.getSessionFactory();
		}
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		if (session == null) {
			session = getSessionFactory().openSession();
		}
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transaction getTransaction() {
		transaction = getSession().beginTransaction();
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public void save(ServiceOrder entity) {
		getServiceOrderBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(ServiceOrder entity) {
		getServiceOrderBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public ServiceOrder retrieve(Long id) {
		ServiceOrder serviceOrder = getServiceOrderBusiness().retrieve(id);
		getTransaction().commit();

		return serviceOrder;
	}

	@Override
	public void update(ServiceOrder entity) {
		getServiceOrderBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<ServiceOrder> getAll() {
		List<ServiceOrder> serviceOrders = getServiceOrderBusiness().getAll();
		return serviceOrders;
	}

	public ServiceOrder getActuallyExecuting(Employee employee) {
		ServiceOrder serviceOrder = getServiceOrderBusiness().getActuallyExecuting(employee);
		return serviceOrder;
		
	}

	public List<ServiceOrder> getServiceOrderBySituation(Employee employee, ServiceOrderSituationEnum situation) {
		List<ServiceOrder> serviceOrders = getServiceOrderBusiness().getServiceOrderBySituation(employee, situation);
		return serviceOrders;
	}
	public List<ServiceOrder> getAllByEmployee(Employee employee) {
		List<ServiceOrder> serviceOrders = getServiceOrderBusiness().getAllByEmployee(employee);
		return serviceOrders;
	}

	public int getCountBySituation(ServiceOrderSituationEnum situation) {
		return getServiceOrderBusiness().getCountBySituation(situation);
	}

}

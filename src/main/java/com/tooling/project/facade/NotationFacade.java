package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.NotationBusiness;
import com.tooling.project.enums.PauseReasonNotationEnum;
import com.tooling.project.model.Employee;
import com.tooling.project.model.Notation;
import com.tooling.project.model.ServiceOrder;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class NotationFacade implements Business<Notation> {

	private static final long serialVersionUID = 7585932597571221758L;

	private NotationBusiness notationBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public NotationBusiness getNotationBusiness() {
		if (notationBusiness == null) {
			notationBusiness = new NotationBusiness(getSession(), Notation.class);
		}
		return notationBusiness;
	}

	public void setNotationBusiness(NotationBusiness notationBusiness) {
		this.notationBusiness = notationBusiness;
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
	public void save(Notation entity) {
		getNotationBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Notation entity) {
		getNotationBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Notation retrieve(Long id) {
		Notation notation = getNotationBusiness().retrieve(id);
		getTransaction().commit();

		return notation;
	}

	@Override
	public void update(Notation entity) {
		getNotationBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Notation> getAll() {
		List<Notation> notations = getNotationBusiness().getAll();
		return notations;
	}
	
	public List<Notation> retrieveByEmployee(Employee employee) {
		List<Notation> notations = getNotationBusiness().retrieveByEmployee(employee);
		return notations;
	}

	public Notation retrieveLastNotationByEmployeeAndServiceOrder(Employee employee, ServiceOrder serviceOrder) {
		Notation notation  = getNotationBusiness().retrieveLastNotationByEmployeeAndServiceOrder(employee, serviceOrder);
		return notation;
	}

	public int countBySituation(PauseReasonNotationEnum situation) {
		return getNotationBusiness().countBySituation(situation);
	}

	public List<Notation> retrieveByServiceOrder(ServiceOrder serviceOrder) {
		return getNotationBusiness().retrieveByServiceOrder(serviceOrder);
	}
	

}

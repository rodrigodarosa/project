package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.StateBusiness;
import com.tooling.project.model.State;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class StateFacade implements Business<State> {

	private static final long serialVersionUID = 7585932597571221758L;

	private StateBusiness stateBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public StateBusiness getStateBusiness() {
		if (stateBusiness == null) {
			stateBusiness = new StateBusiness(getSession(), State.class);
		}
		return stateBusiness;
	}

	public void setStateBusiness(StateBusiness stateBusiness) {
		this.stateBusiness = stateBusiness;
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
	public void save(State entity) {
		getStateBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(State entity) {
		getStateBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public State retrieve(Long id) {
		State state = getStateBusiness().retrieve(id);
		getTransaction().commit();

		return state;
	}

	@Override
	public void update(State entity) {
		getStateBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<State> getAll() {
		List<State> states = getStateBusiness().getAll();
		return states;
	}
	
	public State retrieveByExactName(String name){
		return getStateBusiness().retrieveByExactName(name);
	}

}

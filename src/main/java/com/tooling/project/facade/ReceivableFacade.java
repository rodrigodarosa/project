package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.ReceivableBusiness;
import com.tooling.project.enums.DischargeSituationEnum;
import com.tooling.project.model.Receivable;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class ReceivableFacade implements Business<Receivable> {

	private static final long serialVersionUID = 7585932597571221758L;

	private ReceivableBusiness receivableBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public ReceivableBusiness getReceivableBusiness() {
		if (receivableBusiness == null) {
			receivableBusiness = new ReceivableBusiness(getSession(), Receivable.class);
		}
		return receivableBusiness;
	}

	public void setReceivableBusiness(ReceivableBusiness receivableBusiness) {
		this.receivableBusiness = receivableBusiness;
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
	public void save(Receivable entity) {
		getReceivableBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Receivable entity) {
		getReceivableBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Receivable retrieve(Long id) {
		Receivable receivable = getReceivableBusiness().retrieve(id);
		getTransaction().commit();

		return receivable;
	}

	@Override
	public void update(Receivable entity) {
		getReceivableBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Receivable> getAll() {
		List<Receivable> receivables = getReceivableBusiness().getAll();
		return receivables;
	}
	
	public int getCountBySituation(DischargeSituationEnum situation){
		return getReceivableBusiness().getCountBySituation(situation);
	}

}

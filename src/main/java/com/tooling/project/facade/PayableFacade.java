package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.PayableBusiness;
import com.tooling.project.enums.DischargeSituationEnum;
import com.tooling.project.model.Payable;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class PayableFacade implements Business<Payable> {

	private static final long serialVersionUID = 7585932597571221758L;

	private PayableBusiness payableBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public PayableBusiness getPayableBusiness() {
		if (payableBusiness == null) {
			payableBusiness = new PayableBusiness(getSession(), Payable.class);
		}
		return payableBusiness;
	}

	public void setpayableBusiness(PayableBusiness payableBusiness) {
		this.payableBusiness = payableBusiness;
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
	public void save(Payable entity) {
		getPayableBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Payable entity) {
		getPayableBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Payable retrieve(Long id) {
		Payable payable = getPayableBusiness().retrieve(id);

		return payable;
	}

	@Override
	public void update(Payable entity) {
		getPayableBusiness().update(entity);
	}

	@Override
	public List<Payable> getAll() {
		List<Payable> payables = getPayableBusiness().getAll();
		return payables;
	}
	
	public int getCountBySituation(DischargeSituationEnum situation){
		return getPayableBusiness().getCountBySituation(situation);
	}

}

package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.PurchaseBusiness;
import com.tooling.project.model.Purchase;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class PurchaseFacade implements Business<Purchase> {

	private static final long serialVersionUID = 7585932597571221758L;

	private PurchaseBusiness purchaseBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public PurchaseBusiness getPurchaseBusiness() {
		if (purchaseBusiness == null) {
			purchaseBusiness = new PurchaseBusiness(getSession(), Purchase.class);
		}
		return purchaseBusiness;
	}

	public void setPurchaseBusiness(PurchaseBusiness purchaseBusiness) {
		this.purchaseBusiness = purchaseBusiness;
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
	public void save(Purchase entity) {
		getPurchaseBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Purchase entity) {
		getPurchaseBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Purchase retrieve(Long id) {
		Purchase purchase = getPurchaseBusiness().retrieve(id);
		return purchase;
	}

	@Override
	public void update(Purchase entity) {
		getPurchaseBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Purchase> getAll() {
		List<Purchase> purchases = getPurchaseBusiness().getAll();
		return purchases;
	}

}

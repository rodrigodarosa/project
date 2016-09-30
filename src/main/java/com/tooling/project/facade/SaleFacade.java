package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.SaleBusiness;
import com.tooling.project.model.Sale;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class SaleFacade implements Business<Sale> {

	private static final long serialVersionUID = 7585932597571221758L;

	private SaleBusiness saleBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public SaleBusiness getSaleBusiness() {
		if (saleBusiness == null) {
			saleBusiness = new SaleBusiness(getSession(), Sale.class);
		}
		return saleBusiness;
	}

	public void setSaleBusiness(SaleBusiness saleBusiness) {
		this.saleBusiness = saleBusiness;
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
	public void save(Sale entity) {
		getSaleBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Sale entity) {
		getSaleBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Sale retrieve(Long id) {
		Sale sale = getSaleBusiness().retrieve(id);
		//getTransaction().commit();

		return sale;
	}

	@Override
	public void update(Sale entity) {
		getSaleBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Sale> getAll() {
		List<Sale> sales = getSaleBusiness().getAll();
		return sales;
	}

}

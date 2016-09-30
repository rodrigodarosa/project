package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.OutputBudgetBusiness;
import com.tooling.project.model.OutputBudget;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class OutputBudgetFacade implements Business<OutputBudget> {

	private static final long serialVersionUID = 7585932597571221758L;

	private OutputBudgetBusiness outputBudgetBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public OutputBudgetBusiness getBudgetBusiness() {
		if (outputBudgetBusiness == null) {
			outputBudgetBusiness = new OutputBudgetBusiness(getSession(), OutputBudget.class);
		}
		return outputBudgetBusiness;
	}

	public void setBudgetBusiness(OutputBudgetBusiness outputBudgetBusiness) {
		this.outputBudgetBusiness = outputBudgetBusiness;
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
	public void save(OutputBudget entity) {
		getBudgetBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(OutputBudget entity) {
		getBudgetBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public OutputBudget retrieve(Long id) {
		OutputBudget outputBudget = getBudgetBusiness().retrieve(id);
		getTransaction().commit();

		return outputBudget;
	}

	@Override
	public void update(OutputBudget entity) {
		getBudgetBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<OutputBudget> getAll() {
		List<OutputBudget> outputBudgets = getBudgetBusiness().getAll();
		return outputBudgets;
	}

}

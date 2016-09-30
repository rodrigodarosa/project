package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.InputBudgetBusiness;
import com.tooling.project.enums.InputBudgetSituationEnum;
import com.tooling.project.model.Customer;
import com.tooling.project.model.InputBudget;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class InputBudgetFacade implements Business<InputBudget> {

	private static final long serialVersionUID = 7585932597571221758L;

	private InputBudgetBusiness inputBudgetBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public InputBudgetBusiness getInputBudgetBusiness() {
		if (inputBudgetBusiness == null) {
			inputBudgetBusiness = new InputBudgetBusiness(getSession(), InputBudget.class);
		}
		return inputBudgetBusiness;
	}

	public void setBudgetBusiness(InputBudgetBusiness inputBudgetBusiness) {
		this.inputBudgetBusiness = inputBudgetBusiness;
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
	public void save(InputBudget entity) {
		getInputBudgetBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(InputBudget entity) {
		getInputBudgetBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public InputBudget retrieve(Long id) {
		InputBudget user = getInputBudgetBusiness().retrieve(id);
		getTransaction().commit();

		return user;
	}

	@Override
	public void update(InputBudget entity) {
		getInputBudgetBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<InputBudget> getAll() {
		List<InputBudget> users = getInputBudgetBusiness().getAll();
		return users;
	}

	public List<InputBudget> retrieveBySituationAndCustomer(InputBudgetSituationEnum situation, Customer customer) {
		return getInputBudgetBusiness().retrieveBySituationAndCustomer(situation, customer);
	}
	
	public int getCountBySituation(InputBudgetSituationEnum situation){
		return getInputBudgetBusiness().getCountBySituation(situation);
	}

}

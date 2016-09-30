package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.MachineBusiness;
import com.tooling.project.enums.MachineSituationEnum;
import com.tooling.project.model.Machine;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class MachineFacade implements Business<Machine> {

	private static final long serialVersionUID = 7585932597571221758L;

	private MachineBusiness machineBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public MachineBusiness getMachineBusiness() {
		if (machineBusiness == null) {
			machineBusiness = new MachineBusiness(getSession(), Machine.class);
		}
		return machineBusiness;
	}

	public void setMachineBusiness(MachineBusiness machineBusiness) {
		this.machineBusiness = machineBusiness;
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
	public void save(Machine entity) {
		getMachineBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Machine entity) {
		getMachineBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Machine retrieve(Long id) {
		Machine machine = getMachineBusiness().retrieve(id);
		

		return machine;
	}

	@Override
	public void update(Machine entity) {
		getMachineBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Machine> getAll() {
		List<Machine> machines = getMachineBusiness().getAll();
		return machines;
	}

	public List<Machine> retrieveByName(String complete) {
		List<Machine> machines = getMachineBusiness().retrieveByName(complete);
		return machines;
	}

	public Machine retrieveByExactName(String machineName) {
		Machine machine = getMachineBusiness().retrieveByExactName(machineName);
		return machine;
	}

	public int countBySituation(MachineSituationEnum situation) {
		return getMachineBusiness().countBySituation(situation);
	}

}

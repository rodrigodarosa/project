package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.ProviderBusiness;
import com.tooling.project.model.Provider;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class ProviderFacade implements Business<Provider> {

	private static final long serialVersionUID = 7585932597571221758L;

	private ProviderBusiness providerBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public ProviderBusiness getProviderBusiness() {
		if (providerBusiness == null) {
			providerBusiness = new ProviderBusiness(getSession(), Provider.class);
		}
		return providerBusiness;
	}

	public void setProviderBusiness(ProviderBusiness ProviderBusiness) {
		this.providerBusiness = ProviderBusiness;
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
	public void save(Provider entity) {
		getProviderBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Provider entity) {
		getProviderBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Provider retrieve(Long id) {
		Provider Provider = getProviderBusiness().retrieve(id);
		getTransaction().commit();

		return Provider;
	}

	@Override
	public void update(Provider entity) {
		getProviderBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Provider> getAll() {
		List<Provider> Providers = getProviderBusiness().getAll();
		return Providers;
	}
	
	public List<Provider> retrieveByName(String name){
		List<Provider> providers = getProviderBusiness().retrieveByName(name);
		return providers;
	}
	
	public Provider retrieveByExactName(String name){
		Provider provider = getProviderBusiness().retrieveByExactName(name);
		return provider;
	}

}

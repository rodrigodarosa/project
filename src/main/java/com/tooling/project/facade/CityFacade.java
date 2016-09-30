package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.CityBusiness;
import com.tooling.project.model.City;
import com.tooling.project.model.State;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class CityFacade implements Business<City> {

	private static final long serialVersionUID = 7585932597571221758L;

	private CityBusiness cityBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public CityBusiness getCityBusiness() {
		if (cityBusiness == null) {
			cityBusiness = new CityBusiness(getSession(), City.class);
		}
		return cityBusiness;
	}

	public void setCityBusiness(CityBusiness cityBusiness) {
		this.cityBusiness = cityBusiness;
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
	public void save(City entity) {
		getCityBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(City entity) {
		getCityBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public City retrieve(Long id) {
		City city = getCityBusiness().retrieve(id);
		getTransaction().commit();

		return city;
	}

	@Override
	public void update(City entity) {
		getCityBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<City> getAll() {
		List<City> cities = getCityBusiness().getAll();
		return cities;
	}
	
	public List<City> retrieveByState(State state){
		return getCityBusiness().retrieveByState(state);
		
	}

	public City retrieveByExactName(String string) {
		return getCityBusiness().retrieveByExactName(string);
	}

}

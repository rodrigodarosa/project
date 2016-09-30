package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.ThemeBusiness;
import com.tooling.project.model.Employee;
import com.tooling.project.model.Theme;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class ThemeFacade implements Business<Theme> {

	private static final long serialVersionUID = 7585932597571221758L;

	private ThemeBusiness themeBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public ThemeBusiness getThemeBusiness() {
		if (themeBusiness == null) {
			themeBusiness = new ThemeBusiness(getSession(), Theme.class);
		}
		return themeBusiness;
	}

	public void setThemeBusiness(ThemeBusiness themeBusiness) {
		this.themeBusiness = themeBusiness;
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
	public void save(Theme entity) {
		getThemeBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Theme entity) {
		getThemeBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Theme retrieve(Long id) {
		Theme theme = getThemeBusiness().retrieve(id);
		getTransaction().commit();
		return theme;
	}

	@Override
	public void update(Theme entity) {
		getThemeBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Theme> getAll() {
		List<Theme> themes = getThemeBusiness().getAll();
		return themes;
	}

	public Theme retrieveByEmployee(Employee employee) {
		return getThemeBusiness().retrieveByEmployee(employee);
	}


}

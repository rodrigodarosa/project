package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.AnnotationBusiness;
import com.tooling.project.model.Annotation;
import com.tooling.project.model.Employee;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class AnnotationFacade implements Business<Annotation> {

	private static final long serialVersionUID = 7585932597571221758L;

	private AnnotationBusiness annotationBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public AnnotationBusiness getAnnotationBusiness() {
		if (annotationBusiness == null) {
			annotationBusiness = new AnnotationBusiness(getSession(), Annotation.class);
		}
		return annotationBusiness;
	}

	public void setAnnotationBusiness(AnnotationBusiness annotationBusiness) {
		this.annotationBusiness = annotationBusiness;
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
	public void save(Annotation entity) {
		getAnnotationBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Annotation entity) {
		getAnnotationBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Annotation retrieve(Long id) {
		Annotation annotation = getAnnotationBusiness().retrieve(id);
		getTransaction().commit();

		return annotation;
	}

	@Override
	public void update(Annotation entity) {
		getAnnotationBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Annotation> getAll() {
		List<Annotation> annotations = getAnnotationBusiness().getAll();
		return annotations;
	}

	public List<Annotation> getLastTthree(Employee employee) {
		List<Annotation> annotations = getAnnotationBusiness().getLastThree(employee);
		return annotations;
	}

}

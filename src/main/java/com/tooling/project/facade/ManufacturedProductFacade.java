package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.ManufacturedProductBusiness;
import com.tooling.project.model.ManufacturedProduct;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class ManufacturedProductFacade implements Business<ManufacturedProduct> {

	private static final long serialVersionUID = 7585932597571221758L;

	private ManufacturedProductBusiness manufacturedProductBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public ManufacturedProductBusiness getManufacturedProductBusiness() {
		if (manufacturedProductBusiness == null) {
			manufacturedProductBusiness = new ManufacturedProductBusiness(getSession(), ManufacturedProduct.class);
		}
		return manufacturedProductBusiness;
	}

	public void setManufacturedProductBusiness(ManufacturedProductBusiness manufacturedProductBusiness) {
		this.manufacturedProductBusiness = manufacturedProductBusiness;
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
	public void save(ManufacturedProduct entity) {
		getManufacturedProductBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(ManufacturedProduct entity) {
		getManufacturedProductBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public ManufacturedProduct retrieve(Long id) {
		ManufacturedProduct manufacturedProduct = getManufacturedProductBusiness().retrieve(id);
		getTransaction().commit();

		return manufacturedProduct;
	}

	@Override
	public void update(ManufacturedProduct entity) {
		getManufacturedProductBusiness().update(entity);
		// getTransaction().commit();
	}

	@Override
	public List<ManufacturedProduct> getAll() {
		List<ManufacturedProduct> manufacturedProducts = getManufacturedProductBusiness().getAll();
		return manufacturedProducts;
	}

	public ManufacturedProduct retrieveByExactName(String manufacturedProductName) {
		ManufacturedProduct manufacturedProduct = getManufacturedProductBusiness().retrieveByExactName(manufacturedProductName);
		return manufacturedProduct;
	}

	public List<ManufacturedProduct> retrieveManufacturedProductsNoInPurchase() {
		return getManufacturedProductBusiness().retrieveManufacturedProductsNoInPurchase();
	}

}

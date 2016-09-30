package com.tooling.project.facade;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tooling.project.dao.ProductBusiness;
import com.tooling.project.model.Product;
import com.tooling.project.util.Business;
import com.tooling.project.util.HibernateService;

public class ProductFacade implements Business<Product> {

	private static final long serialVersionUID = 7585932597571221758L;

	private ProductBusiness productBusiness;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public ProductBusiness getProductBusiness() {
		if (productBusiness == null) {
			productBusiness = new ProductBusiness(getSession(), Product.class);
		}
		return productBusiness;
	}

	public void setProductBusiness(ProductBusiness productBusiness) {
		this.productBusiness = productBusiness;
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
	public void save(Product entity) {
		getProductBusiness().save(entity);
		getTransaction().commit();
	}

	@Override
	public void remove(Product entity) {
		getProductBusiness().remove(entity);
		getTransaction().commit();
	}

	@Override
	public Product retrieve(Long id) {
		Product product = getProductBusiness().retrieve(id);
		getTransaction().commit();

		return product;
	}

	@Override
	public void update(Product entity) {
		getProductBusiness().update(entity);
		getTransaction().commit();
	}

	@Override
	public List<Product> getAll() {
		List<Product> products = getProductBusiness().getAll();
		return products;
	}

	public List<Product> retrieveByName(String complete) {
		return getProductBusiness().retrieveByName(complete);
	}
	
	public Product retrieveByExactName(String complete) {
		return getProductBusiness().retrieveByExactName(complete);
	}

	public List<Product> retrieveProductsNoInPurchase(){
		return getProductBusiness().retrieveProductsNoInPurchase();
	}
}

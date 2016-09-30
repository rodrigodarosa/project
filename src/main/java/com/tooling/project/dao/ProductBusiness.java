package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.Product;
import com.tooling.project.util.DAO;

public class ProductBusiness extends DAO<Product> {

	public ProductBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<Product> retrieveByName(String name) {
		Criteria c = getSession().createCriteria(Product.class);
		c.add(Restrictions.ilike("name", "%" + name + "%"));
		return c.list();

	}

	public Product retrieveByExactName(String name) {
		Criteria c = getSession().createCriteria(Product.class);
		c.add(Restrictions.eq("name", name));
		return (Product) c.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<Product> retrieveProductsNoInPurchase() {
		SQLQuery query = getSession().createSQLQuery("select * from Product inner join purchase_product on product.ID_PRODUCT = purchase_product.ID_PRODUCT inner join purchase on purchase_product.ID_PURCHASE=purchase.ID_PURCHASE");

		query.addEntity(Product.class);
		List<Product> results = query.list();
		return results;
		
	}

}
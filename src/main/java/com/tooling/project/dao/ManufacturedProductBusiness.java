package com.tooling.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tooling.project.model.ManufacturedProduct;
import com.tooling.project.util.DAO;

public class ManufacturedProductBusiness extends DAO<ManufacturedProduct> {

	public ManufacturedProductBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}
	
	public ManufacturedProduct retrieveByExactName(String name) {
		Criteria c = getSession().createCriteria(ManufacturedProduct.class);
		c.add(Restrictions.eq("name", name));
		return (ManufacturedProduct) c.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<ManufacturedProduct> retrieveManufacturedProductsNoInPurchase() {
			SQLQuery query = getSession().createSQLQuery("select * from Manufactured_Product inner join sale_man_product on manufactured_product.ID_MAN_PRODUCT = sale_man_product.ID_MAN_PRODUCT inner join sale on sale_man_product.ID_SALE=SALE.ID_SALE");

			query.addEntity(ManufacturedProduct.class);
			List<ManufacturedProduct> results = query.list();
			return results;
			
		}

}
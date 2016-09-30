package com.tooling.project.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public class HibernateService {
	private static SessionFactory sessionFactory;
	private static Session session;
	
	static {
		try {
			if (session == null) {
				sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
				session = sessionFactory.openSession();
			}

		} catch (HibernateException he) {
			System.err.println("Error creating Session: " + he);
			throw new ExceptionInInitializerError(he);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
package com.tooling.project.dao;

import org.hibernate.Session;

import com.tooling.project.model.OutputBudget;
import com.tooling.project.util.DAO;

public class OutputBudgetBusiness extends DAO<OutputBudget> {

	public OutputBudgetBusiness(Session session, Class<?> persistentClass) {
		super(session, persistentClass);
	}

}
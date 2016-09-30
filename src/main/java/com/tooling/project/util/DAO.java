package com.tooling.project.util;

import java.util.List;

import org.hibernate.Session;

public class DAO<T> {

    private Class<?> persistentClass;
    private Session session;

    public DAO(Session session, Class<?> persistentClass) {
        this.session = session;
        this.persistentClass = persistentClass;
    }

    protected Session getSession(){
		return session; 	
	}
    
    @SuppressWarnings("unchecked")
    public T retrieve(Long id) {
        return (T) session.load(persistentClass, id);
    }
   
    public void save(T t) {
        session.save(t);
    }
    
	public void remove(T t) {
		session.delete(t);
	}
	
    @SuppressWarnings("unchecked")
	public List<T> getAll() {
    	return session.createCriteria(persistentClass).list();
    }

    public void update(T t) {
        session.clear();  
        session.update(t);
    }
    
    
}
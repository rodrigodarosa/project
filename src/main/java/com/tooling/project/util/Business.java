package com.tooling.project.util;

import java.io.Serializable;
import java.util.List;

public interface Business<T> extends Serializable {
	
	public abstract void save(T entity); 
	public abstract void remove(T entity);  
	public abstract T retrieve(Long id);  
    public abstract void update(T entity);
    public abstract List<T> getAll();
}
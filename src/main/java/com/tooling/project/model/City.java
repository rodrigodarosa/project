package com.tooling.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CITY")
public class City {

	private Long id;
	private String name;
	private List<Employee> employees;
	private List<Customer> customers;
	private List<Provider> providers;
	private State state;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CITY", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STATE")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final City other = (City) obj;
		if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

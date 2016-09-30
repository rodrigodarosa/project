package com.tooling.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "THEME")
public class Theme {

	private Long id;
	private Employee employee;
	private String theme;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_THEME", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_EMPLOYEE")
	public Employee getEmployee() {
		return employee;
	}

	@Column(name = "THEME")
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}

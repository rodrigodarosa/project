package com.tooling.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "OUTPUT_BUDGET")
public class OutputBudget {

	private Long id;
	private String description;
	private String technicalSpecification;
	private Date date;
	private Date deliveryDate;
	private Long quantity;
	private Employee employee;
	private Provider provider;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_OUTPUT_BUDGET", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EMPLOYEE")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROVIDER")
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Column(name="TECHNICAL_SPECIFICATION")
	public String getTechnicalSpecification() {
		return technicalSpecification;
	}

	public void setTechnicalSpecification(String technicalSpecification) {
		this.technicalSpecification = technicalSpecification;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DELIVERY_DATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name="QUANTITY")
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	

}

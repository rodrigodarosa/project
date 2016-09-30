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

import com.tooling.project.enums.PauseReasonNotationEnum;

@Entity
@Table(name="NOTATION")
public class Notation {

	private Long id;
	private Employee employee;
	private ServiceOrder serviceOrder;
	private Date startDate;
	private Date endDate;
	private String observation;
	private PauseReasonNotationEnum pauseReason;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_NOTATION", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	@JoinColumn(name = "ID_SERVICE_ORDER")
	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name="OBSERVATION")
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Column(name="PAUSE_REASON")
	public PauseReasonNotationEnum getPauseReason() {
		return pauseReason;
	}

	public void setPauseReason(PauseReasonNotationEnum pauseReason) {
		this.pauseReason = pauseReason;
	}

	

}

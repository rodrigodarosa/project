package com.tooling.project.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tooling.project.enums.ServiceOrderSituationEnum;

@Entity
@Table(name = "SERVICE_ORDER")
public class ServiceOrder {

	private Long id;
	private String description;
	private Date startDate;
	private Date endDate;
	private Employee employee;
	private Machine machine;
	private ServiceOrderSituationEnum situation;
	private List<Notation> notations;
	private Integer walkThrough;
	private ManufacturedProduct manufacturedProduct;
	private String fileName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SERVICE_ORDER", unique = true)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EMPLOYEE")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	// @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinTable(name = "SERVICE", joinColumns = { @JoinColumn(name =
	// "ID_SERVICE_ORDER", updatable = false) }, inverseJoinColumns = {
	// @JoinColumn(name = "ID_MACHINE", updatable = false) })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MACHINE")
	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceOrder")
	public List<Notation> getNotations() {
		return notations;
	}

	public void setNotations(List<Notation> notations) {
		this.notations = notations;
	}

	@Column(name = "SITUATION")
	public ServiceOrderSituationEnum getSituation() {
		return situation;
	}

	public void setSituation(ServiceOrderSituationEnum situation) {
		this.situation = situation;
	}

	@Column(name = "WALK_THROUGH")
	public Integer getWalkThrough() {
		return walkThrough;
	}

	public void setWalkThrough(Integer walkThrough) {
		this.walkThrough = walkThrough;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MANUFACTURED_PRODUCT")
	public ManufacturedProduct getManufacturedProduct() {
		return manufacturedProduct;
	}

	public void setManufacturedProduct(ManufacturedProduct manufacturedProduct) {
		this.manufacturedProduct = manufacturedProduct;
	}
	
	@Override
	public String toString() {
		return description;
	}

	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}

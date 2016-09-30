package com.tooling.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tooling.project.enums.MachineSituationEnum;

@Entity
@Table(name = "MACHINE")
public class Machine {

	private Long id;
	private String name;
	private String producer;
	private MachineSituationEnum situation;
	private List<ServiceOrder> serviceOrder;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MACHINE", unique = true)
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

	@Column(name = "SITUATION")
	public MachineSituationEnum getSituation() {
		return situation;
	}

	public void setSituation(MachineSituationEnum situation) {
		this.situation = situation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "machine")
	public List<ServiceOrder> getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(List<ServiceOrder> serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	@Column(name = "PRODUCER")
	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	@Override
	public String toString() {
		return name;
	}

}

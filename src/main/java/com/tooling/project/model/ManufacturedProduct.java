package com.tooling.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tooling.project.enums.ManufacturedProductSituationEnum;

@Entity
@Table(name = "MANUFACTURED_PRODUCT")
public class ManufacturedProduct {

	private Long id;
	private String name;
	private String description;
	private ManufacturedProductSituationEnum situation;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MAN_PRODUCT", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="SUTUATION")
	public ManufacturedProductSituationEnum getSituation() {
		return situation;
	}

	public void setSituation(ManufacturedProductSituationEnum situation) {
		this.situation = situation;
	}

	
	@Override
	public String toString() {
		return this.name;
	}

}

package com.tooling.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tooling.project.enums.ProductTypeEnum;

@Entity
@Table(name = "PRODUCT")
public class Product {

	private Long id;
	private String name;
	private String description;
	private ProductTypeEnum type;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUCT", unique = true)
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

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TYPE")
	public ProductTypeEnum getType() {
		return type;
	}

	public void setType(ProductTypeEnum type) {
		this.type = type;
	}


	@Override
	@Transient
	public String toString() {
		return name;
	}

	
}

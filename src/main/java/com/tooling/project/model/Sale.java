package com.tooling.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SALE")
public class Sale {

	private Long id;
	private String description;
	private Date date;
	private Customer customer;
	private List<ManufacturedProduct> manufacturedProducts;
	private Employee Seller;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SALE", unique = true)
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
	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUSTOMER")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="SALE_MAN_PRODUCT", joinColumns={@JoinColumn(name="ID_SALE", referencedColumnName="ID_SALE")}, inverseJoinColumns={@JoinColumn(name="ID_MAN_PRODUCT", referencedColumnName="ID_MAN_PRODUCT")})
	public List<ManufacturedProduct> getManufacturedProducts() {
		if(manufacturedProducts == null){
			manufacturedProducts= new ArrayList<ManufacturedProduct>();
		}
		return manufacturedProducts;
	}

	public void setManufacturedProducts(List<ManufacturedProduct> manufacturedProducts) {
		this.manufacturedProducts = manufacturedProducts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SELLER")
	public Employee getSeller() {
		return Seller;
	}

	public void setSeller(Employee seller) {
		Seller = seller;
	}

	@Override
	public String toString() {
		return id.toString();
	}
}

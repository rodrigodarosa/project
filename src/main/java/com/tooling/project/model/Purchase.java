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
@Table(name = "PURCHASE")
public class Purchase {

	private Long id;
	private String description;
	private Date date;
	private Provider provider;
	private List<Product> products;
	private Employee buyer;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PURCHASE", unique = true)
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
	@JoinColumn(name = "ID_PROVIDER")
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="PURCHASE_PRODUCT", joinColumns={@JoinColumn(name="ID_PURCHASE", referencedColumnName="ID_PURCHASE")}, inverseJoinColumns={@JoinColumn(name="ID_PRODUCT", referencedColumnName="ID_PRODUCT")})
	public List<Product> getProducts() {
		if(products == null){
			products= new ArrayList<Product>();
		}
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_BUYER")
	public Employee getBuyer() {
		return buyer;
	}

	public void setBuyer(Employee buyer) {
		this.buyer = buyer;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
	
}

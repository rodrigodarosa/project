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
@Table(name="CUSTOMER")
public class Customer {

	private Long id;
	private String name;
	private String cnpj;
	private String address;
	private String telephone;
	private String email;
	private City city;
	private String cep;
	private String homePage;
	private List<InputBudget> inputBudgets;
	private List<Sale> sales;
	private List<Receivable> receivables;
	private String username;
	private String password;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CUSTOMER", unique = true)
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

	@Column(name="CNPJ")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CITY")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<InputBudget> getInputBudgets() {
		return inputBudgets;
	}

	public void setInputBudgets(List<InputBudget> inputBudgets) {
		this.inputBudgets = inputBudgets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public List<Receivable> getReceivables() {
		return receivables;
	}

	public void setReceivables(List<Receivable> receivables) {
		this.receivables = receivables;
	}

	@Column(name="CEP")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name="HOME_PAGE")
	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	@Column(name="USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return name;
	}
	
}

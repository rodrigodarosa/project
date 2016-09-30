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
@Table(name = "PROVIDER")
public class Provider {

	private Long id;
	private String name;
	private String cnpj;
	private String address;
	private String telephone;
	private String email;
	private City city;
	private String cep;
	private String homePage;
	private List<OutputBudget> outputBudgets;
	private List<Payable> payables;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROVIDER", unique = true)
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

	@Column(name = "CNPJ")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "EMAIL")
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
	public List<OutputBudget> getOutputBudgets() {
		return outputBudgets;
	}

	public void setOutputBudgets(List<OutputBudget> outputBudgets) {
		this.outputBudgets = outputBudgets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
	public List<Payable> getPayables() {
		return payables;
	}

	public void setPayables(List<Payable> payables) {
		this.payables = payables;
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

	
	@Override
	public String toString() {
		return this.name;
	}
	
}

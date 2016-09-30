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

import com.tooling.project.enums.CivilStateEnum;
import com.tooling.project.enums.ProfileEnum;
import com.tooling.project.enums.SexEnum;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	private Long id;
	private String name;
	private String address;
	private String cpf;
	private String telephone;
	private String email;
	private City city;
	private String username;
	private String password;
	private CivilStateEnum civilStatus;
	private String cep;
	private String motherName;
	private String fatherName;
	private SexEnum sex;
	private List<OutputBudget> outputBudgets;
	private List<ServiceOrder> serviceOrders;
	private ProfileEnum profile;
	private List<Notation> notations;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPLOYEE", unique = true)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public List<OutputBudget> getOutputBudgets() {
		return outputBudgets;
	}

	public void setOutputBudgets(List<OutputBudget> outputBudgets) {
		this.outputBudgets = outputBudgets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public List<ServiceOrder> getServiceOrders() {
		return serviceOrders;
	}

	public void setServiceOrders(List<ServiceOrder> serviceOrders) {
		this.serviceOrders = serviceOrders;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "CPF")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CITY")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "PROFILE")
	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public List<Notation> getNotations() {
		return notations;
	}

	public void setNotations(List<Notation> notations) {
		this.notations = notations;
	}

	@Column(name = "CIVIL_STATUS")
	public CivilStateEnum getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(CivilStateEnum civilStatus) {
		this.civilStatus = civilStatus;
	}

	@Column(name = "CEP")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "MOTHER_NAME")
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	@Column(name = "FATHER_NAME")
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	@Column(name = "SEX")
	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((civilStatus == null) ? 0 : civilStatus.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fatherName == null) ? 0 : fatherName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motherName == null) ? 0 : motherName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notations == null) ? 0 : notations.hashCode());
		result = prime * result + ((outputBudgets == null) ? 0 : outputBudgets.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((serviceOrders == null) ? 0 : serviceOrders.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (civilStatus != other.civilStatus)
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fatherName == null) {
			if (other.fatherName != null)
				return false;
		} else if (!fatherName.equals(other.fatherName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motherName == null) {
			if (other.motherName != null)
				return false;
		} else if (!motherName.equals(other.motherName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notations == null) {
			if (other.notations != null)
				return false;
		} else if (!notations.equals(other.notations))
			return false;
		if (outputBudgets == null) {
			if (other.outputBudgets != null)
				return false;
		} else if (!outputBudgets.equals(other.outputBudgets))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (profile != other.profile)
			return false;
		if (serviceOrders == null) {
			if (other.serviceOrders != null)
				return false;
		} else if (!serviceOrders.equals(other.serviceOrders))
			return false;
		if (sex != other.sex)
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	

}

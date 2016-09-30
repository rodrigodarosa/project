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

import com.tooling.project.enums.DischargeSituationEnum;
import com.tooling.project.enums.PaymentTypeEnum;

@Entity
@Table(name = "RECEIVABLE")
public class Receivable {

	private Long id;
	private float totalValue;
	private Long paymentTimes;
	private String description;
	private Customer customer;
	private Date date;
	private Date expirationDate;
	private Long documentNumber;
	private Date documentDate;
	private DischargeSituationEnum dischargeSituation;
	private PaymentTypeEnum paymentType;
	private Date paymentDate;
	private Sale sale;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RECEIVABLE", unique = true, nullable = false)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUSTOMER", nullable = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "DISCHARGE_SITUATION")
	public DischargeSituationEnum getDischargeSituation() {
		return dischargeSituation;
	}

	public void setDischargeSituation(DischargeSituationEnum dischargeSituation) {
		this.dischargeSituation = dischargeSituation;
	}

	@Column(name = "PAYMENT_TYPE")
	public PaymentTypeEnum getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentTypeEnum paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "TOTAL_VALUE")
	public float getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}

	@Column(name = "PAYMENT_TIMES")
	public Long getPaymentTimes() {
		return paymentTimes;
	}

	public void setPaymentTimes(Long paymentTimes) {
		this.paymentTimes = paymentTimes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRATION_DATE")
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAYMENT_DATE")
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column(name = "DOCUMENT_NUMBER")
	public Long getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DOCUMENT_DATE")
	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SALE", nullable = false)
	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
}

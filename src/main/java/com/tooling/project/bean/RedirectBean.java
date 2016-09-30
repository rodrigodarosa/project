package com.tooling.project.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.tooling.project.enums.ProfileEnum;
import com.tooling.project.facade.EmployeeFacade;

@ManagedBean
@RequestScoped
public class RedirectBean {
	private EmployeeFacade employeeFacade;
	

	public EmployeeFacade getEmployeeFacade() {
		if(employeeFacade == null){
			employeeFacade = new EmployeeFacade();
		}
		return employeeFacade;
	}

	public void setEmployeeFacade(EmployeeFacade employeeFacade) {
		this.employeeFacade = employeeFacade;
	}

	public String goToPayables() {
		return "payables";
	}

	public String goToReceivables() {
		return "receivables";
	}

	public String goToInputBudgets() {
		return "inputBudgets";
	}

	public String goToOutputBudgets() {
		return "outputBudgets";
	}

	public String goToHome() {
		return "home";
	}

	public String goToMachines() {
		return "machines";
	}

	public String goToServiceOrders() {
		if(getEmployeeFacade().getCurrentLogged().getProfile().equals(ProfileEnum.OPERATOR)){
			return "homeOperator";
		}
		return "serviceOrders";
	}
	
	public String goToManufacturedProduct() {
		return "manufacturedProducts";
	}
	
	public String goToCustomers() {
		return "customers";
	}
	
	public String goToProviders() {
		return "providers";
	}
	
	public String goToProducts(){
		return "products";
	}
	
	public String goToEmployees(){
		return "employees";
	}
	
	public String goToPurchases(){
		return "purchases";
	}
	
	public String goToSales(){
		return "sales";
	}
	
	public String goToNotations(){
		return "notations";
	}
	
	public String goToCustomerInputBudgets(){
		return "homeCustomer";
	}
	
	public String goToContact(){
		return "contactCustomer";
	}
	
	public String goToHomeOperator() { 
		return "homeOperator";
	}

	public String goToHomeManagerOS(){
		return "homeManagerOS";
	}
	
	public String goToHomeBuyer(){
		return "homeBuyer";
	}
	
	public String goToHomeFinancial(){
		return "homeFinancial";
	}
	public String goToHomeSeller(){
		return "homeSeller";
	}
	
}

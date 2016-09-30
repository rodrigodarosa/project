package com.tooling.project.enums;

public enum ProfileEnum {

	ADMINISTRATOR("Administrador"), FINANCIAL("Financeiro"), MANAGER_OS("Processista"), BUYER("Comprador"), SELLER("Vendedor"), OPERATOR("Oper\u00E1rio");
	
	private final String label;

	  private ProfileEnum(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }

}

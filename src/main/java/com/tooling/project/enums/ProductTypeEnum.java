package com.tooling.project.enums;

public enum ProductTypeEnum {
	TOOL("Ferramenta"), IT("T.I."), INFRASTRUCTURE("Infraestrutura"), OTHERS("Outros"); 
	
	private final String label;

	  private ProductTypeEnum(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }


}

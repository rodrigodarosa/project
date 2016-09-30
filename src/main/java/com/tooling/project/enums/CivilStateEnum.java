package com.tooling.project.enums;

public enum CivilStateEnum {
	SINGLE("Solteiro"), MARRIED("Casado"), DIVORCED("Divorciado"), WIDOWER("Vi\u00FAvo");
	
	private final String label;

	  private CivilStateEnum(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }


}

package com.tooling.project.enums;

public enum DischargeSituationEnum {
	COMPLETED("Completo"), PENDING("Pendente");
	
	private final String label;

	  private DischargeSituationEnum(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }

}

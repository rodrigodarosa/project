package com.tooling.project.enums;

public enum MachineSituationEnum {
	AVAILABLE("Dispon\u00EDvel"), UNAVAILABLE("Indispon\u00EDvel");
	
	private final String label;

	  private MachineSituationEnum(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }


}

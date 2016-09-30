package com.tooling.project.enums;

public enum InputBudgetSituationEnum {
	PENDING("Pendente"), ACCEPTED("Deferido"), NOT_ACCEPTED("Indeferido");
	
	private final String label;

	  private InputBudgetSituationEnum(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }


}

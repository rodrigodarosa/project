package com.tooling.project.enums;

public enum SexEnum {
	MALE("Masculino"), FEMALE("Feminino");

	private final String label;

	  private SexEnum(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }

}

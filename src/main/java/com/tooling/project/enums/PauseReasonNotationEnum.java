package com.tooling.project.enums;

public enum PauseReasonNotationEnum {
	LACK_OF_STUFF("Falta de Material"), ENERGY_BLACKOUT("Queda de Energia"), MECHANICAL_ISSUE("Problema Mec\u00E2nico"), OTHERS_PRIORITIES("Outra Prioridade"), MEAL("Refei\uFFFD\uFFFDo"), REUNION("Reuni\uFFFDo"), ;
	
	private final String label;

	  private PauseReasonNotationEnum(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }


}

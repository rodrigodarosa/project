package com.tooling.project.enums;

public enum ServiceOrderSituationEnum {
	ASSEMBLING("Em Execu\u00E7\u00E3o"), NOT_INITIATED("N\u00E3o Inicializado"), FINALIZED("Finalizado"), STAND_BY("Em Pausa");
	
	private final String label;

	   private ServiceOrderSituationEnum(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }


}

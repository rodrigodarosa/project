package com.tooling.project.enums;

public enum PaymentTypeEnum {
	CARD("Cart\u00E3o"), DEPOSIT("Dep\u00F3sito"), MONEY("Dinheiro");

	private final String label;

	private PaymentTypeEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

}

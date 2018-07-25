package com.mpathozulu.soft.ibudget2.model;

public enum BudgetEntryType {
	Income("Income"), Expenditure("Expenditure"), Saving("Saving");
	private String name;

	BudgetEntryType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
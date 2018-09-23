package com.mpathozulu.soft.ibudget.model;

public enum BudgetEntryType {
	Income("Income"),
	Expenditure("Expenditure"), // change to Personal
	Transportation("Transportation"),
	Medical("Medical"),
	Food("Food"),
	Shelter("Shelter"),
	Clothing("Clothing"),
	Insurance("Insurance"),
	Supplies("Supplies"),
	DebtReduction("Debt Reduction"),
	FunMoney("Fun Money"),
	Saving("Saving");

	private String name;

	BudgetEntryType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
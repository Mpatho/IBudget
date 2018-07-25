package com.mpathozulu.soft.ibudget2.model;

public enum BudgetMonth {
	JANUARY("January",1),
	FEBRUARY("February",2),
	MARCH("March",3),
	APRIL("April",4),
	MAY("May",5),
	JUNE("June",6),
	JULY("July",7),
	AUGUST("August",8),
	SEPTEMBER("September",9),
	OCTOBER("October",10),
	NOVEMBER("November",11),
	DECEMBER("December",12);

	private String name;

	private int id;

	BudgetMonth(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

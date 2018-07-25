package com.mpathozulu.soft.ibudget.model;

import android.content.Context;
import com.mpathozulu.soft.ibudget.BudgetApplication;
import com.mpathozulu.soft.ibudget.persist.Persistable;

import java.io.Serializable;
import java.util.*;

public class BudgetPlan implements Serializable {

	private static List<BudgetPlan> budgetPlans = new LinkedList<>();
	private Persistable<BudgetEntry> persistable;
	private int year;
	private BudgetMonth month;
	private SortedSet<BudgetEntry> incomes;
	private SortedSet<BudgetEntry> expenditures;
	private SortedSet<BudgetEntry> savings;

	private BudgetPlan(Context context, int year, BudgetMonth month) {
		persistable = new BudgetEntryPersistence(context);
		this.year = year;
		this.month = month;
		this.incomes = persistable.getItems(BudgetEntryType.Income, year, month);
		this.expenditures = persistable.getItems(BudgetEntryType.Expenditure, year, month);
		this.savings = persistable.getItems(BudgetEntryType.Saving, year, month);
	}

	public BudgetMonth getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public static BudgetPlan getBudgetPlan(int year, BudgetMonth budgetMonth) {
		for (BudgetPlan budgetPlan : budgetPlans) {
			if (budgetPlan.getMonth().equals(budgetMonth) && budgetPlan.getYear() == year) {
				return budgetPlan;
			}
		}
		BudgetPlan budgetPlan = new BudgetPlan(BudgetApplication.getContext(), year, budgetMonth);
		budgetPlans.add(budgetPlan);
		return budgetPlan;
	}

	public static BudgetPlan getBudgetPlan() {
		int size = budgetPlans.size();
		if (size != 0) {
			return budgetPlans.get(size - 1);
		}
		return null;
	}

	private double getTotalOutcome() {
		return getTotalAmount(expenditures) + getTotalAmount(savings);
	}

	private double getTotalIncome() {
		return getTotalAmount(incomes);
	}

	public double getSavings() {
		return getTotalIncome() - getTotalOutcome();
	}

	private double getTotalAmount(Iterable<BudgetEntry> budgetEntries) {
		int total = 0;
		for (BudgetEntry budgetEntry : budgetEntries) {
			total += budgetEntry.getAmount() * 100 * budgetEntry.getFrequency();
		}
		return total / 100.0;
	}

	public SortedSet<BudgetEntry> getItems(BudgetEntryType budgetEntryType) {
		switch (budgetEntryType) {
			case Income:
				return incomes;
			case Expenditure:
				return expenditures;
			case Saving:
				return savings;
		}
		return null;
	}

	public void delete(BudgetEntry budgetEntry) {
		if (incomes.remove(budgetEntry) || expenditures.remove(budgetEntry) || savings.remove(budgetEntry)) persistable.delete(budgetEntry);
	}

	public void save(BudgetEntry budgetEntry) {
		switch (budgetEntry.getBudgetEntryType()) {
			case Income:
				incomes.add(budgetEntry);
				break;
			case Expenditure:
				expenditures.add(budgetEntry);
				break;
			case Saving:
				savings.add(budgetEntry);
		}
		persistable.save(budgetEntry);
	}
}
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

	private BudgetPlan(Context context, int year, BudgetMonth month) {
		persistable = new BudgetEntryPersistence(context);
		this.year = year;
		this.month = month;
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

	public double getTotalOutcome() {
		return getExpenditure() +
				getTransportation() +
				getMedical() +
				getFood() +
				getShelter() +
				getClothing() +
				getInsurance() +
				getSupplies() +
				getDebtReduction() +
				getSaving() +
				getFunMoney();
	}

	public double getIncome() {
		return getTotalAmount(BudgetEntryType.Income);
	}

	public double getExpenditure() {
		return getTotalAmount(BudgetEntryType.Expenditure);
	}

	public double getTransportation() {
		return getTotalAmount(BudgetEntryType.Transportation);
	}

	public double getMedical() {
		return getTotalAmount(BudgetEntryType.Medical);
	}

	public double getFood() {
		return getTotalAmount(BudgetEntryType.Food);
	}

	public double getShelter() {
		return getTotalAmount(BudgetEntryType.Shelter);
	}

	public double getClothing() {
		return getTotalAmount(BudgetEntryType.Clothing);
	}

	public double getInsurance() {
		return getTotalAmount(BudgetEntryType.Insurance);
	}

	public double getSupplies() {
		return getTotalAmount(BudgetEntryType.Supplies);
	}

	public double getDebtReduction() {
		return getTotalAmount(BudgetEntryType.DebtReduction);
	}

	public double getFunMoney() {
		return getTotalAmount(BudgetEntryType.FunMoney);
	}

	public double getSaving() {
		return getTotalAmount(BudgetEntryType.Saving);
	}

	public double getSavings() {
		return getIncome() - getTotalOutcome();
	}

	private double getTotalAmount(BudgetEntryType type) {
		Iterable<BudgetEntry> budgetEntries = getItems(type);
		int total = 0;
		for (BudgetEntry budgetEntry : budgetEntries) {
			total += budgetEntry.getAmount() * 100 * budgetEntry.getFrequency();
		}
		return total / 100.0;
	}

	public SortedSet<BudgetEntry> getItems(BudgetEntryType budgetEntryType) {
		return  persistable.getItems(budgetEntryType, year, month);
	}

	public void delete(BudgetEntry budgetEntry) {
		persistable.delete(budgetEntry);
	}

	public void save(BudgetEntry budgetEntry) {
		persistable.save(budgetEntry);
	}
}
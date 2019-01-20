package com.mpathozulu.ibudget.model;

import com.mpathozulu.ibudget.persistance.Persistable;

public class BudgetEntry extends Persistable<BudgetEntry> {

    private String name;

    private int amount;

    private int frequency;

    private int year;

    private BudgetMonth month;

    private BudgetEntryType budgetEntryType;


    public BudgetEntry(Long id, String name, double amount, int frequency, int year, BudgetMonth month, BudgetEntryType budgetEntryType) {
        super(id);
        this.setName(name);
        this.setAmount(amount);
        this.frequency = frequency;
        this.year = year;
        this.month = month;
        this.budgetEntryType = budgetEntryType;
    }

    public BudgetEntry(int year, BudgetMonth month, BudgetEntryType budgetEntryType) {
        this(null, null, 0, 0, year, month, budgetEntryType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount / 100.0;
    }

    public void setAmount(double amount) {
        this.amount = (int) (amount * 100);
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BudgetMonth getMonth() {
        return month;
    }

    public void setMonth(BudgetMonth month) {
        this.month = month;
    }

    public BudgetEntryType getBudgetEntryType() {
        return budgetEntryType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj.getClass() != getClass()) return false;
        BudgetEntry budgetEntry = (BudgetEntry) obj;
        return (name != null && budgetEntry.name != null && name.equals(budgetEntry.name));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(BudgetEntry o) {
        if (equals(o)) return 0;
        if (amount * frequency > o.amount * o.frequency) return -1;
        if (amount * frequency < o.amount * o.frequency) return 1;
        if (amount > o.amount) return -1;
        if (amount < o.amount) return 1;
        int compareTo = name.compareTo(o.name);
        if (compareTo != 0) return compareTo;
        return 0;
    }
}
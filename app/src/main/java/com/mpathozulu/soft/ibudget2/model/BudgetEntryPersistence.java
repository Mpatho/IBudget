package com.mpathozulu.soft.ibudget2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mpathozulu.soft.ibudget2.persist.BudgetSQLiteOpenHelper;
import com.mpathozulu.soft.ibudget2.persist.Persistable;

import java.util.*;

class BudgetEntryPersistence implements Persistable<BudgetEntry> {

	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_AMOUNT = "amount";
	private static final String COLUMN_FREQUENCY = "frequency";
	private static final String COLUMN_TYPE = "Type";
	private static final String COLUMN_YEAR = "Year";
	private static final String COLUMN_MONTH = "Month";

	private static final String DATABASE_NAME = "budget.db";
	private static final String TABLE_BUDGET_ENTRY = "Budget_Entry";

	private BudgetSQLiteOpenHelper budgetSQLiteOpenHelper;

	BudgetEntryPersistence(Context context) {
		this.budgetSQLiteOpenHelper = new BudgetSQLiteOpenHelper(context, this);
	}

	public String getDatabaseName() {
		return DATABASE_NAME;
	}

	public String[] getSchemaStatement() {
		String format = "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s REAL, %s REAL,%s INTEGER NOT NULL,%s TEXT NOT NULL, %s TEXT NOT NULL)";
		String create = String.format(format, TABLE_BUDGET_ENTRY, COLUMN_ID, COLUMN_NAME, COLUMN_AMOUNT, COLUMN_FREQUENCY, COLUMN_YEAR, COLUMN_MONTH, COLUMN_TYPE);
		return new String[] {create};
	}

	public String[] getTablesName() {
		return new String[] {TABLE_BUDGET_ENTRY};
	}

	public void delete(BudgetEntry entry) {
		SQLiteDatabase db = budgetSQLiteOpenHelper.getWritableDatabase();
		db.delete(TABLE_BUDGET_ENTRY, String.format(Locale.getDefault(), "%s='%d'", COLUMN_ID, entry.getId()), null);
		db.close();
	}

	public void save(BudgetEntry budgetEntry) {
		SQLiteDatabase db = budgetSQLiteOpenHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, budgetEntry.getName());
		values.put(COLUMN_AMOUNT, budgetEntry.getAmount());
		values.put(COLUMN_FREQUENCY, budgetEntry.getFrequency());
		values.put(COLUMN_YEAR, budgetEntry.getYear());
		values.put(COLUMN_MONTH, budgetEntry.getMonth().getName());
		values.put(COLUMN_TYPE, budgetEntry.getBudgetEntryType().getName());
		if (budgetEntry.hasId()) {
			String whereClause = String.format(Locale.getDefault(), "%s='%d'", COLUMN_ID, budgetEntry.getId());
			db.update(TABLE_BUDGET_ENTRY, values, whereClause, null);
		} else {
			budgetEntry.setId(db.insert(TABLE_BUDGET_ENTRY, null, values));
		}
		db.close();
	}

	public SortedSet<BudgetEntry> getItems(Object ... args) {
		BudgetEntryType entryType = (BudgetEntryType) args[0];
		int year = (Integer) args[1];
		BudgetMonth month = (BudgetMonth) args[2];
		SQLiteDatabase db = budgetSQLiteOpenHelper.getWritableDatabase();
		SortedSet<BudgetEntry> incomes = new TreeSet<>();
		String selection = String.format("%s=? AND %s=? AND %s=?", COLUMN_TYPE, COLUMN_YEAR, COLUMN_MONTH);
		Cursor c = db.query(TABLE_BUDGET_ENTRY, null, selection, new String[]{entryType.getName(), String.valueOf(year), month.getName()},null,null,null);
		if (c.moveToFirst()) {
			do {
				long id = c.getInt(0);
				String name = c.getString(1);
				double amount = c.getDouble(2);
				int frequency = c.getInt(3);
				incomes.add(new BudgetEntry(id, name, amount, frequency, year, month, entryType));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return incomes;
	}
}

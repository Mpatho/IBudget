package com.mpathozulu.ibudget.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BudgetSQLiteOpenHelper extends SQLiteOpenHelper {

	private Persistence persistable;

	BudgetSQLiteOpenHelper(Context context, Persistence persistable) {
		super(context, persistable.getDatabaseName(), null, 1);
		this.persistable = persistable;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (String schema: persistable.getSchemaStatement()) {
			db.execSQL(schema);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for(String table: persistable.getTablesName()) {
			db.execSQL("DROP TABLE IF EXISTS " + table);
		}
		onCreate(db);
	}
}

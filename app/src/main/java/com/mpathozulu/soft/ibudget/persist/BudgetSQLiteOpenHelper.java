package com.mpathozulu.soft.ibudget.persist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BudgetSQLiteOpenHelper extends SQLiteOpenHelper {

	private Persistable persistable;

	public BudgetSQLiteOpenHelper(Context context, Persistable persistable) {
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
//			db.execSQL("DROP TABLE IF EXISTS " + table);
		}
//		onCreate(db);
	}
}

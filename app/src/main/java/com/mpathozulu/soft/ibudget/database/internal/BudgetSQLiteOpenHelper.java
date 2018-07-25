package com.mpathozulu.soft.ibudget.database.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Collections;
import java.util.List;

public class BudgetSQLiteOpenHelper extends SQLiteOpenHelper {

    private List<Class<?>> classes;

    public BudgetSQLiteOpenHelper(Context context, List<Class<?>> classes, String databaseName) {
        super(context, databaseName, null, 1);
        this.classes = Collections.unmodifiableList(classes);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for  (Class<?> clazz : classes) {
            db.execSQL(TableUtil.getTableDeclaration(clazz));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (Class<?> clazz : classes) {
//            db.execSQL("DROP TABLE IF EXISTS " + TableUtil.getTable(clazz).name());
        }
        onCreate(db);
    }

}

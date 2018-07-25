package com.mpathozulu.soft.ibudget2.database.internal;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import com.mpathozulu.soft.ibudget2.database.PersistenceContext;
import com.mpathozulu.soft.ibudget2.database.PersistenceContextFactory;

import java.util.List;

public final class PersistenceContextFactoryImpl implements PersistenceContextFactory  {

    private final PersistenceContext persistenceContext;

    public PersistenceContextFactoryImpl(Context context, List<Class<?>> classes) {
        SQLiteOpenHelper sqLiteOpenHelper= new BudgetSQLiteOpenHelper(context, classes, "budget.db");
        this.persistenceContext = new PersistenceContextImpl(sqLiteOpenHelper);
    }

    public PersistenceContext getPersistenceContext() {
        return persistenceContext;
    }
}

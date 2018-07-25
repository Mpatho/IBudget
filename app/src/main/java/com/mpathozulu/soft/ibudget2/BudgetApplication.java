package com.mpathozulu.soft.ibudget2;

import android.app.Application;
import android.content.Context;
import com.mpathozulu.soft.ibudget2.database.internal.PersistenceContextFactoryImpl;
import com.mpathozulu.soft.ibudget2.model.BudgetEntry;

import java.util.Arrays;

public class BudgetApplication extends Application {

    private static Class<?>[] classes = {BudgetEntry.class };

    private static Context context;

    private static PersistenceContextFactoryImpl persistenceContextFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this.getApplicationContext();
        this.persistenceContextFactory = new PersistenceContextFactoryImpl(context, Arrays.asList(classes));
    }

    public static Context getContext() {
        return context.getApplicationContext();
    }

    public static PersistenceContextFactoryImpl getPersistenceContextFactory() {
        return persistenceContextFactory;
    }

}

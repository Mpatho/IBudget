package com.mpathozulu.soft.ibudget;

import android.app.Application;
import android.content.Context;
import com.mpathozulu.soft.ibudget.database.internal.PersistenceContextFactoryImpl;
import com.mpathozulu.soft.ibudget.model.BudgetEntry;

import java.util.Arrays;

public class BudgetApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this.getApplicationContext();
    }

    public static Context getContext() {
        return context.getApplicationContext();
    }

}

package com.mpathozulu.ibudget;

import android.app.Application;
import android.content.Context;

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

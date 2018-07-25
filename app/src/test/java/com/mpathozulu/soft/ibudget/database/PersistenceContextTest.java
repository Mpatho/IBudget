package com.mpathozulu.soft.ibudget.database;

import android.content.Context;
import android.test.mock.MockContext;
import com.mpathozulu.soft.ibudget.database.internal.PersistenceContextFactoryImpl;
import com.mpathozulu.soft.ibudget.model.BudgetEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PersistenceContextTest {

    @Mock
    private Context context = new MockContext();

    @Test
    public void persist() {
        List<Class<?>> classes = Arrays.asList(new Class<?>[]{BudgetEntry.class});
        PersistenceContextFactory pcf = new PersistenceContextFactoryImpl(context, classes);
        PersistenceContext persistenceContext = pcf.getPersistenceContext();
        persistenceContext.persist(new BudgetEntry(1, null, null));
    }
}


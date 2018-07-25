package com.mpathozulu.soft.ibudget.database.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mpathozulu.soft.ibudget.database.Column;
import com.mpathozulu.soft.ibudget.database.PersistenceContext;
import com.mpathozulu.soft.ibudget.database.Table;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Locale;

public class PersistenceContextImpl implements PersistenceContext {

    private static final String ID = "_id";

    private SQLiteOpenHelper sqLiteOpenHelper;

    PersistenceContextImpl(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    @Override
    public boolean persist(Object entity) {
        Class<?> clazz = entity.getClass();
        Table table = clazz.getAnnotation(Table.class);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String tableName = table.name();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals("id")) {
                values.put(ID, TableUtil.getColumnValue(field, entity, Long.class));
                continue;
            }
            Column column = TableUtil.geColumn(field);
            field.setAccessible(true);
            if (field.getType().equals(String.class)) {
                values.put(column.name(), TableUtil.getColumnValue(field, entity, String.class));
            }
            else if (field.getType().equals(Integer.class)) {
                values.put(column.name(), TableUtil.getColumnValue(field, entity, Integer.class));
            }
        }
        if (values.get(ID) != null) {
            String whereClause = String.format(Locale.getDefault(), "%s='%d'", ID, values.get(ID));
            db.update(tableName, values, whereClause, null);
        } else {
            Long id = db.insert(tableName, null, values);
            TableUtil.setColumnValue("id", entity, id);
        }
        db.close();
        return false;
    }

    @Override
    public boolean remove(Object entity) {
        return false;
    }

    @Override
    public boolean remove(Long id, Class<?> clazz) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        int result = db.delete(TableUtil.getTable(clazz).name(), String.format(Locale.getDefault(), "%s='%d'", ID, id), null);
        db.close();
        return result > 0;
    }

    @Override
    public <T> T find(Class<T> clazz, Long id) {
        return null;
    }

    @Override
    public <T> Collection<T> findAll(Class<T> clazz) {
        return null;
    }
}

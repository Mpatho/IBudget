package com.mpathozulu.ibudget.database.internal;

import com.mpathozulu.ibudget.database.Column;
import com.mpathozulu.ibudget.database.Table;

import java.lang.reflect.Field;
import java.util.Locale;

class TableUtil {

    private TableUtil() {
    }

    static String getTableDeclaration(Class<?> clazz) {
        Table table = getTable(clazz);
        StringBuilder stringBuilder = new StringBuilder(String.format(Locale.getDefault(), "CREATE TABLE %s (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,", table.name()));
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals("id")) {
                continue;
            }
            Column column = geColumn(field);
            stringBuilder.append(column.name() + " " + column.type());
            if (!column.nullable()) {
                stringBuilder.append(" NOT NULL");
            }
            if (column.unique()) {
                stringBuilder.append(" UNIQUE");
            }
            stringBuilder.append(",");
        }
        stringBuilder.setCharAt(stringBuilder.lastIndexOf(","), ')');
        return stringBuilder.append(";").toString();
    }

    static Table getTable(Class<?> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        if (table == null) {
            throw new RuntimeException("Class is not annotated with Table annotation");
        }
        return table;
    }

    static Column geColumn(Field field) {
        Column column = field.getAnnotation(Column.class);
        if (column == null) {
            throw new RuntimeException("Field is not annotated with Column annotation");
        }
        return column;
    }

    static <T> T getColumnValue(Field field, Object entity, Class<T> clazz) {
        try {
            field.setAccessible(true);
            return (T) field.get(entity);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static void setColumnValue(String name, Object entity, Object value) {
        try {
            Field field = entity.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(entity, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
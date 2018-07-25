package com.mpathozulu.soft.ibudget2.persist;

import java.util.SortedSet;

public interface Persistable<T> {
	String getDatabaseName();

	String[] getSchemaStatement();

	String[] getTablesName();

	void save(T object);

	void delete(T object);

	SortedSet<T> getItems(Object ... args);

}

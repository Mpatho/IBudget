package com.mpathozulu.ibudget.persistance;

import java.util.SortedSet;

public interface Persistence<T extends Persistable> {
	String getDatabaseName();

	String[] getSchemaStatement();

	String[] getTablesName();

	void save(T object);

	void delete(T object);

	SortedSet<T> getItems(Object ... args);

}

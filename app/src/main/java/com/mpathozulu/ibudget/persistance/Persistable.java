package com.mpathozulu.ibudget.persistance;

import java.io.Serializable;

public abstract class Persistable<T> implements Comparable<T>, Serializable {

    protected Long id;

    public Persistable(Long id) {
        this.id = id;
    }

    public boolean hasId() {
        return id != null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

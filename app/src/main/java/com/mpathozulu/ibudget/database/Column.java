package com.mpathozulu.ibudget.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Column {
    /**
     *
     * @return
     */
    String name();

    /**
     *
     * @return database type of the column
     */
    String type();

    /**
     *
     * @return true if the column can be null else false
     */
    boolean nullable() default true;

    /**
     *
     * @return true if the database column can accept duplicate
     */
    boolean unique() default false;

}

package com.base.annontation;

import java.lang.annotation.*;

/**
 * @desc: Created by Fengyun on 4/10/2018.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValueBind {
    fieldType type();

    String value();

    enum fieldType{
        STRING,INT
    };
}

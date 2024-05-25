package edu.phystech.hw5.annotation.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Size {
    int min() default 1;
    int max();
    String message() default "";
}

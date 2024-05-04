package edu.phystech.hw5.annotation.validation;

/**
 * @author kzlv4natoly
 */
public @interface Size {
    int min() default 1;
    int max();
    String message() default "";
}

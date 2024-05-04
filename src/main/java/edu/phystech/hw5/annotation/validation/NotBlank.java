package edu.phystech.hw5.annotation.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author kzlv4natoly
 */
@Target(ElementType.FIELD)
public @interface NotBlank {
    String message() default "";
}

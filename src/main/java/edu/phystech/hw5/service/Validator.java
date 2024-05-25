package edu.phystech.hw5.service;
import java.lang.reflect.Field;

/**
 * @author kzlv4natoly
 */

public interface Validator {
    void validate(Object object) throws IllegalAccessException;
    void validateNotBlank(Field inputField, Object object) throws IllegalAccessException;
}

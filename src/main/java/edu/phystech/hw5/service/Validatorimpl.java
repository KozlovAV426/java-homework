package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.validation.NotBlank;
import edu.phystech.hw5.annotation.validation.Size;
import java.lang.reflect.Field;
import edu.phystech.hw5.exception.ValidationException;

public class Validatorimpl implements Validator {
    void validateSize(Field inputField, Object inputObject) throws IllegalAccessException {
        Object fieldValue = inputField.get(inputObject);
        Size fieldSize = inputField.getAnnotation(Size.class);
        if (fieldValue instanceof String && (fieldValue.toString().length() > fieldSize.max() || fieldValue.toString().length() < fieldSize.min())) {
            throw new ValidationException(fieldSize.message());
        }
    }
   void validateNotBlank(Field inputField, Object inputObject) throws IllegalAccessException {
        Object fieldValue = inputField.get(inputObject);
        if (fieldValue instanceof String && fieldValue.toString().isBlank()) {
            throw new ValidationException(inputField.getAnnotation(NotBlank.class).message());
        }
    }
  

    @Override
    public void validate(Object inputObject) throws IllegalAccessException {
        for (var singleField : inputObject.getClass().getDeclaredFields()) {
            singleField.setAccessible(true);

            if (singleField.isAnnotationPresent(NotBlank.class)) {
                validateNotBlank(singleField, inputObject);
            } else if (singleField.isAnnotationPresent(Size.class)) {
                validateSize(singleField, inputObject);
            } else {
                throw new RuntimeException();
            }
        }
    }

}

package peaksoft.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNullValidation  implements ConstraintValidator<NotNullValidationClass,String> {

    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        return !field.isEmpty();
    }

}



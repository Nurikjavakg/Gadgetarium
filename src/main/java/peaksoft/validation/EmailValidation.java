package peaksoft.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


    public class EmailValidation implements ConstraintValidator<EmailValidatian,String> {
        @Override
        public boolean isValid(String email, ConstraintValidatorContext context) {
            return (email.endsWith(".com") || email.endsWith(".ru")) && email.length() > 10 && (email.contains("@gmail") || email.contains("@mail"));
        }
    }



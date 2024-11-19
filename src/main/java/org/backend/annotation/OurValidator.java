package org.backend.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class OurValidator implements ConstraintValidator<OurValidation, String> {
    @Override
    public void initialize(OurValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if (password == null) {
            addConstraintViolation(context, "Password cannot be null");
            return false;
        }

        List<String> errors = new ArrayList<>();

        if (password.length() < 8) {
            errors.add("Password length must be more than 8 characters");
        }

        if (!password.matches(".*[A-Z].*")) {
            errors.add("The password must contain at least one capital letter.");
        }

        if (!password.matches(".*[a-z].*")) {
            errors.add("The password must contain at least one lowercase letter.");
        }

        if (!password.matches(".*\\d.*")) {
            errors.add("The password must contain at least one number.");
        }

        // ---------

        if (!errors.isEmpty()){
            for (String error: errors){
                addConstraintViolation(context,error);
            }
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message){
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}

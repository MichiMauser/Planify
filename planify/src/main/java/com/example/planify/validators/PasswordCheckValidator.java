package com.example.planify.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordCheckValidator implements ConstraintValidator<PasswordCheck, String> {
    private static final String digit_regex= ".*\\d.*";
    private static final String special_character= ".*[!@#$%^&*<>,.?:;'|].*";
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(s == null || s.isEmpty()){
            return false;
        }
        boolean hasDigit = s.matches(digit_regex);
        boolean hasSpecialCharacter = s.matches(special_character);

        return hasDigit && hasSpecialCharacter;

    }
}

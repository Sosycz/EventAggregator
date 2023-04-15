package com.example.eventapp.validation;

import com.example.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailTakenValidator implements ConstraintValidator<EmailTakenConstraint, String> {
    @Autowired
    UserService userService;
    @Override
    public void initialize(EmailTakenConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userService.getUserByEmail(email)==null;
    }
}

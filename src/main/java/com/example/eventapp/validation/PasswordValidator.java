package com.example.eventapp.validation;

import com.example.eventapp.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, UserDto> {

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword().equals(userDto.getPasswordRetype());


    }
}

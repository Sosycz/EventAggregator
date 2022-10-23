package com.example.eventapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeValidator implements ConstraintValidator<TimeConstraint, String> {

    @Override
    public boolean isValid(String time, ConstraintValidatorContext constraintValidatorContext) {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

            try {
                return (LocalDateTime.parse(time, dateTimeFormatter)
                        .isAfter(LocalDateTime.now()));

            } catch (DateTimeParseException e) {
                e.printStackTrace();
                return false;
            }
        }

    @Override
    public void initialize(TimeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}

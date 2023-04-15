package com.example.eventapp.validation;

import com.example.eventapp.dto.UserDto;
import com.example.eventapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UserNameConstraint, UserDto> {
    @Autowired
    UserService userService;
    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userService.getUserByName(userDto.getUserName())==null;
    }
}

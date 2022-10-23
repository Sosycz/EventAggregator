package com.example.eventapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameConstraint {


        String message() default "user name already in use";
        Class<?>[] groups() default {};
        Class<? extends Payload> [] payload() default {};
    }


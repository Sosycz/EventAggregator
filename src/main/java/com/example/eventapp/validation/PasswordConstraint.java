package com.example.eventapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( {ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Passwords do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}

package com.example.eventapp.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TimeValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeConstraint {
    String message() default "Time cannot refer to past.";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}

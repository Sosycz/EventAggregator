package com.example.eventapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailTakenValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface EmailTakenConstraint {
    String message() default "Email already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}

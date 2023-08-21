package com.mongo.patient.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePatientValidator.class)
@Documented
public @interface UniquePatient {
    String message() default "Patient with the same name and email already exists"; // Make sure this message is set
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


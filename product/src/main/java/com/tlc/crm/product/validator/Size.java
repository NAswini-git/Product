package com.tlc.crm.product.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SizeValidator.class)
@Documented
public @interface Size {

    String message() default "Give Valid inputs";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}

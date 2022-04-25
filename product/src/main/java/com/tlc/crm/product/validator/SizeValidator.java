package com.tlc.crm.product.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for product size
 *
 * @author AswiniN
 */
public class SizeValidator implements ConstraintValidator<Size, String> {

    /**
     * Validates product size.
     *
     * @param size
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(final String size, final ConstraintValidatorContext constraintValidatorContext) {
        return size != null && size.matches("(?i)s|m|l");
    }
}

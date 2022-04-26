package com.tlc.crm.product.validator;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.product.model.Product;
import com.tlc.crm.product.status.ProductErrorCode;
import com.tlc.validator.ModelValidator;
import com.tlc.validator.ValidatorAccess;

/**
 * Input Validator.
 *
 * @author AswiniN
 */
public class InputValidator {

    private static final ModelValidator MODEL_VALIDATOR = ValidatorAccess.get();

    /**
     * Validate inputs.
     *
     * @param product
     * @param optedClass
     */
    public static void validateInput(final Product product, final Class... optedClass) {

        if (!MODEL_VALIDATOR.isValid(product, optedClass)) {
            throw ErrorCode.get(ProductErrorCode.INVALID_INPUT_DATA);
        }
    }
}

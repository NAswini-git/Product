package com.tlc.crm.product.status;

import com.tlc.commons.code.ErrorCodeGroup;
import com.tlc.commons.code.ErrorCodeProvider;

/**
 * Product Error code implementation.
 */
public enum ProductErrorCode implements ErrorCodeProvider {

    INVALID_INPUT_DATA(0x01),
    ID_NOT_FOUND(0x02);

    private final int code;

    ProductErrorCode(int localCode) {
        this.code = Group.GROUP.getConvertedCode(localCode);
    }

    @Override
    public int getCode() {
        return code;
    }

    /**
     * Creates error code group for product data.
     */
    private static class Group implements ErrorCodeGroup {
        private static final ErrorCodeGroup GROUP = new Group();

        @Override
        public int getPrefix() {
            return 0x10_0_0000;
        }
    }
}

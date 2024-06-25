package com.shopnow.product.util;

public enum ErrorCatalog {
    PRODUCT_NOT_FOUND("ERR_PRODUCT_001", "Product not found."),
    INVALID_PRODUCT("ERR_PRODUCT_002", "Invalid product parameters."),
    GENERIC_ERROR("ERR_GENERIC_001", "An unexpected error occurred.");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

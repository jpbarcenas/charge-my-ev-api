package com.myev.charge.exception;

public class DuplicateLocationException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public DuplicateLocationException(String resourceName, String fieldName, String fieldValue) {
        // Post not found with id : 1
        super(String.format("%s already exist with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
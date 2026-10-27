package com.example.blog_app.exception;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldValue;
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("Resource %s not found with name %s and value %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}

package com.adrian.validate.util;

import java.util.*;

import org.springframework.validation.FieldError;

public class ValidateUtils {
    public static Map<String, String> transformErrorsToMap(Collection<FieldError> errors) {
        Map<String, String> map = new HashMap<>();
        for (FieldError error : errors) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }
}

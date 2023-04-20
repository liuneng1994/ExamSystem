package com.adrian.util;

public class AssertUtil {
    public static void assertNotNull(Object object) {
        assertNotNull(object, "Field cannot be null");
    }

    public static void assertNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertStringNotEmpty(String string) {
        assertStringNotEmpty(string, "");
    }

    public static void assertStringNotEmpty(String string, String message) {
        if (StringUtil.stringIsNullOrWhiteSpace(string)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertMinValue(int value, int min, String message) {
        if (value < min) {
            throw new IllegalArgumentException(message);
        }
    }
}

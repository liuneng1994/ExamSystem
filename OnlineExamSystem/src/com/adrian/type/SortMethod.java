package com.adrian.type;

/**
 * Sorts method type of list.
 */
public enum SortMethod {
    ASC, DESC;

    /**
     * Tranforms string to SortMethod type value.
     *
     * @param str
     *            method name
     * @return method value or null(don't find)
     */
    public static SortMethod fromString(String str) {
        for (SortMethod method : SortMethod.values()) {
            if (method.name().equalsIgnoreCase(str)) {
                return method;
            }
        }
        return null;
    }
}

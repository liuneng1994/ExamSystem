package com.adrian.common;

import java.util.HashMap;
import java.util.Map;

import com.adrian.util.StringUtil;

/**
 * Stores objects for every thread.
 */
public final class RequestContext {
    private static final ThreadLocal<Map<String, Object>> APP_CONTEXT = new ThreadLocal<>();

    /**
     * Sets attributes to context.
     *
     * @param key
     *            attribute name, it can not be null or empty string
     * @param value
     *            attribute value, it can not be null
     */
    public static void setContextAttribute(String key, Object value) {
        if (StringUtil.stringIsNullOrEmpty(key) || value == null) {
            throw new IllegalArgumentException("Key or value is null.");
        }
        Map<String, Object> map = APP_CONTEXT.get();
        if (map == null) {
            map = new HashMap<>();
            APP_CONTEXT.set(map);
        }
        map.put(key, value);
    }

    /**
     * Gets specified attribute.
     *
     * @param key
     *            attribute name
     * @return attribute value
     */
    public static Object getContextAttribute(String key) {
        Map<String, Object> map = APP_CONTEXT.get();
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    public static void clear() {
        Map<String, Object> map = APP_CONTEXT.get();
        if (map != null) {
            map.clear();
        }
    }
}

package com.adrian.util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class StringUtil {
    // empty string
    public final static String STRING_EMPTY = "";

    /**
     * Validates that string is null or empty.
     *
     * @param string
     *            string which need be validated
     * @return result of validating
     */
    public static boolean stringIsNullOrEmpty(String string) {
        return string == null || STRING_EMPTY.equals(string);
    }

    /**
     * Validates that string is null or white space.
     *
     * @param string
     *            string which need be validated
     * @return result of validating
     */
    public static boolean stringIsNullOrWhiteSpace(String string) {
        return string == null || STRING_EMPTY.equals(string.trim());
    }

    /**
     * Removes prefix from string. All arguments cannot be null. If argument is
     * null, throw IllegalArgumentException.
     *
     * @param string
     *            source string
     * @param prefix
     *            prefix string
     * @return if source string has prefix, return result. Otherwise empty
     *         string.
     */
    public static String removePrefix(String string, String prefix) {
        if (string == null || prefix == null) {
            throw new IllegalArgumentException("arguments can not be null");
        }
        if (stringIsNullOrWhiteSpace(string)) {
            return STRING_EMPTY;
        }
        if (string.startsWith(prefix)) {
            return string.substring(prefix.length());
        } else {
            return string;
        }
    }

    /**
     * Encodes string through MD5. String can not be null. If string is null, it
     * will throw IllegalArgumentException.
     *
     * @param str
     *            string which needs encode
     * @return cryptograph
     */
    public static String EncoderByMd5(String str) {
        if (str == null) {
            throw new IllegalArgumentException("String reference can not be null");
        }
        String newstr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();

            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newstr;
    }
}

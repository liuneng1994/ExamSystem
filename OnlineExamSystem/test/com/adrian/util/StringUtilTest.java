package com.adrian.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStringIsNullOrEmptyWhenStringIsNull() {
        String testString = null;
        boolean result = StringUtil.stringIsNullOrEmpty(testString);
        assertTrue(result);
    }

    @Test
    public void testStringIsNullOrEmptyWhenStringIsEmpty() {
        String testString = "";
        boolean result = StringUtil.stringIsNullOrEmpty(testString);
        assertTrue(result);
    }

    @Test
    public void testStringIsNullOrEmptyWhenStringIsWhiteSpace() {
        String testString = " ";
        boolean result = StringUtil.stringIsNullOrEmpty(testString);
        assertFalse(result);
    }

    @Test
    public void testStringIsNullOrWhiteSpaceWhenStringHasCharacter() {
        String testString = "test";
        boolean result = StringUtil.stringIsNullOrWhiteSpace(testString);
        assertFalse(result);
    }

    @Test
    public void testStringIsNullOrWhiteSpaceWhenStringIsNull() {
        String testString = "";
        boolean result = StringUtil.stringIsNullOrWhiteSpace(testString);
        assertTrue(result);
    }

    @Test
    public void testStringIsNullOrWhiteSpaceWhenStringIsWhiteSpace() {
        String testString = " ";
        boolean result = StringUtil.stringIsNullOrWhiteSpace(testString);
        assertTrue(result);
    }

    @Test
    public void testRemovePrefix() {
        String prefix = "/online";
        String testString = "/online/test";
        String expectedValue = "/test";
        String value = StringUtil.removePrefix(testString, prefix);
        assertEquals(expectedValue, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovePrefixWhenStringIsNull() {
        String prefix = "/online";
        String testString = null;
        StringUtil.removePrefix(testString, prefix);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovePrefixWhenPrefixIsNull() {
        String prefix = null;
        String testString = "/online/test";
        StringUtil.removePrefix(testString, prefix);
    }
}

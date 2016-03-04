package com.adrian.controller;

import javax.servlet.http.HttpSession;

import com.adrian.common.Constants;
import com.adrian.common.RequestContext;
import com.adrian.util.StringUtil;

/**
 * Super class of all controller class. There are many common functions.
 */
public abstract class BaseController {
    /**
     * Adds attribute to session.
     *
     * @param key
     *            attribute name
     * @param value
     *            attribute value
     */
    protected void setSessionAttribute(String key, Object value) {
        if (StringUtil.stringIsNullOrWhiteSpace(key)) {
            throw new IllegalArgumentException("Key can not be null or empty string");
        }
        HttpSession session = (HttpSession) RequestContext.getContextAttribute(Constants.REQUEST_CONTEXT_SESSION);
        session.setAttribute(key, value);
    }

    /**
     * Gets attribute from session.
     *
     * @param key
     *            attribute name
     * @return attribute value
     */
    protected Object getSessionAttribute(String key) {
        Object value = null;
        if (!StringUtil.stringIsNullOrWhiteSpace(key)) {
            HttpSession session = (HttpSession) RequestContext.getContextAttribute(Constants.REQUEST_CONTEXT_SESSION);
            value = session.getAttribute(key);
        }
        return value;
    }

    protected void InvalidateSession() {
        HttpSession session = (HttpSession) RequestContext.getContextAttribute(Constants.REQUEST_CONTEXT_SESSION);
        if (session != null) {
            session.setAttribute(Constants.SESSION_USER, null);
            session.invalidate();
        }
    }
}

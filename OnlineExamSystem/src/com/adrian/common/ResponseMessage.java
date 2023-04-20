package com.adrian.common;

import com.adrian.type.RequestResultType;

/**
 * Stores response message.
 */
public class ResponseMessage {
    private RequestResultType code;
    private Object message;

    public RequestResultType getCode() {
        return this.code;
    }

    public void setCode(RequestResultType code) {
        this.code = code;
    }

    public Object getMessage() {
        return this.message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}

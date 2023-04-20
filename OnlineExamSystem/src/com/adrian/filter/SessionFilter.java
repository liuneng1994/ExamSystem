package com.adrian.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.adrian.common.Constants;
import com.adrian.common.RequestContext;

public class SessionFilter implements Filter {

    @Override
    public void destroy() {
        // nothing to do
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        RequestContext.setContextAttribute(Constants.REQUEST_CONTEXT_SESSION, httpServletRequest.getSession());
        chain.doFilter(request, response);
        RequestContext.clear();
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // nothing to do
    }

}

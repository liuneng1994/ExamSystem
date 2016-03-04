package com.adrian.filter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import com.adrian.common.Constants;
import com.adrian.common.PermissionContainer;
import com.adrian.model.User;
import com.adrian.util.StringUtil;

/**
 * Validates user permission. If user has permission, continue.
 *
 */
public class PermissionFilter implements Filter {
    private PermissionContainer container = new PermissionContainer();
    private String prefix;
    private String suffix;

    @Override
    public void destroy() {
        // nothing to do
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        boolean result = false;
        String fromUrl = StringUtil.STRING_EMPTY;
        if (!httpServletRequest.getRequestURI().endsWith(this.suffix)
                || httpServletRequest.getContextPath().equals(StringUtil.STRING_EMPTY)) {
            User user = (User) httpServletRequest.getSession().getAttribute(Constants.SESSION_USER);
            if (user != null) {
                String simpleUri = StringUtil.removePrefix(httpServletRequest.getRequestURI(), this.prefix);
                result = this.container.hasPermission(user.getRoleType(), simpleUri);
            } else {
                fromUrl = "?fromUrl=" + httpServletRequest.getRequestURI();
            }
        } else {
            result = true;
        }

        if (result) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect(this.prefix + "/page/login" + fromUrl);
        }
    }

    /**
     * Initializes permission container;
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.prefix = fConfig.getInitParameter("prefix");
        this.suffix = fConfig.getInitParameter("suffix");
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            String path = this.getClass().getResource("/").getPath() + "config/permission-config.xml";
            document = reader.read(new File(path.substring(1)));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Element root = document.getRootElement();
        List<Element> elementList = root.elements("permission");
        for (Element element : elementList) {
            int roleType = Integer.valueOf(element.attribute("roleType").getValue());
            String uri = element.attribute("uri").getValue();
            this.container.addPermission(roleType, uri);
        }
    }
}

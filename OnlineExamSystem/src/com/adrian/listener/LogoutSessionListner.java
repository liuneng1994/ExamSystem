package com.adrian.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.adrian.common.Constants;
import com.adrian.model.User;
import com.adrian.util.LoginUsers;

public class LogoutSessionListner implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        User user = (User) se.getSession().getAttribute(Constants.SESSION_USER);
        if (user != null) {
            LoginUsers.deleteUser(user.getUserName());
        }
    }

    // @Override
    // public void valueBound(HttpSessionBindingEvent event) {
    // System.out.println(event.getSession().getId());
    // }
    //
    // @Override
    // public void valueUnbound(HttpSessionBindingEvent event) {
    // User user = (User)
    // event.getSession().getAttribute(Constants.SESSION_USER);
    // if (user != null) {
    // LoginUsers.deleteUser(user.getUserName());
    // }
    // }

}

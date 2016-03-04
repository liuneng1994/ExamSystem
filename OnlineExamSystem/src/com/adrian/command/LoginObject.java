package com.adrian.command;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginObject {
    @NotEmpty(message = "{login.username.null}")
    private String userName;
    @NotEmpty(message = "{login.password.null}")
    private String password;
    private boolean remembered = false;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemembered() {
        return remembered;
    }

    public void setRemembered(boolean remembered) {
        this.remembered = remembered;
    }
}

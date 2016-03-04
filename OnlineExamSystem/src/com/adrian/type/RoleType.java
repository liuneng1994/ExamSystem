package com.adrian.type;

/**
 * User types. {@code code} indicates value stored in database.
 */
public enum RoleType {
    SYS_ADMIN(1), CONTENT_ADMIN(2), STUDENT(3), TEACHER(4);

    private int code;

    private RoleType(int code) {
        this.setCode(code);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static RoleType valueOfInt(int code) {
        RoleType result = null;
        for (RoleType roleType : RoleType.values()) {
            if (roleType.getCode() == code) {
                result = roleType;
                break;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException(String.format("There is not this role type code(%d)", code));
        }
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }
}

package com.adrian.type;

/**
 * Gender.
 */
public enum Gender {
    MALE(0, "ÄÐ"), FEMALE(1, "Å®");

    private int code;
    private String description;

    private Gender(int code, String description) {
        this.setCode(code);
        this.setDescription(description);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Gender valueOfInt(int code) {
        Gender result = null;
        for (Gender gender : Gender.values()) {
            if (gender.getCode() == code) {
                result = gender;
                break;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException(String.format("There is not this gender code(%d)", code));
        }
        return result;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

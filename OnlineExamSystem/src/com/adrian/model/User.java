package com.adrian.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.*;

import com.adrian.validate.group.*;

/**
 * User informations.
 */
public class User {

    private Integer id;

    @NotEmpty(message = "{user.username.null}", groups = { ValidateCreate.class, ValidateUpdate.class })
    @Length(min = 1, max = 32, message = "{user.username.length}", groups = { ValidateCreate.class })
    private String userName;

    @Length(min = 4, max = 32, message = "{user.password.length}", groups = { ValidateUpdate.class })
    private String password;

    // 0 male; 1 female
    @NotNull(message = "{username.gender.null}", groups = { ValidateCreate.class, ValidateUpdate.class })
    @Range(min = 0, max = 1, message = "{username.gender.range}", groups = { ValidateCreate.class,
            ValidateUpdate.class })
    private Integer gender;

    // 1 sysadmin; 2 contentadmin; 3 teacher; 4 student
    @NotNull(message = "{user.roletype.null}", groups = { ValidateCreate.class, ValidateUpdate.class })
    @Range(min = 1, max = 4, message = "{username.gender.range}", groups = { ValidateCreate.class,
            ValidateUpdate.class })
    private Integer roleType;

    @Length(max = 20, message = "{user.phone.length}", groups = { ValidateCreate.class, ValidateUpdate.class,
            ValidatePerson.class })
    private String phoneNumber;

    @Length(max = 125, message = "{user.email.length}", groups = { ValidateCreate.class, ValidateUpdate.class,
            ValidatePerson.class })
    private String email;

    @Length(max = 32, message = "{user.chinesename.length}", groups = { ValidateCreate.class, ValidateUpdate.class,
            ValidatePerson.class })
    private String chineseName;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer code) {
        this.gender = code;
    }

    public Integer getRoleType() {
        return this.roleType;
    }

    public void setRoleType(Integer code) {
        this.roleType = code;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChineseName() {
        return this.chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

}

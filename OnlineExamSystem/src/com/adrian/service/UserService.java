package com.adrian.service;

import java.util.List;

import com.adrian.command.BasePagination;
import com.adrian.model.User;

public interface UserService {
    /**
     * Validates user login information, if correct, it will clear password
     * field and return user information.
     *
     * @param userName
     *            user name
     * @param password
     *            password
     * @return User Information(success) or null(failed)
     */
    User login(String userName, String password);

    /**
     * Creates new user.
     *
     * @param user
     *            information of new user
     * @return if create successfully, user id. Otherwise -1
     */
    int addUser(User user);

    /**
     * Deletes user through user id.
     *
     * @param ids
     *            array of user id
     * @return if success, result is true. Otherwise is false.
     */
    boolean deleteUsers(int[] ids);

    /**
     * Queries users through pagination information
     *
     * @param pagination
     *            pagination information
     * @return list of user
     */
    List<User> getUsers(BasePagination pagination);

    /**
     * Updates user information through specified id or user name.
     *
     * @param user
     *            user information
     * @return true, if update success, or false, update failed
     * @throws IllegalArgumentException
     *             if user is illegal
     */
    boolean updateUser(User user);

    /**
     * Updates user password.
     *
     * @param user
     *            user information(must have id and new password)
     */
    void updateUserPassword(User user);

    /**
     * Gets user information through specified id.
     *
     * @param id
     *            user id
     * @return user object
     */
    User getUserById(int id);
}

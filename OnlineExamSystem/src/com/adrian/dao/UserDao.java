package com.adrian.dao;

import java.util.List;

import com.adrian.command.BasePagination;
import com.adrian.model.User;

/**
 * Supports method to access user information.
 */
public interface UserDao {
    /**
     * Gets user informtion through user name.
     *
     * @param userName
     *            user name
     * @return user information object
     */
    User getUserByName(String userName);

    /**
     * Creates new user.
     *
     * @param user
     *            user information object.
     * @return user id
     */
    int addUser(User user);

    /**
     * Deletes user information through user id.(logic delete)
     *
     * @param ids
     *            array of id
     */
    void deleteUsers(int[] ids);

    /**
     * Gets count of users which user name like keyword in pagination.
     *
     * @param keyword
     *            keyword of user name
     * @return user count
     */
    int getUserCountByPagination(String keyword);

    /**
     * Gets users through pagination information.
     *
     * @param pagination
     *            pagination information
     * @return user list or empty list, if no record
     */
    List<User> getUsers(BasePagination pagination);

    /**
     * Updates specified user's base information(except user name and password).
     *
     * @param user
     *            user information(user id andd user name cannot be both empty)
     */
    void updateUserBaseInfo(User user);

    /**
     * Update specified user's password.
     *
     * @param user
     *            user information(user id andd user name cannot be both empty)
     */
    void updateUserPassword(User user);

    /**
     * Gets user record of specified id.
     * 
     * @param id
     *            user id
     * @return user object
     */
    User getUserById(int id);
}

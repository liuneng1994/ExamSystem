package com.adrian.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Records users which have login.
 */
public class LoginUsers {
    private static Set<String> users = new HashSet<>();

    /**
     * Adds a new user record.
     *
     * @param userName
     *            user name
     * @return true, if user name not exist, otherwise false
     */
    public static boolean addUser(String userName) {
        if (contains(userName)) {
            return false;
        }

        users.add(userName);
        return true;
    }

    /**
     * Deletes a new user record.
     *
     * @param userName
     *            user name
     * @return true, if user name exist, otherwise false
     */
    public static boolean deleteUser(String userName) {
        if (!contains(userName)) {
            return false;
        }

        users.remove(userName);
        return true;
    }

    /**
     * Return true, if contains the sepecified user, otherwise false
     *
     * @param userName
     *            user name
     * @return true, if contains the sepecified user, otherwise false
     */
    public static boolean contains(String userName) {
        return users.contains(userName);
    }
}

package com.adrian.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.adrian.util.StringUtil;

/**
 * Permissions information container. Supports to store permission information.
 *
 * @author Administrator
 *
 */
public final class PermissionContainer {
    private Map<Integer, Set<String>> container;

    /**
     * Initializes container.
     */
    public PermissionContainer() {
        this.container = new HashMap<>();
    }

    /**
     * Add permission to container. If uri is empty string or null, throws
     * IllegalArgumentException.
     *
     * @param roleType
     *            code of role type
     * @param uri
     *            uri which can be accessed by this role type
     *
     */
    public void addPermission(int roleType, String uri) {
        if (StringUtil.stringIsNullOrEmpty(uri)) {
            throw new IllegalArgumentException("Uri can not be null or empty string.");
        }
        Set<String> uriSet = this.container.get(Integer.valueOf(roleType));
        uriSet = uriSet == null ? new HashSet<>() : uriSet;
        if (uriSet.add(uri)) {
            this.container.put(Integer.valueOf(roleType), uriSet);
        }
    }

    /**
     * Validates role type having specified permission
     *
     * @param roleType
     *            code of role type
     * @param uri
     *            uri which can be accessed by this role type
     * @return if has permission, result is true, otherwise false.
     */
    public boolean hasPermission(int roleType, String uri) {
        boolean result = false;
        Set<String> uriSet = this.container.get(Integer.valueOf(roleType));
        if (uriSet == null) {
            result = false;
        } else {
            result = uriSet.contains(uri);
        }
        return result;
    }

    /**
     * Deletes permission of specified role type.
     *
     * @param roleType
     *            code of role type
     * @param uri
     *            uri which can be accessed by this role type
     * @return if delete success, result is true. Otherwise result is false.
     */
    public boolean deletePermission(int roleType, String uri) {
        if (StringUtil.stringIsNullOrEmpty(uri) || !this.hasPermission(roleType, uri)) {
            return false;
        }
        this.container.get(roleType).remove(uri);
        return true;
    }

    /**
     * Clears container.
     */
    public void clear() {
        this.container.clear();
    }
}
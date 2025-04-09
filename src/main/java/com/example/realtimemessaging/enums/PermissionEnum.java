package com.example.realtimemessaging.enums;

import org.springframework.security.core.GrantedAuthority;

public enum PermissionEnum implements GrantedAuthority {
    GET_USER,
    GET_ALL_USERS,
    EDIT_ONE_USER,

//    ROLE
    ADD_ROLE,
    DELETE_ROLE,
    EDIT_ROLE,
    GET_ROLES,

    ADD_MESSAGE,
    EDIT_OWN_MESSAGE,
    DELETE_OWN_MESSAGE,
    DELETE_MESSAGE,
    GET_ALL_MESSAGES;

    @Override
    public String getAuthority() {
        return this.name();
    }
}

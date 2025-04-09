package com.example.realtimemessaging.enums;

import org.springframework.security.core.GrantedAuthority;

public enum PermissionEnum implements GrantedAuthority {
    ADD_USER,
    DELETE_USER,
    EDIT_USER,
    GET_USERS;

    @Override
    public String getAuthority() {
        return this.name();
    }
}

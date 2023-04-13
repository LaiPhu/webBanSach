package com.example.webSpring.api.enums;

import lombok.Getter;

@Getter
public enum UserPermission {
    ADMIN("admin", 1),
    MANAGER("manage", 2),
    USER("user", 3);

    private final Integer code;
    private final String messageKey;

    UserPermission(String messageKey, Integer code) {
        this.code = code;
        this.messageKey = messageKey;
    }
}

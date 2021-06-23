package com.sillock.core.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * created by soyeon 21/06/23
 */
@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;
}

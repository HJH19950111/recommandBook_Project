package com.project.recommandBook.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnumUserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String value;
}

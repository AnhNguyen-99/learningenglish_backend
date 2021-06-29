package com.learningenglish.entiy;

public enum RoleName {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String type;

    private RoleName(String type) {
        this.type = type;
    }

    public String toString() {
        return this.type;
    }
}

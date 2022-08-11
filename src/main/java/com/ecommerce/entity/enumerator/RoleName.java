package com.ecommerce.entity.enumerator;

public enum RoleName {

    ADMIN("ADMIN"),
    USER("USER");

    public final String roleName;

    RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

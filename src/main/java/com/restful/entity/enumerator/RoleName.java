package com.restful.entity.enumerator;

public enum RoleName {

    ADMIN("ADMIN"),
    CONSUMER("CONSUMER"),
    SUPPLIER("SUPPLIER");

    public final String roleName;

    RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

package com.codecool.onlineshop.models;

public enum Role {
    ADMIN(1), CUSTOMER(2);

    final int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}

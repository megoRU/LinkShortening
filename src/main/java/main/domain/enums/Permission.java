package main.domain.enums;

public enum Permission {
    USER("user:write"),
    ADMIN("user:admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
package com.epam.jwd.dto;

public class UserDto {
    private long id;
    private String login;
    private String[] roles;

    public UserDto(long id, String login, String[] roles) {
        this.id = id;
        this.login = login;
        this.roles = roles;
    }

    public String[] getRoles() {
        return roles;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}

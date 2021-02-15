package com.epam.jwd.domain.impl;

import com.epam.jwd.domain.Entity;

import java.util.List;
import java.util.Objects;

public class User extends Entity {
    private String login;
    private String password;
    private List<UserRole> roles;

    public User() {
    }

    public User(String login, String password, List<UserRole> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public User(long id, String login, String password, List<UserRole> roles) {
        super(id);
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", roles=" + roles +
                '}';
    }
}

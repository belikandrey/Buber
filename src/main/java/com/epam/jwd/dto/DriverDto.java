package com.epam.jwd.dto;

public class DriverDto {
    private long id;
    private String login;
    private String name;
    private String phoneNumber;
    private String email;
    private String rating;
    private String status;

    public DriverDto(long id, String login, String name, String phoneNumber, String email, String rating, String status) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rating = rating;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getRating() {
        return rating;
    }

    public String getStatus() {
        return status;
    }
}

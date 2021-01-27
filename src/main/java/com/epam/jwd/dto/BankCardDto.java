package com.epam.jwd.dto;

public class BankCardDto {
    private long id;
    private String clientLogin;
    private String number;
    private String date;

    public BankCardDto(long id, String clientLogin, String number, String date) {
        this.id = id;
        this.clientLogin = clientLogin;
        this.number = number;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }
}

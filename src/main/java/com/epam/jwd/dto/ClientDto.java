package com.epam.jwd.dto;

public class ClientDto {
    private long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String rating;
    private String status;
    private int bonus;
    private int countRide;

    public ClientDto(long id, String name, String phoneNumber, String email, String rating, String status, int bonus, int countRide) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rating = rating;
        this.status = status;
        this.bonus = bonus;
        this.countRide = countRide;
    }

    public long getId() {
        return id;
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

    public int getBonus() {
        return bonus;
    }

    public int getCountRide() {
        return countRide;
    }
}

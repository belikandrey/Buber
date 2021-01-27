package com.epam.jwd.dto;

public class CarDto {
    private long id;
    private String driverLogin;
    private String number;
    private String brand;
    private String model;
    private String color;

    public CarDto(long id, String driverLogin, String number, String brand, String model, String color) {
        this.id = id;
        this.driverLogin = driverLogin;
        this.number = number;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getDriverLogin() {
        return driverLogin;
    }

    public String getNumber() {
        return number;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }
}

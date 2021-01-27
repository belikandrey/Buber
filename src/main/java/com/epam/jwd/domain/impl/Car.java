package com.epam.jwd.domain.impl;

import com.epam.jwd.domain.Entity;

import java.util.Objects;

public class Car extends Entity {
    private Driver driver;
    private String number;
    private String brand;
    private String model;
    private String color;

    public Car() {
    }

    public Car(Driver driver, String number, String brand, String model, String color) {
        this.driver = driver;
        this.number = number;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Car(long id, Driver driver, String number, String brand, String model, String color) {
        super(id);
        this.driver = driver;
        this.number = number;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(driver, car.driver) && Objects.equals(number, car.number) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driver, number, brand, model, color);
    }

    @Override
    public String toString() {
        return "Car{" +
                "driver=" + driver +
                ", number='" + number + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

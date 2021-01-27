package com.epam.jwd.domain.impl;

import java.util.List;
import java.util.Objects;

public class Driver extends User{
    private String name;
    private String phoneNumber;
    private String email;
    private DriverStatus status;
    private Rating rating;

    private Driver(){}

    public static Builder newBuilder(){
        return new Driver().new Builder();
    }

    public class Builder{

        private Builder(){}

        public Builder addName(String name){
            Driver.this.name = name;
            return this;
        }

        public Builder addPhoneNumber(String number){
            Driver.this.phoneNumber = number;
            return this;
        }

        public Builder addEmail(String email){
            Driver.this.email = email;
            return this;
        }

        public Builder addRating(Rating rating){
            Driver.this.rating = rating;
            return this;
        }

        public Builder addStatus(DriverStatus status){
            Driver.this.status = status;
            return this;
        }

        public Builder addId(long id){
            Driver.this.setId(id);
            return this;
        }

        public Builder addLogin(String login){
            Driver.this.setLogin(login);
            return this;
        }

        public Builder addPassword(String password){
            Driver.this.setPassword(password);
            return this;
        }

        public Builder addRoles(List<UserRole> roleList){
            Driver.this.setRoles(roleList);
            return this;
        }

        public Driver build(){
            return Driver.this;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) && Objects.equals(phoneNumber, driver.phoneNumber) && Objects.equals(email, driver.email) && status == driver.status && rating == driver.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, phoneNumber, email, status, rating);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", rating=" + rating +
                '}';
    }
}

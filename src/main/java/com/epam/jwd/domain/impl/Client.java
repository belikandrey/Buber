package com.epam.jwd.domain.impl;

import java.util.List;
import java.util.Objects;

public class Client extends User {
    private String name;
    private String phoneNumber;
    private String email;
    private Rating rating;
    private ClientStatus status;
    private int bonusPercent;
    private int countRide;

    public static Builder newBuilder() {
        return new Client().new Builder();
    }

    private Client() {
    }

    public class Builder {

        private Builder() {
        }

        public Builder addName(String name) {
            Client.this.name = name;
            return this;
        }

        public Builder addPhoneNumber(String number) {
            Client.this.phoneNumber = number;
            return this;
        }

        public Builder addEmail(String email) {
            Client.this.email = email;
            return this;
        }

        public Builder addRating(Rating rating) {
            Client.this.rating = rating;
            return this;
        }

        public Builder addStatus(ClientStatus status) {
            Client.this.status = status;
            return this;
        }

        public Builder addBonus(int bonus) {
            Client.this.bonusPercent = bonus;
            return this;
        }

        public Builder addCountRide(int count) {
            Client.this.countRide = count;
            return this;
        }

        public Builder addId(long id) {
            Client.this.setId(id);
            return this;
        }

        public Builder addLogin(String login) {
            Client.this.setLogin(login);
            return this;
        }

        public Builder addPassword(String password) {
            Client.this.setPassword(password);
            return this;
        }

        public Builder addRoles(List<UserRole> roleList) {
            Client.this.setRoles(roleList);
            return this;
        }

        public Client build() {
            return Client.this;
        }

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

    public Rating getRating() {
        return rating;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public int getBonusPercent() {
        return bonusPercent;
    }

    public int getCountRide() {
        return countRide;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    public void setBonusPercent(int bonusPercent) {
        this.bonusPercent = bonusPercent;
    }

    public void setCountRide(int countRide) {
        this.countRide = countRide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return bonusPercent == client.bonusPercent && countRide == client.countRide && Objects.equals(name, client.name) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(email, client.email) && rating == client.rating && status == client.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, phoneNumber, email, rating, status, bonusPercent, countRide);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                ", status=" + status +
                ", bonusPercent=" + bonusPercent +
                ", countRide=" + countRide +
                '}';
    }
}

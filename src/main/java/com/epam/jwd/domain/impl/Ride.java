package com.epam.jwd.domain.impl;

import com.epam.jwd.domain.Entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ride extends Entity {
    private Client client;
    private Driver driver;
    private Location startLocation;
    private Location endLocation;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Rating clientMark;
    private Rating driverMark;
    private double distance;

    public static Builder newBuilder(){
        return new Ride().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder addClient(Client client){
            Ride.this.client = client;
            return this;
        }

        public Builder addDriver(Driver driver){
            Ride.this.driver = driver;
            return this;
        }

        public Builder addId(long id){
            Ride.this.setId(id);
            return this;
        }

        public Builder addStartLocation(Location startLocation){
            Ride.this.startLocation = startLocation;
            return this;
        }

        public Builder addEndLocation(Location endLocation){
            Ride.this.endLocation = endLocation;
            return this;
        }

        public Builder addStartDate(LocalDateTime startDate){
            Ride.this.startDate = startDate;
            return this;
        }

        public Builder addEndDate(LocalDateTime endDate){
            Ride.this.endDate = endDate;
            return this;
        }

        public Builder addClientMark(Rating clientMark){
            Ride.this.clientMark = clientMark;
            return this;
        }

        public Builder addDriverMark(Rating driverMark){
            Ride.this.driverMark = driverMark;
            return this;
        }

        public Builder addDistance(double distance){
            Ride.this.distance = distance;
            return this;
        }

        public Ride build(){
            return Ride.this;
        }
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Rating getClientMark() {
        return clientMark;
    }

    public void setClientMark(Rating clientMark) {
        this.clientMark = clientMark;
    }

    public Rating getDriverMark() {
        return driverMark;
    }

    public void setDriverMark(Rating driverMark) {
        this.driverMark = driverMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride = (Ride) o;
        return Double.compare(ride.distance, distance) == 0 && Objects.equals(client, ride.client) && Objects.equals(driver, ride.driver) && Objects.equals(startLocation, ride.startLocation) && Objects.equals(endLocation, ride.endLocation) && Objects.equals(startDate, ride.startDate) && Objects.equals(endDate, ride.endDate) && clientMark == ride.clientMark && driverMark == ride.driverMark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, driver, startLocation, endLocation, startDate, endDate, clientMark, driverMark, distance);
    }

    @Override
    public String toString() {
        return "Ride{" +
                "client=" + client +
                ", driver=" + driver +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", clientMark=" + clientMark +
                ", driverMark=" + driverMark +
                ", distance=" + distance +
                '}';
    }
}

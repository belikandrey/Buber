package com.epam.jwd.dto;

import java.time.LocalDateTime;

public class RideDto {
    private long id;
    private String clientLogin;
    private String driverLogin;
    private double distance;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public RideDto(String clientLogin, String driverLogin, double distance, LocalDateTime startDate, LocalDateTime endDate) {
        this.clientLogin = clientLogin;
        this.driverLogin = driverLogin;
        this.distance = distance;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public String getDriverLogin() {
        return driverLogin;
    }

    public double getDistance() {
        return distance;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}

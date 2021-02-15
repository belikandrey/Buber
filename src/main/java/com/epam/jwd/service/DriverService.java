package com.epam.jwd.service;

import com.epam.jwd.domain.impl.*;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DriverService {

    Optional<Driver> findByLogin(String login) throws ServiceException;

    boolean add(Driver driver) throws ServiceException, ValidationException;

    List<Ride> findAvailableRides() throws ServiceException;

    List<Driver> findNotConfirmedDrivers() throws ServiceException;

    List<Driver> findAll() throws ServiceException;

    boolean acceptRide(String login, Ride ride) throws ServiceException;

    boolean addEmail(String login, String email) throws ServiceException, ValidationException;

    boolean addCar(Car car) throws ServiceException, ValidationException;

    List<Car> findDriversCars(String login) throws ServiceException;

    void addRole(UserRole role, Long userId) throws ServiceException;

    void updateRideEndDate(Long rideId, LocalDateTime dateTime) throws ServiceException;

    void updateRating(String login, int rating) throws ServiceException;

    void updatePassword(Driver driver) throws ServiceException;

    Optional<Payment> findPaymentByRideId(Long rideId) throws ServiceException;

    void updateRideDriver(Ride ride, Driver driver) throws ServiceException;

    boolean updateStatus(String login, DriverStatus status) throws ServiceException;

    Optional<Ride> findRideById(Long id) throws ServiceException;


}

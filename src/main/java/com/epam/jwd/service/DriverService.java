package com.epam.jwd.service;

import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    Optional<Driver> findByLogin(String login) throws ServiceException, ValidationException;


    boolean add(Driver driver) throws ServiceException, ValidationException;
    List<Ride> findAvailableRides() throws ServiceException;

    List<Driver> findNotConfirmedDrivers() throws ServiceException;

    List<Driver> findAll() throws ServiceException;

    boolean acceptRide(String login, Ride ride) throws ServiceException;

    boolean addEmail(String login, String email) throws ServiceException, ValidationException;

    boolean addCar(Car car) throws ServiceException, ValidationException;

    List<Car> findDriversCars(String login) throws ServiceException, ValidationException;
}

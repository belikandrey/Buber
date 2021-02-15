package com.epam.jwd.service;

import com.epam.jwd.domain.impl.*;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Optional<User> findByLogin(String login) throws ValidationException, ServiceException;

    boolean updateDriverStatus(String login, DriverStatus status) throws ServiceException;

    boolean updateClientStatus(String login, ClientStatus status) throws ServiceException;

    boolean addAdmin(User user) throws ServiceException, ValidationException;

    List<Ride> findAllRides() throws ServiceException;

    List<Payment> findAllPayments() throws ServiceException;

    boolean confirmDriver(String login) throws ServiceException, ValidationException;

    List<Driver> findAllDriversWithBadRating() throws ServiceException;

    List<Client> findAllClientsWithBadRating() throws ServiceException;

    boolean removeAdmin(User user) throws ServiceException;

    boolean updateClientRating(String login, int rating) throws ServiceException;

    boolean updateDriverRating(String login, int rating) throws ServiceException;


}

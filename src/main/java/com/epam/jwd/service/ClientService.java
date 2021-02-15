package com.epam.jwd.service;

import com.epam.jwd.domain.impl.*;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Optional<Client> findByLogin(String login) throws ServiceException;

    boolean addBankCard(BankCard bankCard) throws ValidationException, ServiceException;

    boolean addEmail(String login, String email) throws ValidationException, ServiceException;

    boolean doPay(Payment payment) throws ServiceException;

    boolean addClient(Client client) throws ServiceException, ValidationException;

    List<Client> findAll() throws ServiceException;

    void updateRideDriverMark(Long id, int newMark) throws ServiceException;

    List<Ride> findRidesByClientId(Long id) throws ServiceException;

    Optional<Ride> findRideById(Long id) throws ServiceException;

    Optional<Ride> findRideByClientIdAndStartLocationId(Long clientId, Long locationId) throws ServiceException;

    List<BankCard> findAllClientCard(String login) throws ServiceException;

    void addRole(Long id, UserRole role) throws ServiceException;

    Optional<Location> findLocationByLatitudeLongitude(Location location) throws ServiceException;

    void addRide(Ride ride) throws ServiceException;

    void addLocation(Location location) throws ServiceException;

    boolean updateStatus(String login, ClientStatus status) throws ServiceException;

    boolean updatePassword(Client client) throws ServiceException;

    void updatePhoneNumber(Long id, String newNumber) throws ServiceException, ValidationException;

    void updateRating(String login, int rating) throws ServiceException;

    double calculatePrice(double distance, double bonus);

}

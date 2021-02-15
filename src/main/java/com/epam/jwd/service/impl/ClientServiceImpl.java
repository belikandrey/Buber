package com.epam.jwd.service.impl;

import com.epam.jwd.dao.impl.*;
import com.epam.jwd.domain.impl.*;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.validator.Validator;
import com.epam.jwd.validator.impl.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {

    private ClientServiceImpl() {
    }

    private static ClientServiceImpl clientService;

    public static ClientServiceImpl getInstance() {
        if (clientService == null) {
            clientService = new ClientServiceImpl();
        }
        return clientService;
    }

    private final Validator<String> phoneNumberValidator = PhoneNumberValidator.PHONE_NUMBER_VALIDATOR;
    private final Validator<String> loginValidator = LoginValidator.LOGIN_VALIDATOR;
    private final Validator<String> cardNumberValidator = CardNumberValidator.CARD_NUMBER_VALIDATOR;
    private final Validator<String> cardDateValidator = CardDateValidator.CARD_DATE_VALIDATOR;
    private final Validator<String> emailValidator = EmailValidator.EMAIL_VALIDATOR;
    private final ClientDao clientDao = ClientDao.CLIENT_DAO;
    private final BankCardDao bankCardDao = BankCardDao.BANK_CARD_DAO;
    private final RideDAO rideDAO = RideDAO.RIDE_DAO;
    private final LocationDao locationDao = LocationDao.LOCATION_DAO;
    private final PaymentDao paymentDao = PaymentDao.PAYMENT_DAO;
    private final UserDao userDao = UserDao.USER_DAO;


    @Override
    public Optional<Client> findByLogin(String login) throws ServiceException {
        try {
            return clientDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Client DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public boolean addBankCard(BankCard bankCard) throws ValidationException, ServiceException {
        if (!cardNumberValidator.validate(bankCard.getNumber())) {
            throw new ValidationException("Card number : " + bankCard.getNumber() + " is invalid");
        } else if (!cardDateValidator.validate(bankCard.getDate())) {
            throw new ValidationException("Card date : " + bankCard.getDate() + " is invalid");
        }
        try {
            return bankCardDao.add(bankCard);
        } catch (DaoException e) {
            throw new ServiceException("Bank card DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public boolean addEmail(String login, String email) throws ValidationException, ServiceException {
        if (!loginValidator.validate(login)) {
            throw new ValidationException("Login : " + login + " is invalid");
        } else if (!emailValidator.validate(email)) {
            throw new ValidationException("Email : " + email + " is invalid");
        }
        try {
            return clientDao.updateEmail(login, email);
        } catch (DaoException e) {
            throw new ServiceException("Client DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public boolean doPay(Payment payment) throws ServiceException {
        try {
            return paymentDao.add(payment);
        } catch (DaoException e) {
            throw new ServiceException("Payment DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public boolean addClient(Client client) throws ServiceException, ValidationException {
        if (!loginValidator.validate(client.getLogin())) {
            throw new ValidationException("Login : " + client.getLogin() + " is invalid");
        } else if (!emailValidator.validate(client.getEmail())) {
            throw new ValidationException("Email : " + client.getEmail() + " is invalid");
        } else if (!phoneNumberValidator.validate(client.getPhoneNumber())) {
            throw new ValidationException("Phone number : " + client.getPhoneNumber() + " is invalid");
        }
        try {
            Optional<User> userOptional = UserDao.USER_DAO.findByLogin(client.getLogin());
            if (userOptional.isPresent()) {
                return clientDao.add(client);
            }
        } catch (DaoException e) {
            throw new ServiceException("User DAO provides exception in service : " + e.getMessage());
        }
        Connection connection = clientDao.getConnection();
        try {
            connection.setAutoCommit(false);
            UserDao.USER_DAO.add(client, connection);
            ClientDao.CLIENT_DAO.add(client, connection);
            connection.commit();
            return true;
        } catch (SQLException | DaoException exception) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new ServiceException("Rollback error : " + e.getMessage());
            }
            throw new ServiceException("User or client DAO provides exception in service : " + exception.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new ServiceException("Connection dont closed : " + e.getMessage());
            }
        }
    }

    @Override
    public List<Client> findAll() throws ServiceException {
        try {
            return clientDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Client DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void updateRideDriverMark(Long id, int newMark) throws ServiceException {
        try {
            rideDAO.updateDriverMark(id, newMark);
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public List<Ride> findRidesByClientId(Long id) throws ServiceException {
        try {
            return rideDAO.findRidesByClientId(id);
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public Optional<Ride> findRideById(Long id) throws ServiceException {
        try {
            return rideDAO.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service" + e.getMessage());
        }
    }

    @Override
    public Optional<Ride> findRideByClientIdAndStartLocationId(Long clientId, Long locationId) throws ServiceException {
        try {
            return rideDAO.findRideByClientIdAndStartLocationId(clientId, locationId);
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public List<BankCard> findAllClientCard(String login) throws ServiceException {
        try {
            return bankCardDao.findAll().stream().
                    filter((p)->p.getClient().getLogin().equals(login)).
                    collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Bank Card DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void addRole(Long id, UserRole role) throws ServiceException {
        try {
            userDao.addRole(role, id);
        } catch (DaoException e) {
            throw new ServiceException("User DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public Optional<Location> findLocationByLatitudeLongitude(Location location) throws ServiceException {
        try {
            return locationDao.findEntityByCoords(location.getLatitude(), location.getLongitude());
        } catch (DaoException e) {
            throw new ServiceException("Location DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void addRide(Ride ride) throws ServiceException {
        try {
            rideDAO.addMinRide(ride);
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void addLocation(Location location) throws ServiceException {
        try {
            locationDao.add(location);
        } catch (DaoException e) {
            throw new ServiceException("Location DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public boolean updateStatus(String login, ClientStatus status) throws ServiceException {
        try {
            return clientDao.updateStatus(login, status);
        } catch (DaoException e) {
            throw new ServiceException("Client DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public boolean updatePassword(Client client) throws ServiceException {
        try {
            return userDao.update(client);
        } catch (DaoException e) {
            throw new ServiceException("User DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void updatePhoneNumber(Long id, String newNumber) throws ServiceException, ValidationException {
        if (!phoneNumberValidator.validate(newNumber)) {
            throw new ValidationException("Phone number : " + newNumber + " is invalid");
        }
        try {
            clientDao.updatePhoneNumber(id, newNumber);
        } catch (DaoException e) {
            throw new ServiceException("Client DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void updateRating(String login, int rating) throws ServiceException {
        try {
            clientDao.updateRating(login, rating);
        } catch (DaoException e) {
            throw new ServiceException("Client DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public double calculatePrice(double distance, double bonus) {
        return distance - bonus*distance;
    }
}

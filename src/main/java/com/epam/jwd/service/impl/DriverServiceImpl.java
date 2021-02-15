package com.epam.jwd.service.impl;

import com.epam.jwd.dao.impl.*;
import com.epam.jwd.domain.impl.*;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.validator.Validator;
import com.epam.jwd.validator.impl.CarNumberValidator;
import com.epam.jwd.validator.impl.EmailValidator;
import com.epam.jwd.validator.impl.LoginValidator;
import com.epam.jwd.validator.impl.PhoneNumberValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DriverServiceImpl implements DriverService {

    private DriverServiceImpl() {
    }

    private static DriverServiceImpl driverService;

    public static DriverServiceImpl getInstance() {
        if (driverService == null) {
            driverService = new DriverServiceImpl();
        }
        return driverService;
    }

    private final CarDao carDao = CarDao.CAR_DAO;
    private final RideDAO rideDAO = RideDAO.RIDE_DAO;
    private final DriverDao driverDao = DriverDao.DRIVER_DAO;
    private final PaymentDao paymentDao = PaymentDao.PAYMENT_DAO;
    private final UserDao userDao = UserDao.USER_DAO;
    private final Validator<String> loginValidator = LoginValidator.LOGIN_VALIDATOR;
    private final Validator<String> carNumberValidator = CarNumberValidator.CAR_NUMBER_VALIDATOR;
    private final Validator<String> emailValidator = EmailValidator.EMAIL_VALIDATOR;
    private final Validator<String> phoneNumberValidator = PhoneNumberValidator.PHONE_NUMBER_VALIDATOR;


    @Override
    public Optional<Driver> findByLogin(String login) throws ServiceException {
        try {
            return driverDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Driver DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public boolean add(Driver driver) throws ServiceException, ValidationException {
        if (!loginValidator.validate(driver.getLogin())) {
            throw new ValidationException("Login : " + driver.getLogin() + " is invalid");
        } else if (!emailValidator.validate(driver.getEmail())) {
            throw new ValidationException("Email : " + driver.getEmail() + " is invalid");
        } else if (!phoneNumberValidator.validate(driver.getPhoneNumber())) {
            throw new ValidationException("Phone number : " + driver.getPhoneNumber() + " is invalid");
        }
        try {
            Optional<User> userOptional = UserDao.USER_DAO.findByLogin(driver.getLogin());
            if (userOptional.isPresent()) {
                return driverDao.add(driver);
            }
        } catch (DaoException e) {
            throw new ServiceException("User DAO provides exception in service : " + e.getMessage());
        }

        Connection connection = driverDao.getConnection();
        try {
            connection.setAutoCommit(false);
            UserDao.USER_DAO.add(driver, connection);
            DriverDao.DRIVER_DAO.add(driver, connection);
            connection.commit();
            return true;
        } catch (SQLException | DaoException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new ServiceException("Rollback error : " + exception.getMessage());
            }
            throw new ServiceException("User or driver DAO provides exception in service : " + e.getMessage());
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
    public List<Ride> findAvailableRides() throws ServiceException {
        try {
            return rideDAO.findAll().stream().filter((p) -> p.getDriver() == null).collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public List<Driver> findNotConfirmedDrivers() throws ServiceException {
        try {
            return driverDao.findAll().stream().filter((p) -> p.getStatus().equals(DriverStatus.WAITING_CONFIRM)).collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Driver DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public List<Driver> findAll() throws ServiceException {
        try {
            return driverDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Driver DAO provides exception in service : " + e);
        }
    }

    @Override
    public boolean acceptRide(String login, Ride ride) throws ServiceException {
        try {
            Optional<Driver> driverOptional = driverDao.findByLogin(login);
            if (driverOptional.isPresent()) {
                return rideDAO.setDriver(driverOptional.get(), ride);
            }
        } catch (DaoException e) {
            throw new ServiceException("Driver or Ride DAO provides exception in service : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addEmail(String login, String email) throws ServiceException, ValidationException {
        if (emailValidator.validate(login)) {
            try {
                return driverDao.updateEmail(login, email);
            } catch (DaoException e) {
                throw new ServiceException("Driver DAO provides exception in service : " + e.getMessage());
            }
        }
        throw new ValidationException("Email : " + email + " is invalid");
    }

    @Override
    public boolean addCar(Car car) throws ServiceException, ValidationException {
        if (carNumberValidator.validate(car.getNumber())) {
            try {
                return carDao.add(car);
            } catch (DaoException e) {
                throw new ServiceException("Car DAO provides exception in service : " + e.getMessage());
            }
        }
        throw new ValidationException("Car number " + car.getNumber() + " is invalid");
    }

    @Override
    public List<Car> findDriversCars(String login) throws ServiceException {
        try {
            Optional<Driver> driverOptional = driverDao.findByLogin(login);
            if (driverOptional.isPresent()) {
                return carDao.findByDriverId(driverOptional.get().getId());
            }
            return List.of();
        } catch (DaoException e) {
            throw new ServiceException("Driver or Car DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void addRole(UserRole role, Long userId) throws ServiceException {
        try {
            userDao.addRole(role, userId);
        } catch (DaoException e) {
            throw new ServiceException("User DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void updateRideEndDate(Long rideId, LocalDateTime dateTime) throws ServiceException {
        try {
            rideDAO.updateEndDate(rideId, dateTime);
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void updateRating(String login, int rating) throws ServiceException {
        try {
            driverDao.updateRating(login, rating);
        } catch (DaoException e) {
            throw new ServiceException("Driver DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void updatePassword(Driver driver) throws ServiceException {
        try {
            userDao.update(driver);
        } catch (DaoException e) {
            throw new ServiceException("User DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public Optional<Payment> findPaymentByRideId(Long rideId) throws ServiceException {
        try {
            return paymentDao.findPaymentByRideId(rideId);
        } catch (DaoException e) {
            throw new ServiceException("Payment DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public void updateRideDriver(Ride ride, Driver driver) throws ServiceException {
        try {
            rideDAO.setDriver(driver, ride);
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public boolean updateStatus(String login, DriverStatus status) throws ServiceException {
        try {
            return driverDao.updateStatus(login, status);
        } catch (DaoException e) {
            throw new ServiceException("Driver dao provides exception in service : " + e.getMessage());
        }
    }

    @Override
    public Optional<Ride> findRideById(Long id) throws ServiceException {
        try {
            return rideDAO.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service : " + e.getMessage());
        }
    }

}

package com.epam.jwd.service.impl;

import com.epam.jwd.dao.impl.*;
import com.epam.jwd.domain.impl.*;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.validator.Validator;
import com.epam.jwd.validator.impl.LoginValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {

    private AdminServiceImpl(){}

    private static AdminServiceImpl adminService;

    public static AdminServiceImpl getInstance(){
        if(adminService==null){
            adminService = new AdminServiceImpl();
        }
        return adminService;
    }

    private final UserDao userDao = UserDao.USER_DAO;
    private final Validator<String> loginValidator = LoginValidator.LOGIN_VALIDATOR;
    private final DriverDao driverDao = DriverDao.DRIVER_DAO;
    private final ClientDao clientDao = ClientDao.CLIENT_DAO;
    private final RideDAO rideDAO = RideDAO.RIDE_DAO;
    private final PaymentDao paymentDao = PaymentDao.PAYMENT_DAO;

    @Override
    public Optional<User> findByLogin(String login) throws ValidationException, ServiceException {
        if(loginValidator.validate(login)) {
            try {
                return userDao.findByLogin(login);
            } catch (DaoException e) {
                throw new ServiceException("User DAO provides exception in service : "+e);
            }
        }
        throw new ValidationException("Login : "+login+" is invalid");
    }

    @Override
    public boolean removeAdmin(User user) throws ServiceException {
        try {
            return userDao.remove(user);
        } catch (DaoException e) {
            throw new ServiceException("User DAO provides exception in service : "+e);
        }
    }

    @Override
    public boolean updateDriverStatus(String login, DriverStatus status) throws ServiceException, ValidationException {
        if(loginValidator.validate(login)){
            try {
                return driverDao.updateStatus(login, status);
            } catch (DaoException e) {
                throw new ServiceException("Driver DAO provides exception in service : "+e);
            }
        }
        throw new ValidationException("Login : "+login+" is invalid");
    }

    public boolean updateClientRating(String login, int rating) throws ServiceException {
        try {
            return clientDao.updateRating(login,rating);
        } catch (DaoException e) {
            throw new ServiceException("Client DAO provides exception in service : "+e);
        }
    }

    public boolean updateDriverRating(String login, int rating) throws ServiceException {
        try {
            return driverDao.updateRating(login, rating);
        } catch (DaoException e) {
            throw new ServiceException("Driver DAO provides exception in service : "+e);
        }
    }

    @Override
    public boolean updateClientStatus(String login, ClientStatus status) throws ServiceException, ValidationException {
        if(loginValidator.validate(login)){
            try {
                return clientDao.updateStatus(login, status);
            } catch (DaoException e) {
                throw new ServiceException("Client DAO provides exception in service : "+e);
            }
        }
        throw new ValidationException("Login : "+login+" is invalid");
    }

    @Override
    public boolean addAdmin(User user) throws ServiceException, ValidationException {
        if(loginValidator.validate(user.getLogin())){
            try {
                return userDao.add(user);
            } catch (DaoException e) {
                throw new ServiceException("User DAO provides exception in service : "+e);
            }
        }
        throw new ValidationException("Login : "+user.getLogin()+" is invalid");
    }

    @Override
    public List<Ride> findAllRides() throws ServiceException {
        try {
            return rideDAO.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Ride DAO provides exception in service : "+e);
        }
    }

    @Override
    public List<Payment> findAllPayments() throws ServiceException {
        try {
            return paymentDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Payment DAO provides exception in service : "+e);
        }
    }

    @Override
    public boolean confirmDriver(String login) throws ServiceException, ValidationException {
        if(loginValidator.validate(login)){
            try {
                return driverDao.updateStatus(login, DriverStatus.ACTIVE);
            } catch (DaoException e) {
                throw new ServiceException("Driver DAO provides exception in service : "+e);
            }
        }
        throw new ValidationException("Login : "+login+" is invalid");
    }

    @Override
    public List<Driver> findAllDriversWithBadRating() throws ServiceException {
        try {
            return driverDao.findAll().stream().filter((p)->p.getRating().ordinal()<3).collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Driver DAO provides exception in service : "+e);
        }
    }

    @Override
    public List<Client> findAllClientsWithBadRating() throws ServiceException {
        try {
            return clientDao.findAll().stream().filter((p)->p.getRating().ordinal()<3).collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Client DAO provides exception in service : "+e);
        }
    }
}

package com.epam.jwd.service;

import com.epam.jwd.domain.impl.BankCard;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Payment;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;

import java.util.Optional;

public interface ClientService {

    Optional<Client> findByLogin(String login) throws ServiceException, ValidationException;

    boolean addBankCard(BankCard bankCard) throws ValidationException, ServiceException;

    boolean addEmail(String login, String email) throws ValidationException, ServiceException;

    boolean doPay(Payment payment) throws ServiceException;

    boolean addClient(Client client) throws ServiceException, ValidationException, DaoException;

}

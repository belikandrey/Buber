package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.BankCard;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.AbstractFactory;
import com.epam.jwd.factory.impl.BankCardFactory;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.validator.Validator;
import com.epam.jwd.validator.impl.CardDateValidator;
import com.epam.jwd.validator.impl.CardNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AddBankCardCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(AddBankCardCommand.class);
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final Validator<String> cardDateValidator = CardDateValidator.CARD_DATE_VALIDATOR;
    private final Validator<String> cardNumberValidator = CardNumberValidator.CARD_NUMBER_VALIDATOR;
    private final AbstractFactory<BankCard> bankCardFactory = BankCardFactory.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
        try {
            clientService.addBankCard(parseBankCard(servletRequest));
            logger.info("Bank card added successful");
        } catch (ValidationException e){
            commandResult = new CommandResult("WEB-INF/jsp/client/add_bank_card.jsp", CommandResult.ResponseType.FORWARD);
            logger.info("Invalid bank card data : "+e.getMessage());
            commandResult.addAttribute("message", e.getMessage());
        } catch (ServiceException e) {
            logger.error("Bank card service : "+e.getMessage());
        }
        return commandResult;
    }
    private BankCard parseBankCard(HttpServletRequest servletRequest) throws ValidationException {
        String cardNumber = servletRequest.getParameter("cardNumber");
        String cardDate = servletRequest.getParameter("cardDate");
        String cardCsc = servletRequest.getParameter("cardCsc");
        if(!cardNumberValidator.validate(cardNumber)){
            throw new ValidationException("Card number is invalid");
        }
        if(!cardDateValidator.validate(cardDate)){
            throw new ValidationException("Card date is invalid");
        }
        Client client = (Client) servletRequest.getSession().getAttribute("client");
        try {
            return bankCardFactory.create(client, cardNumber, cardDate, Integer.parseInt(cardCsc));
        } catch (FactoryException e) {
            throw new ValidationException("Something was wrong");
        }
    }
}

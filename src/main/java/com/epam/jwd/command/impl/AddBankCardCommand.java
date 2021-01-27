package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.BankCard;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.BankCardFactory;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.validator.impl.CardDateValidator;
import com.epam.jwd.validator.impl.CardNumberValidator;

import javax.servlet.http.HttpServletRequest;

public class AddBankCardCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_add_bank_card", CommandResult.ResponseType.REDIRECT);
        commandResult.addAttribute("message", "successful");
        try {
            ClientServiceImpl.getInstance().addBankCard(parseBankCard(servletRequest));
        } catch (Exception e){
            commandResult.addAttribute("message", e.getMessage());
        }
        return commandResult;
    }
    private BankCard parseBankCard(HttpServletRequest servletRequest) throws ValidationException {
        String cardNumber = servletRequest.getParameter("cardNumber");
        String cardDate = servletRequest.getParameter("cardDate");
        String cardCsc = servletRequest.getParameter("cardCsc");
        if(!CardNumberValidator.CARD_NUMBER_VALIDATOR.validate(cardNumber)){
            throw new ValidationException("Card number is invalid");
        }
        if(!CardDateValidator.CARD_DATE_VALIDATOR.validate(cardDate)){
            throw new ValidationException("Card date is invalid");
        }
        Client client = (Client) servletRequest.getSession().getAttribute("client");
        try {
            return BankCardFactory.getInstance().create(client, cardNumber, cardDate, Integer.parseInt(cardCsc));
        } catch (FactoryException e) {
            throw new ValidationException("Something was wrong");
        }
    }
}

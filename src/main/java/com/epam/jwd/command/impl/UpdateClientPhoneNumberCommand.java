package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.validator.impl.PhoneNumberValidator;

import javax.servlet.http.HttpServletRequest;

public class UpdateClientPhoneNumberCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_update_client_phone_number", CommandResult.ResponseType.REDIRECT);
        String newNumber = servletRequest.getParameter("newPhoneNumber");
        if(PhoneNumberValidator.PHONE_NUMBER_VALIDATOR.validate(newNumber)){
            Client client = (Client) servletRequest.getSession().getAttribute("client");
            client.setPhoneNumber(newNumber);
            try {
                ClientServiceImpl.getInstance().updatePhoneNumber(client.getId(), newNumber);
                servletRequest.getSession().setAttribute("client", client);
            } catch (ServiceException e) {
                commandResult.addAttribute("message", "Something was wrong");
            }
        }else{
            commandResult.addAttribute("message", "something was wrong");
        }
        return commandResult;
    }
}

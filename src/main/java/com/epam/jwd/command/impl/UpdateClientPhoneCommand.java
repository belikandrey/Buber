package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.validator.impl.PhoneNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UpdateClientPhoneCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(UpdateClientPhoneCommand.class);
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_update_client_phone", CommandResult.ResponseType.REDIRECT);
        String newNumber = servletRequest.getParameter("newPhoneNumber");
        Client client = (Client) servletRequest.getSession().getAttribute("client");
        try {
            clientService.updatePhoneNumber(client.getId(), newNumber);
            client.setPhoneNumber(newNumber);
            servletRequest.getSession().setAttribute("client", client);
            logger.info("Client phone number updated successful");
        } catch (ServiceException e) {
            logger.error("Client phone number not updated : " + e.getMessage());
            commandResult.addAttribute("message", "Something was wrong");
        } catch (ValidationException e) {
            logger.error("Invalid client phone number : " + e.getMessage());
            commandResult.addAttribute("message", "Invalid phone number");
        }
        return commandResult;
    }
}

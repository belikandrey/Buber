package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UpdateClientPasswordCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(UpdateClientPasswordCommand.class);
    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
        String newPassword = servletRequest.getParameter("newPassword");
        Client client =(Client) servletRequest.getSession().getAttribute("client");
        client.setPassword(newPassword);
        try {
            clientService.updatePassword(client);
            servletRequest.getSession().setAttribute("client", client);
            logger.info("Clients password updated successful");
        } catch (ServiceException e) {
            logger.error("Clients password not updated : "+e.getMessage());
            commandResult.addAttribute("message", "Something was wrong. : "+e);
        }
        return commandResult;
    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class EditClientPasswordCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
        String newPassword = servletRequest.getParameter("newPassword");
        Client client =(Client) servletRequest.getSession().getAttribute("client");
        client.setPassword(newPassword);
        try {
            ClientServiceImpl.getInstance().updatePassword(client);
            servletRequest.getSession().setAttribute("client", client);
        } catch (ServiceException e) {
            commandResult.addAttribute("message", "something was wrong. : "+e);
        }
        return commandResult;
    }
}

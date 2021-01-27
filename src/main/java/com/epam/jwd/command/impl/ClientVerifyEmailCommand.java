package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.ClientStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ClientVerifyEmailCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        CommandResult commandResult = new CommandResult("home?command=to_client_verify_email", CommandResult.ResponseType.FORWARD);;
        if (servletRequest.getParameter("userCode").equals(String.valueOf(session.getAttribute("code")))) {
            session.removeAttribute("code");
            Client client = (Client) session.getAttribute("client");
            client.setStatus(ClientStatus.ACTIVE);
            try {
                ClientServiceImpl.getInstance().updateStatus(client.getLogin(), ClientStatus.ACTIVE);
                commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
                return commandResult;
            } catch (ServiceException e) {
                servletRequest.setAttribute("message", "Error at server! Message admin, please");
            }
            return commandResult;
        }
        servletRequest.setAttribute("message", "Incorrect code!");
        return commandResult;
    }
}

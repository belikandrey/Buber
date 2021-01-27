package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ClientLoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        CommandResult commandResult;
        String message = null;
        try {
            Optional<Client> clientOptional = ClientServiceImpl.getInstance().findByLogin(login);
            if(clientOptional.isPresent() && clientOptional.get().getPassword().equals(password)){
                HttpSession session = servletRequest.getSession();
                session.setAttribute("userRoles", clientOptional.get().getRoles());
                commandResult =  new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
                session.setAttribute("client", clientOptional.get());
                return commandResult;
            }
            message = "Incorrect login or password";
        } catch (ServiceException e) {
            message = "Sorry, aliens attacked our server ... But they left some incomprehensible message : "+e+"...\n Please, try again later";
        } catch (ValidationException e) {
            message = "Invalid login";
        }
        commandResult = new CommandResult("WEB-INF/jsp/common/client_common.jsp", CommandResult.ResponseType.FORWARD);
        servletRequest.setAttribute("message", message);
        return commandResult;
    }
}

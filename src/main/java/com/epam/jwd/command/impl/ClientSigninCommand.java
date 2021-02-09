package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.ClientStatus;
import com.epam.jwd.domain.impl.Rating;
import com.epam.jwd.domain.impl.UserRole;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ClientSignInCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String name = servletRequest.getParameter("name");
        String phoneNumber = servletRequest.getParameter("phone");
        String email = servletRequest.getParameter("email");
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        String repeatedPassword = servletRequest.getParameter("repeatedPassword");
        CommandResult commandResult;
        String message;
        if(password==null || !password.equals(repeatedPassword)){
            commandResult = new CommandResult("WEB-INF/jsp/common/client_sign_in.jsp", CommandResult.ResponseType.FORWARD);
            message = "Passwords do not match";
            commandResult.addAttribute("message", message);
            return commandResult;
        }
        Client client = Client.newBuilder().addName(name).addPhoneNumber(phoneNumber).addEmail(email).addLogin(login)
                .addPassword(password).addRating(Rating.NORMAL).addStatus(ClientStatus.NOT_VERIFIED).addRoles(List.of(UserRole.CLIENT)).build();
        try {
            if(ClientServiceImpl.getInstance().addClient(client)){
                client = ClientServiceImpl.getInstance().findByLogin(client.getLogin()).get();
                commandResult = new CommandResult("home?command=to_verify_email", CommandResult.ResponseType.REDIRECT);
                HttpSession session = servletRequest.getSession();
                session.setAttribute("client", client);
                session.setAttribute("userRoles", client.getRoles());
                return commandResult;
            }
            message = "Sorry, aliens attacked our server ...\t Please, try again later";
        } catch (ServiceException e) {
            message = "Sorry, aliens attacked our server ... But they left some incomprehensible message : "+e+"...\n Please, try again later";
        } catch (ValidationException e) {
            message = "Invalid login, phone number or email : "+e.getMessage();
        }
        commandResult = new CommandResult("WEB-INF/jsp/common/client_sign_in.jsp", CommandResult.ResponseType.FORWARD);
        commandResult.addAttribute("message", message);
        return commandResult;
    }
}

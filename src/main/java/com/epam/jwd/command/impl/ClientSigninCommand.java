package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.ClientStatus;
import com.epam.jwd.domain.impl.Rating;
import com.epam.jwd.domain.impl.UserRole;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ClientSignInCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ClientSignInCommand.class);
    private final ClientService clientService = ClientServiceImpl.getInstance();


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
            logger.info("Wrong repeated password");
            return commandResult;
        }
        Client client = Client.newBuilder().addName(name).addPhoneNumber(phoneNumber).addEmail(email).addLogin(login)
                .addPassword(password).addRating(Rating.NORMAL).addStatus(ClientStatus.NOT_VERIFIED).addRoles(List.of(UserRole.CLIENT)).build();
        try {
            if(clientService.addClient(client)){
                client = clientService.findByLogin(client.getLogin()).get();
                commandResult = new CommandResult("home?command=to_verify_email", CommandResult.ResponseType.REDIRECT);
                HttpSession session = servletRequest.getSession();
                session.setAttribute("client", client);
                session.setAttribute("userRoles", client.getRoles());
                logger.info("Client sign in successful");
                return commandResult;
            }else{
                throw new ServiceException("Client not added");
            }
        } catch (ServiceException e) {
            logger.error("Client dont sign in : "+e.getMessage());
            message = "Sorry, aliens attacked our server ... \nPlease, try again later";
        } catch (ValidationException e) {
            logger.info("Invalid client info : "+e.getMessage());
            message = "Invalid login, phone number or email : "+e.getMessage();
        }
        commandResult = new CommandResult("WEB-INF/jsp/common/client_sign_in.jsp", CommandResult.ResponseType.FORWARD);
        commandResult.addAttribute("message", message);
        return commandResult;
    }
}

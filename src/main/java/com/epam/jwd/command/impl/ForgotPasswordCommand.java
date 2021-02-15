package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ForgotPasswordCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ForgotPasswordCommand.class);
    private final DriverService driverService = DriverServiceImpl.getInstance();
    private final ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String login = servletRequest.getParameter("login");
        CommandResult commandResult = new CommandResult("home?command=to_join", CommandResult.ResponseType.FORWARD);
        String message = "Sorry, something were wrong. Try again later";
        try {
            Optional<Driver> driverOptional = driverService.findByLogin(login);
            Optional<Client> clientOptional = clientService.findByLogin(login);
            if(driverOptional.isPresent()){
                MailSender.MAIL_SENDER.send(driverOptional.get().getEmail(), "Support", "Your password : "+driverOptional.get().getPassword());
            }else if(clientOptional.isPresent()){
                MailSender.MAIL_SENDER.send(clientOptional.get().getEmail(), "Support", "Your password : "+clientOptional.get().getPassword());
            }
        } catch (ServiceException e) {
            logger.error("Service exception : "+e.getMessage());
        } catch (MessagingException e) {
            logger.error("Message dont send : "+e.getMessage());
        }
        commandResult.addAttribute("message", message);
        return commandResult;
    }
}

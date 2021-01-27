package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class ClientForgotPasswordCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String login = servletRequest.getParameter("login");
        try {
            Optional<Client> byLogin = ClientServiceImpl.getInstance().findByLogin(login);
            if(byLogin.isPresent()){
                MailSender.MAIL_SENDER.send(byLogin.get().getEmail(), "Support", "Your password : "+byLogin.get().getPassword());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommandResult("home?command=to_client_login", CommandResult.ResponseType.FORWARD);
    }
}

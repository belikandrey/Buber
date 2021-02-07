package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ForgotPasswordCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String login = servletRequest.getParameter("login");
        try {
            Optional<Driver> driverOptional = DriverServiceImpl.getInstance().findByLogin(login);
            Optional<Client> clientOptional = ClientServiceImpl.getInstance().findByLogin(login);
            if(driverOptional.isPresent()){
                MailSender.MAIL_SENDER.send(driverOptional.get().getEmail(), "Support", "Your password : "+driverOptional.get().getPassword());
            }else if(clientOptional.isPresent()){
                MailSender.MAIL_SENDER.send(clientOptional.get().getEmail(), "Support", "Your password : "+clientOptional.get().getPassword());
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return new CommandResult("home?command=to_join", CommandResult.ResponseType.FORWARD);
    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.ClientStatus;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

public class ToVerifyEmailCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        int code = new Random().nextInt(99999999);
        Driver driver =(Driver) session.getAttribute("driver");
        Client client = (Client) session.getAttribute("client");
        if(driver!=null) {
            try {
                MailSender.MAIL_SENDER.send(driver.getEmail(), "Verification code", "You sign in as driver in Buber.\nYour verify code : " + code);
            } catch (Exception e) {
                servletRequest.setAttribute("message", "Sorry, something wrong with email sender. Try again later");
                return new CommandResult("WEB-INF/jsp/common/to_driver_sign_in.jsp", CommandResult.ResponseType.FORWARD);
            }
        }else if(client!=null){
            try {
                MailSender.MAIL_SENDER.send(client.getEmail(), "Verification code", "You sign in as driver in Buber.\nYour verify code : " + code);
            } catch (Exception e) {
                servletRequest.setAttribute("message", "Sorry, something wrong with email sender. Try again later");
                return new CommandResult("WEB-INF/jsp/common/to_client_sign_in.jsp", CommandResult.ResponseType.FORWARD);
            }
        }
        session.setAttribute("code", code);
        return new CommandResult("WEB-INF/jsp/common/verify_email.jsp", CommandResult.ResponseType.FORWARD);
    }
}

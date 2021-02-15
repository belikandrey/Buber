package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.notification.Sender;
import com.epam.jwd.notification.impl.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

public class ToVerifyEmailCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ToVerifyEmailCommand.class);
    private final Sender sender = MailSender.MAIL_SENDER;

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        int code = new Random().nextInt(99999999);
        Driver driver =(Driver) session.getAttribute("driver");
        Client client = (Client) session.getAttribute("client");
        if(driver!=null) {
            try {
                sender.send(driver.getEmail(), "Verification code", "You sign in as driver in Buber.\nYour verify code : " + code);
            } catch (Exception e) {
                logger.error("Message not send : "+e.getMessage());
                servletRequest.setAttribute("message", "Sorry, something wrong with email sender. Try again later");
                return new CommandResult("WEB-INF/jsp/common/to_driver_sign_in.jsp", CommandResult.ResponseType.FORWARD);
            }
        }else if(client!=null){
            try {
                sender.send(client.getEmail(), "Verification code", "You sign in as driver in Buber.\nYour verify code : " + code);
            } catch (Exception e) {
                logger.error("Message not send : "+e.getMessage());
                servletRequest.setAttribute("message", "Sorry, something wrong with email sender. Try again later");
                return new CommandResult("WEB-INF/jsp/common/to_client_sign_in.jsp", CommandResult.ResponseType.FORWARD);
            }
        }
        session.setAttribute("code", code);
        return new CommandResult("WEB-INF/jsp/common/verify_email.jsp", CommandResult.ResponseType.FORWARD);
    }
}

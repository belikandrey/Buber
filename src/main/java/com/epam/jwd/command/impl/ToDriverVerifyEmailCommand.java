package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.notification.impl.MailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

public class ToDriverVerifyEmailCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        int code = new Random().nextInt(99999999);
        Driver driver =(Driver) session.getAttribute("driver");
        try {
            MailSender.MAIL_SENDER.send(driver.getEmail(), "Verification code", "You sign in as driver in Buber.\nYour verify code : "+code);
        } catch (Exception e){
             servletRequest.setAttribute("message", "Sorry, something wrong with email sender. Try again later");
             return new CommandResult("WEB-INF/jsp/common/driver_signin.jsp", CommandResult.ResponseType.FORWARD);
        }
        session.setAttribute("code", code);
        return new CommandResult("WEB-INF/jsp/common/driver_verify_email.jsp", CommandResult.ResponseType.FORWARD);

    }
}

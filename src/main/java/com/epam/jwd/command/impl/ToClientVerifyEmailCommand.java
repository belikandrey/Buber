package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.notification.impl.MailSender;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

public class ToClientVerifyEmailCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Client client = (Client) session.getAttribute("client");
        int code = new Random().nextInt(99999999);
        try {
            MailSender.MAIL_SENDER.send(client.getEmail(), "Verification code", "You sign in as client in Buber\nYour verify code : "+code);
        }catch (Exception e) {
            servletRequest.setAttribute("message", "Sorry, something wrong with email sender. Try again later");
            return new CommandResult("WEB-INF/jsp/common/client_signin.jsp", CommandResult.ResponseType.FORWARD);
        }
        session.setAttribute("code", code);
        return new CommandResult("WEB-INF/jsp/common/client_verify_email.jsp", CommandResult.ResponseType.FORWARD);
    }
}

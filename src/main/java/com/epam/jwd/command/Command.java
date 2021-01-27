package com.epam.jwd.command;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Command {
    CommandResult execute(HttpServletRequest servletRequest) ;
}

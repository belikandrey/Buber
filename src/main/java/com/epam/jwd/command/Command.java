package com.epam.jwd.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandResult execute(HttpServletRequest servletRequest) ;
}

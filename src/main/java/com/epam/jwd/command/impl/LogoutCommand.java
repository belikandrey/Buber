package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        servletRequest.getSession().invalidate();
        return new CommandResult("home?command=to_home", CommandResult.ResponseType.REDIRECT);
    }
}

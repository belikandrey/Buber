package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;

import javax.servlet.http.HttpServletRequest;

public class ToHomeCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        return new CommandResult("WEB-INF/jsp/common/home.jsp", CommandResult.ResponseType.FORWARD);
    }
}

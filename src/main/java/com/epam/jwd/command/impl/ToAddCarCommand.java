package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;

import javax.servlet.http.HttpServletRequest;

public class ToAddCarCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        return new CommandResult("WEB-INF/jsp/driver/add_car.jsp", CommandResult.ResponseType.FORWARD);
    }
}

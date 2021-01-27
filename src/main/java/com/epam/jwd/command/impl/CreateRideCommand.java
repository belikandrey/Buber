package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class CreateRideCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        return new CommandResult("WEB-INF/jsp/client/create_ride_submit.jsp", CommandResult.ResponseType.FORWARD);
    }
}

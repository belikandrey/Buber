package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToAvailableRidesCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/driver/available_rides.jsp", CommandResult.ResponseType.FORWARD);
        try {
            List<Ride> availableRides = DriverServiceImpl.getInstance().findAvailableRides();
            commandResult.addAttribute("rides", availableRides);
        } catch (ServiceException e) {
            commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "something was wrong : "+e);
        }
        return commandResult;
    }
}

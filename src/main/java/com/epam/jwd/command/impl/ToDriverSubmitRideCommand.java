package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ToDriverSubmitRideCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/driver/submit_ride.jsp", CommandResult.ResponseType.FORWARD);
        Long rideId = Long.parseLong(servletRequest.getParameter("ride_id"));
        try {
            Ride ride = DriverServiceImpl.getInstance().findRideById(rideId).get();
            commandResult.addAttribute("ride", ride);
        } catch (ServiceException e) {
            commandResult = new CommandResult("driver?command=to_available_rides", CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.Rating;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UpdateDriverMarkCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_client_show_ride_history", CommandResult.ResponseType.REDIRECT);
        Long rideId = Long.parseLong(servletRequest.getParameter("ride_id"));
        int newMark = Integer.parseInt(servletRequest.getParameter("newMark"));
        try {
            Driver driver = ClientServiceImpl.getInstance().findRideById(rideId).get().getDriver();
            ClientServiceImpl.getInstance().updateRideDriverMark(rideId, newMark);
            int driverMark = driver.getRating().ordinal();
            if(driverMark<newMark){
                DriverServiceImpl.getInstance().updateRating(driver.getLogin(), driverMark+1);
            }else if (driverMark>newMark){
                DriverServiceImpl.getInstance().updateRating(driver.getLogin(), driverMark-1);
            }
        } catch (ServiceException e) {
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
}

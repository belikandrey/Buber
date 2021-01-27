package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.AdminServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ToDriverSubmitRideCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        Long rideId = Long.parseLong(servletRequest.getParameter("ride_id"));
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/driver/submit_ride.jsp", CommandResult.ResponseType.FORWARD);
        String message;
        try {
            List<Car> driversCars = DriverServiceImpl.getInstance().findDriversCars(driver.getLogin());
            Ride rideById = DriverServiceImpl.getInstance().findRideById(rideId).get();
            commandResult.addAttribute("cars", driversCars);
            commandResult.addAttribute("ride", rideById);
            return commandResult;
        } catch (ServiceException e) {
            message = "Sorry, something was wrong : "+e;
        } catch (ValidationException e) {
            message = "Sorry, but cars has invalid number : "+e;
        }
        commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
        commandResult.addAttribute("message", message);
        return commandResult;
    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToAvailableRidesCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ToAvailableRidesCommand.class);
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/driver/available_rides.jsp", CommandResult.ResponseType.FORWARD);
        try {
            List<Ride> availableRides = driverService.findAvailableRides();
            commandResult.addAttribute("rides", availableRides);
        } catch (ServiceException e) {
            logger.error("Exception in find available rides : "+e.getMessage());
            commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "Something was wrong. Try again later ");
        }
        return commandResult;
    }
}

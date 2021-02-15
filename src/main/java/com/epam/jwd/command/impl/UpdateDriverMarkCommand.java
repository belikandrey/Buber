package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverMarkCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(UpdateDriverMarkCommand.class);
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_show_client_rides", CommandResult.ResponseType.REDIRECT);
        Long rideId = Long.parseLong(servletRequest.getParameter("ride_id"));
        int newMark = Integer.parseInt(servletRequest.getParameter("mark"));
        try {
            Driver driver = clientService.findRideById(rideId).get().getDriver();
            clientService.updateRideDriverMark(rideId, newMark);
            int driverMark = driver.getRating().ordinal();
            if(driverMark<newMark){
                driverService.updateRating(driver.getLogin(), driverMark+1);
            }else if (driverMark>newMark){
                driverService.updateRating(driver.getLogin(), driverMark-1);
            }
            logger.info("Driver mark updated successful");
        } catch (ServiceException e) {
            logger.error("Driver mark dont updated : "+e.getMessage());
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
}

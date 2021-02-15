package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class CurrentCarCommand implements Command {
   private final Logger logger = LoggerFactory.getLogger(CurrentCarCommand.class);
   private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        String number = servletRequest.getParameter("number");
        try {
            Car car = driverService.findDriversCars(driver.getLogin()).stream()
                    .filter((p) -> p.getNumber().equals(number)).findAny().get();
            servletRequest.getSession().setAttribute("currentCar", car);
            logger.info("Current car selected");
        } catch (ServiceException e) {
            logger.error("Current car not selected : "+e.getMessage());
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
}

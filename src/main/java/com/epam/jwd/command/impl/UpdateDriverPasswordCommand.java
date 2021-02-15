package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverPasswordCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(UpdateDriverStatusCommand.class);
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
        String newPassword = servletRequest.getParameter("password");
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        driver.setPassword(newPassword);
        try {
            driverService.updatePassword(driver);
            logger.info("Driver password updated successful");
        } catch (ServiceException e) {
            logger.info("Driver status not updated : "+e.getMessage());
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
}

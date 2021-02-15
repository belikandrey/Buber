package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToDriverHomeCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ToDriverHomeCommand.class);
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/driver/home.jsp", CommandResult.ResponseType.FORWARD);
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        try {
            List<Car> driversCars = driverService.findDriversCars(driver.getLogin());
            servletRequest.setAttribute("cars", driversCars);
        } catch (ServiceException e) {
            logger.error("Exception in find drivers car : "+e.getMessage());
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
}

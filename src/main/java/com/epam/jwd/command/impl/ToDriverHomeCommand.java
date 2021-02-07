package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToDriverHomeCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/driver/home.jsp", CommandResult.ResponseType.FORWARD);
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        try {
            List<Car> driversCars = DriverServiceImpl.getInstance().findDriversCars(driver.getLogin());
            servletRequest.setAttribute("cars", driversCars);
        } catch (ServiceException e) {
            commandResult.addAttribute("message", "Something was wrong");
        } catch (ValidationException e) {
            commandResult.addAttribute("message", "Invalid info");
        }
        return commandResult;
    }
}

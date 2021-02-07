package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class CurrentCarCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        String number = servletRequest.getParameter("number");
        try {
            Car car = DriverServiceImpl.getInstance().findDriversCars(driver.getLogin()).stream()
                    .filter((p) -> p.getNumber().equals(number)).findAny().get();
            servletRequest.getSession().setAttribute("currentCar", car);
        } catch (ServiceException | ValidationException e) {
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
}

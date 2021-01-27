package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.CarFactory;
import com.epam.jwd.service.impl.DriverServiceImpl;
import com.epam.jwd.validator.impl.CarNumberValidator;

import javax.servlet.http.HttpServletRequest;

public class AddCarCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
        String carBrand = servletRequest.getParameter("carBrand");
        String carModel = servletRequest.getParameter("carModel");
        String carNumber = servletRequest.getParameter("carNumber");
        String carColor = servletRequest.getParameter("carColor");
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        try {
            Car car = CarFactory.getInstance().create(driver, carNumber, carBrand, carModel, carColor);
            DriverServiceImpl.getInstance().addCar(car);
        } catch (FactoryException | ServiceException e) {
            commandResult.addAttribute("message", "Something was wrong");
        } catch (ValidationException e) {
            commandResult.addAttribute("message", "Car number is invalid");
        }
        return commandResult;
    }
}

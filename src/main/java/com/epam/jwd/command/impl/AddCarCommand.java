package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.AbstractFactory;
import com.epam.jwd.factory.impl.CarFactory;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AddCarCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(AddCarCommand.class);
    private final AbstractFactory<Car> carFactory = CarFactory.getInstance();
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
        String carBrand = servletRequest.getParameter("car_brand");
        String carModel = servletRequest.getParameter("car_model");
        String carNumber = servletRequest.getParameter("car_number");
        String carColor = servletRequest.getParameter("car_color");
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        try {
            Car car = carFactory.create(driver, carNumber, carBrand, carModel, carColor);
            driverService.addCar(car);
            logger.info("Car added successful");
        } catch (FactoryException | ServiceException e) {
            commandResult.addAttribute("message", "Something was wrong");
            logger.error("Can't add bank card : "+e.getMessage());
        } catch (ValidationException e) {
            commandResult.addAttribute("message", "Car number is invalid");
            logger.error("Invalid card number : "+e.getMessage());
        }
        return commandResult;
    }
}

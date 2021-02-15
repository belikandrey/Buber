package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.*;
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
import java.util.List;

public class ClientSignAsDriverCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ClientSignAsDriverCommand.class);
    private final AbstractFactory<Car> carFactory = CarFactory.getInstance();
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
        String carNumber = servletRequest.getParameter("car_number");
        String carBrand = servletRequest.getParameter("car_brand");
        String carModel = servletRequest.getParameter("car_model");
        String carColor = servletRequest.getParameter("car_color");
        Client client = (Client)servletRequest.getSession().getAttribute("client");
        Driver driver = Driver.newBuilder().addId(client.getId()).addName(client.getName()).addPhoneNumber(client.getPhoneNumber()).addEmail(client.getEmail())
                .addLogin(client.getLogin()).addPassword(client.getPassword()).addRating(Rating.NORMAL).addRoles(List.of(UserRole.DRIVER))
                .addStatus(DriverStatus.NOT_VERIFIED).build();
        try {
            driverService.add(driver);
            Car car = carFactory.create(driver, carNumber, carBrand, carModel, carColor);
            driverService.addCar(car);
            List<UserRole> userRoles =(List<UserRole>) servletRequest.getSession().getAttribute("userRoles");
            userRoles.add(UserRole.DRIVER);
            driverService.addRole(UserRole.DRIVER, driver.getId());
            servletRequest.getSession().setAttribute("userRoles", userRoles);
            logger.info("Client sign in as driver successful");
        } catch (ServiceException e) {
            logger.error("Client dont sign in as driver : "+e.getMessage());
            commandResult.addAttribute("message", "Something was wrong");
        } catch (ValidationException | FactoryException e) {
            logger.info("Invalid data : "+e.getMessage());
            commandResult.addAttribute("message", "Invalid data");
        }
        return commandResult;
    }
}

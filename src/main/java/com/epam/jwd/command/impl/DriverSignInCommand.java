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
import javax.servlet.http.HttpSession;
import java.util.List;

public class DriverSignInCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(DriverSignInCommand.class);
    private final DriverService driverService = DriverServiceImpl.getInstance();
    private final AbstractFactory<Car> carFactory = CarFactory.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String name = servletRequest.getParameter("name");
        String phoneNumber = servletRequest.getParameter("phone");
        String email = servletRequest.getParameter("email");
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        String repeatedPassword = servletRequest.getParameter("repeated_password");

        String carNumber = servletRequest.getParameter("car_number");
        String carBrand = servletRequest.getParameter("car_brand");
        String carModel = servletRequest.getParameter("car_model");
        String carColor = servletRequest.getParameter("car_color");

        CommandResult commandResult;
        String message = "Sorry, aliens attacked our server ...\t Please, try again later";

        if (password == null || !password.equals(repeatedPassword)) {
            commandResult = new CommandResult("WEB-INF/jsp/common/driver_sign_in.jsp", CommandResult.ResponseType.FORWARD);
            message = "Passwords do not match";
            commandResult.addAttribute("message", message);
            logger.info("Wrong repeated password");
            return commandResult;
        }
        Driver driver = Driver.newBuilder().addName(name).addPhoneNumber(phoneNumber).addEmail(email)
                .addLogin(login).addPassword(password).addRating(Rating.NORMAL).addRoles(List.of(UserRole.DRIVER))
                .addStatus(DriverStatus.NOT_VERIFIED).build();
        try {
            if (driverService.add(driver)) {
                driver = driverService.findByLogin(driver.getLogin()).get();
                Car car = carFactory.create(driver, carNumber, carBrand, carModel, carColor);
                if(driverService.addCar(car)) {
                    commandResult = new CommandResult("home?command=to_verify_email", CommandResult.ResponseType.REDIRECT);
                    HttpSession session = servletRequest.getSession();
                    session.setAttribute("driver", driver);
                    session.setAttribute("currentCar", car);
                    session.setAttribute("userRoles", driver.getRoles());
                    logger.info("Driver added successful");
                    return commandResult;
                }
                message = "Invalid car info";
            }
        } catch (ServiceException e) {
            logger.error("Driver dont added : "+e.getMessage());
        } catch (ValidationException | FactoryException e) {
            message = "Invalid login, phone number, email or car number : "+e.getMessage();
            logger.error("Invalid driver data : "+e.getMessage());
        }
        commandResult = new CommandResult("WEB-INF/jsp/common/driver_sign_in.jsp", CommandResult.ResponseType.FORWARD);
        commandResult.addAttribute("message", message);
        return commandResult;
    }
}

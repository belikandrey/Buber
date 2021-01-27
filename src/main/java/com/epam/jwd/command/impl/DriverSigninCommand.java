package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.dao.impl.DriverDao;
import com.epam.jwd.domain.impl.*;
import com.epam.jwd.exception.DaoException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.CarFactory;
import com.epam.jwd.notification.impl.MailSender;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

public class DriverSigninCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String name = servletRequest.getParameter("name");
        String phoneNumber = servletRequest.getParameter("phoneNumber");
        String email = servletRequest.getParameter("email");
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        String repeatedPassword = servletRequest.getParameter("repeatedPassword");

        String carNumber = servletRequest.getParameter("carNumber");
        String carBrand = servletRequest.getParameter("carBrand");
        String carModel = servletRequest.getParameter("carModel");
        String carColor = servletRequest.getParameter("carColor");

        CommandResult commandResult;
        String message = null;

        if (password == null || !password.equals(repeatedPassword)) {
            commandResult = new CommandResult("WEB-INF/jsp/common/driver_signin.jsp", CommandResult.ResponseType.FORWARD);
            message = "Passwords do not match";
            commandResult.addAttribute("message", message);
            return commandResult;
        }
        Driver driver = Driver.newBuilder().addName(name).addPhoneNumber(phoneNumber).addEmail(email)
                .addLogin(login).addPassword(password).addRating(Rating.NORMAL).addRoles(List.of(UserRole.DRIVER))
                .addStatus(DriverStatus.NOT_VERIFIED).build();
        try {
            if (DriverServiceImpl.getInstance().add(driver)) {
                driver = DriverServiceImpl.getInstance().findByLogin(driver.getLogin()).get();
                Car car = CarFactory.getInstance().create(driver, carNumber, carBrand, carModel, carColor);
                if(DriverServiceImpl.getInstance().addCar(car)) {
                    commandResult = new CommandResult("home?command=to_driver_verify_email", CommandResult.ResponseType.REDIRECT);
                    HttpSession session = servletRequest.getSession();
                    session.setAttribute("driver", driver);
                    session.setAttribute("userRoles", driver.getRoles());
                    return commandResult;
                }
                message = "Invalid car info";
            }
            message = "Sorry, aliens attacked our server ...\t Please, try again later";
        } catch (ServiceException e) {
            message = "Sorry, aliens attacked our server ... But they left some incomprehensible message : "+e+"...\n Please, try again later";
        } catch (ValidationException e) {
            message = "Invalid login, phone number, email or car number : "+e;
        } catch (FactoryException e) {
            //!
        }
        commandResult = new CommandResult("WEB-INF/jsp/common/driver_signin.jsp", CommandResult.ResponseType.FORWARD);
        commandResult.addAttribute("message", message);
        return commandResult;
    }
}

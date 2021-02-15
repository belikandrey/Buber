package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(LoginCommand.class);
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        if (servletRequest.getParameter("role").equals("client")) {
            return clientLogin(servletRequest, login, password);
        }
        return driverLogin(servletRequest, login, password);
    }

    private CommandResult clientLogin(HttpServletRequest servletRequest,String login, String password) {
        CommandResult commandResult;
        String message;
        try {
            Optional<Client> clientOptional = clientService.findByLogin(login);
            if (clientOptional.isPresent() && clientOptional.get().getPassword().equals(password)) {
                HttpSession session = servletRequest.getSession();
                session.setAttribute("userRoles", clientOptional.get().getRoles());
                session.setAttribute("currentPaymentType", "Cash");
                commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
                session.setAttribute("client", clientOptional.get());
                logger.info("Client logged successful");
                return commandResult;
            }
            logger.info("Client dont logged : Incorrect login or password");
            message = "Incorrect login or password";
        } catch (ServiceException e) {
            logger.error("Client dont logged : "+e.getMessage());
            message = "Sorry, aliens attacked our server ... But they left some incomprehensible message : " + e + "...\n Please, try again later";
        }
        commandResult = new CommandResult("WEB-INF/jsp/common/join.jsp", CommandResult.ResponseType.FORWARD);
        servletRequest.setAttribute("message", message);
        return commandResult;
    }

    private CommandResult driverLogin(HttpServletRequest servletRequest,String login,String password) {
        CommandResult commandResult;
        String message;
        try {
            Optional<Driver> driverOptional = driverService.findByLogin(login);
            if (driverOptional.isPresent() && driverOptional.get().getPassword().equals(password)) {
                HttpSession session = servletRequest.getSession();
                List<Car> driversCars = driverService.findDriversCars(driverOptional.get().getLogin());
                servletRequest.getSession().setAttribute("currentCar", driversCars.get(0));
                session.setAttribute("userRoles", driverOptional.get().getRoles());
                commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
                session.setAttribute("driver", driverOptional.get());
                logger.info("Driver logged successful");
                return commandResult;
            }
            logger.info("Client dont logged : Incorrect login or password");
            message = "Incorrect login or password";
        } catch (ServiceException e) {
            logger.error("Client dont logged : "+e.getMessage());
            message = "Sorry, aliens attacked our server ... But they left some incomprehensible message : " + e + "...\n Please, try again later";
        }
        commandResult = new CommandResult("WEB-INF/jsp/common/join.jsp", CommandResult.ResponseType.FORWARD);
        servletRequest.setAttribute("message", message);
        return commandResult;
    }

}

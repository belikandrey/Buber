package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.ClientStatus;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.DriverStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerifyEmailCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(VerifyEmailCommand.class);
    private final ClientService clientService = ClientServiceImpl.getInstance();
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        CommandResult commandResult = new CommandResult("home?command=to_verify_email", CommandResult.ResponseType.FORWARD);;
        String userCode = servletRequest.getParameter("userCode");
        String verificationCode = String.valueOf(session.getAttribute("code"));
        if (userCode.equals(verificationCode)) {
            session.removeAttribute("code");
            Client client = (Client) session.getAttribute("client");
            Driver driver = (Driver)session.getAttribute("driver");

            if(client!=null) {
                client.setStatus(ClientStatus.ACTIVE);
                try {
                    clientService.updateStatus(client.getLogin(), ClientStatus.ACTIVE);
                    servletRequest.getSession().setAttribute("currentPaymentType", "Cash");
                    commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
                    logger.info("Client verified");
                } catch (ServiceException e) {
                    logger.error("Client not verified : "+e.getMessage());
                    servletRequest.setAttribute("message", "Error at server! Message admin, please");
                }
            }else if(driver!=null){
                driver.setStatus(DriverStatus.WAITING_CONFIRM);
                try {
                    driverService.updateStatus(driver.getLogin(), DriverStatus.WAITING_CONFIRM);
                    commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
                    logger.info("Driver verified");
                } catch (ServiceException e) {
                    logger.error("Driver not verified : "+e);
                    servletRequest.setAttribute("message", "Error at server! Message admin, please");
                }

            }
            return commandResult;
        }
        servletRequest.setAttribute("message", "Incorrect code!");
        logger.info("Invalid code for verification");
        return commandResult;
    }
}

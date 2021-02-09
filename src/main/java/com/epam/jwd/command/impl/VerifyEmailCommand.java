package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.ClientStatus;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.DriverStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.ClientServiceImpl;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerifyEmailCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        CommandResult commandResult = new CommandResult("home?command=to_client_verify_email", CommandResult.ResponseType.FORWARD);;
        if (servletRequest.getParameter("userCode").equals(String.valueOf(session.getAttribute("code")))) {
            session.removeAttribute("code");
            Client client = (Client) session.getAttribute("client");
            Driver driver = (Driver)session.getAttribute("driver");

            if(client!=null) {
                client.setStatus(ClientStatus.ACTIVE);
                try {
                    ClientServiceImpl.getInstance().updateStatus(client.getLogin(), ClientStatus.ACTIVE);
                    commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
                } catch (ServiceException e) {
                    servletRequest.setAttribute("message", "Error at server! Message admin, please");
                }
            }else if(driver!=null){
                driver.setStatus(DriverStatus.WAITING_CONFIRM);
                try {
                    DriverServiceImpl.getInstance().updateStatus(driver.getLogin(), DriverStatus.WAITING_CONFIRM);
                    commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
                } catch (ServiceException e) {
                    servletRequest.setAttribute("message", "Error at server! Message admin, please");
                }

            }
            return commandResult;
        }
        servletRequest.setAttribute("message", "Incorrect code!");
        return commandResult;
    }
}

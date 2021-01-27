package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.DriverStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DriverVerifyEmailCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        CommandResult commandResult = new CommandResult("home?command=to_driver_verify_email", CommandResult.ResponseType.FORWARD);
        if(servletRequest.getParameter("userCode").equals(String.valueOf(session.getAttribute("code")))){
            session.removeAttribute("code");
            Driver driver = (Driver) session.getAttribute("driver");
            driver.setStatus(DriverStatus.WAITING_CONFIRM);
            try{
                DriverServiceImpl.getInstance().updateStatus(driver.getLogin(), driver.getStatus());
                commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
                return commandResult;
            }catch (ServiceException e){
                servletRequest.setAttribute("message", "Error at server! Message admin, please");
            }
            return commandResult;
        }
        servletRequest.setAttribute("message", "Incorrect code!");
        return commandResult;
    }
}

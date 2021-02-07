package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverPasswordCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("driver?command=to_driver_home", CommandResult.ResponseType.REDIRECT);
        String newPassword = servletRequest.getParameter("password");
        Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
        driver.setPassword(newPassword);
        try {
            DriverServiceImpl.getInstance().updatePassword(driver);
        } catch (ServiceException e) {
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.DriverServiceImpl;
import com.epam.jwd.validator.impl.PhoneNumberValidator;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverPhoneNumberCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("driver?command=to_update_driver_phone_number", CommandResult.ResponseType.REDIRECT);
        String newPhoneNumber = servletRequest.getParameter("newPhoneNumber");
        if(!PhoneNumberValidator.PHONE_NUMBER_VALIDATOR.validate(newPhoneNumber)){
            commandResult.addAttribute("message", "This number is invalid!");
        }else {
            Driver driver = (Driver) servletRequest.getSession().getAttribute("driver");
            driver.setPhoneNumber(newPhoneNumber);
            servletRequest.getSession().setAttribute("driver", driver);
            try {
                DriverServiceImpl.getInstance().updatePhoneNumber(driver.getId(), newPhoneNumber);
            } catch (ServiceException e) {
                commandResult.addAttribute("message", "Something was wrong");
            }
        }
        return commandResult;
    }
}

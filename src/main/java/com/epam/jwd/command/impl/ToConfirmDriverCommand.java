package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.DriverService;
import com.epam.jwd.service.impl.DriverServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToConfirmDriverCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ToConfirmDriverCommand.class);
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult;
        try {
            List<Driver> notConfirmedDrivers = driverService.findNotConfirmedDrivers();
            commandResult = new CommandResult("WEB-INF/jsp/admin/confirm_driver.jsp", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("drivers", notConfirmedDrivers);
        } catch (ServiceException e) {
            logger.error("Exception in find not confirmed drivers : "+e.getMessage());
            commandResult = new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "Something was wrong. Try again later");
        }
        return commandResult;
    }
}

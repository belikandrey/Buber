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

public class ToUpdateDriversCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ToUpdateDriversCommand.class);
    private final DriverService driverService = DriverServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/admin/update_drivers.jsp", CommandResult.ResponseType.FORWARD);
        try {
            List<Driver> drivers = driverService.findAll();
            commandResult.addAttribute("drivers", drivers);
        } catch (ServiceException e) {
            logger.error("Exception in find all drivers : "+e.getMessage());
            servletRequest.setAttribute("message", "Our server working hard, but something wrong... Try again later");
            commandResult = new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}

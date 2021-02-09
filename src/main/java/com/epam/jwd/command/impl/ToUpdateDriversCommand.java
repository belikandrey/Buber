package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.DriverServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToUpdateDriversCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/admin/update_drivers.jsp", CommandResult.ResponseType.FORWARD);
        try {
            List<Driver> drivers = DriverServiceImpl.getInstance().findAll();
            commandResult.addAttribute("drivers", drivers);
        } catch (ServiceException e) {
            servletRequest.setAttribute("message", "Our server working hard, but something wrong... Try again later");
            commandResult = new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}

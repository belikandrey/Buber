package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToShowRidesCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult ;

        try {
            List<Ride> allRides = AdminServiceImpl.getInstance().findAllRides();
            commandResult = new CommandResult("WEB-INF/jsp/admin/show_rides.jsp", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("rides", allRides);
        } catch (ServiceException e) {
            commandResult = new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "Something wrong with system. Try again");
        }
        return commandResult;
    }
}

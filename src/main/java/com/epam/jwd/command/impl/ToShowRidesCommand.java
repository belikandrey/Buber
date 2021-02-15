package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToShowRidesCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ToShowRidesCommand.class);
    private final AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult ;
        try {
            List<Ride> allRides = adminService.findAllRides();
            commandResult = new CommandResult("WEB-INF/jsp/admin/show_rides.jsp", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("rides", allRides);
        } catch (ServiceException e) {
            logger.error("Exception in find all rides : "+e.getMessage());
            commandResult = new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "Something wrong with system. Try again");
        }
        return commandResult;
    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.DriverStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverStatusCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(UpdateDriverStatusCommand.class);
    private final AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_update_drivers", CommandResult.ResponseType.FORWARD);
        String newStatus = servletRequest.getParameter("newStatus");
        String login = servletRequest.getParameter("login");
        if (login == null) {
            commandResult.addAttribute("message", "Error at server. Try again later");
            logger.error("Driver login is null");
        } else if (newStatus != null) {
            DriverStatus status = DriverStatus.valueOf(newStatus);
            try {
                adminService.updateDriverStatus(login, status);
                logger.info("Driver status updated successful");
            } catch (ServiceException e) {
                logger.error("Driver status not updated : " + e.getMessage());
                commandResult.addAttribute("message", "status not updated");
            }
        }
        return commandResult;
    }
}

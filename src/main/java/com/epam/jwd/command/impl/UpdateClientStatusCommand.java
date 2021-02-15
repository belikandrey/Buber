package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.ClientStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UpdateClientStatusCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(UpdateClientStatusCommand.class);
    private final AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_update_clients", CommandResult.ResponseType.FORWARD);
        String newStatus = servletRequest.getParameter("newStatus");
        String login = servletRequest.getParameter("login");
        if (login == null) {
            logger.error("Client login is empty");
            commandResult.addAttribute("message", "Sorry. Try again later");
        } else if (newStatus != null) {
            ClientStatus status = ClientStatus.valueOf(newStatus);
            try {
                adminService.updateClientStatus(login, status);
                logger.info("Client status updated successful");
            } catch (ServiceException e) {
                logger.error("Client status dont updated : " + e.getMessage());
                commandResult.addAttribute("message", "Sorry. Try again later");
            }
        } else {
            commandResult.addAttribute("message", "Sorry, something was wrong. Try again later");
        }
        return commandResult;
    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UpdateClientRatingCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(UpdateClientRatingCommand.class);
    private final AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_update_clients", CommandResult.ResponseType.FORWARD);
        String login = servletRequest.getParameter("login");
        String ratingStr = servletRequest.getParameter("newRating");
        Integer newRating = ratingStr == null ? null : Integer.parseInt(ratingStr);
        if (newRating != null) {
            try {
                adminService.updateClientRating(login, newRating);
                logger.info("Clients rating updated successful");
            } catch (ServiceException e) {
                logger.error("Client rating not updated : "+e.getMessage());
                commandResult.addAttribute("message", "Rating not updated");
            }
        }else{
            logger.info("New clients rating is empty");
            commandResult.addAttribute("message", "Put new rating to update");
        }
        return commandResult;
    }
}

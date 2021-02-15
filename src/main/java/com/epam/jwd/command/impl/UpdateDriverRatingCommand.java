package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverRatingCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(UpdateDriverRatingCommand.class);
    private final AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_update_drivers", CommandResult.ResponseType.FORWARD);
        String login = servletRequest.getParameter("login");
        String ratingStr = servletRequest.getParameter("newRating");
        Integer newRating = ratingStr==null?null:Integer.parseInt(ratingStr);
        if(login==null){
            commandResult.addAttribute("message", "Error at server");
            logger.error("Driver login is null");
        }else if(newRating!=null){
                try {
                    adminService.updateDriverRating(login, newRating);
                    logger.info("Driver rating updated successful");
                } catch (ServiceException e) {
                    logger.error("Driver rating not updated : "+e.getMessage());
                    commandResult.addAttribute("message", "rating not updated");
                }
        }
        return commandResult;
    }
}

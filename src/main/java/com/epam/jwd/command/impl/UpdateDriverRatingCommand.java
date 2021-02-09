package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.DriverStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverRatingCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_update_drivers", CommandResult.ResponseType.FORWARD);
        String login = servletRequest.getParameter("login");
        String ratingStr = servletRequest.getParameter("newRating");
        Integer newRating = ratingStr==null?null:Integer.parseInt(ratingStr);
        if(login==null){
            commandResult.addAttribute("message", "Error at server");
        }else if(newRating!=null){
                try {
                    AdminServiceImpl.getInstance().updateDriverRating(login, newRating);
                } catch (ServiceException e) {
                    commandResult.addAttribute("message", "rating not updated");
                }
        }
        return commandResult;
    }
}

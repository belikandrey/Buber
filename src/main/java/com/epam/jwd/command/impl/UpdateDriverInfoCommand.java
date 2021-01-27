package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.DriverStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverInfoCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_update_driver_info", CommandResult.ResponseType.REDIRECT);
        String newStatus = servletRequest.getParameter("newStatus");
        String login = servletRequest.getParameter("login");
        String ratingStr = servletRequest.getParameter("newRating");
        Integer newRating = ratingStr==null?null:Integer.parseInt(ratingStr);
        if(login==null){
            commandResult.addAttribute("message", "Error at server");
        }else{
            if(newStatus!=null){
                DriverStatus status = DriverStatus.valueOf(newStatus);
                try {
                    AdminServiceImpl.getInstance().updateDriverStatus(login, status);
                    commandResult.addAttribute("message", "successful");
                } catch (Exception e){
                    commandResult.addAttribute("message", "status not updated");
                }
            }
            if(newRating!=null){
                try {
                    AdminServiceImpl.getInstance().updateDriverRating(login, newRating);
                    commandResult.addAttribute("message", "successful");
                } catch (ServiceException e) {
                    commandResult.addAttribute("message", "rating not updated");
                }
            }
        }
        return commandResult;
    }
}

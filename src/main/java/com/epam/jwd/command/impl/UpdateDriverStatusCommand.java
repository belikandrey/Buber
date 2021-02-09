package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.DriverStatus;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UpdateDriverStatusCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_update_drivers", CommandResult.ResponseType.FORWARD);
        String newStatus = servletRequest.getParameter("newStatus");
        String login = servletRequest.getParameter("login");
        if(login==null){
            commandResult.addAttribute("message", "Error at server");
        }else if(newStatus!=null){
                DriverStatus status = DriverStatus.valueOf(newStatus);
                try {
                    AdminServiceImpl.getInstance().updateDriverStatus(login, status);
                } catch (Exception e){
                    commandResult.addAttribute("message", "status not updated");
                }
        }
        return commandResult;
    }
}

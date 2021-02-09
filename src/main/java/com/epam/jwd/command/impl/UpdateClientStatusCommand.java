package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.ClientStatus;
import com.epam.jwd.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UpdateClientStatusCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_update_clients", CommandResult.ResponseType.FORWARD);
        String newStatus = servletRequest.getParameter("newStatus");
        String login = servletRequest.getParameter("login");
        if(login == null){
            commandResult.addAttribute("message", "Error at server");
        }else{
            if(newStatus!=null){
                ClientStatus status = ClientStatus.valueOf(newStatus);
                try {
                    AdminServiceImpl.getInstance().updateClientStatus(login, status);
                } catch (Exception e){
                    commandResult.addAttribute("message", "Status not updated");
                }
            }
        }
        return commandResult;
    }
}

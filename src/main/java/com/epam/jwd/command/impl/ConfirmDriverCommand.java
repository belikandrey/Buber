package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ConfirmDriverCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_confirm_driver", CommandResult.ResponseType.FORWARD);
        String login = servletRequest.getParameter("login");
        try {
            AdminServiceImpl.getInstance().confirmDriver(login);
        } catch (Exception e){
            commandResult.addAttribute("message", "Something was wrong. Try again later : "+e.getMessage());
        }
        return commandResult;
    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ConfirmDriverCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ConfirmDriverCommand.class);
    private final AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_confirm_driver", CommandResult.ResponseType.FORWARD);
        String login = servletRequest.getParameter("login");
        try {
            adminService.confirmDriver(login);
            logger.info("Driver confirmed successful");
        } catch (Exception e){
            logger.error("Driver not confirmed : "+e.getMessage());
            commandResult.addAttribute("message", "Something was wrong. Try again later : "+e.getMessage());
        }
        return commandResult;
    }
}

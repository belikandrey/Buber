package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToUpdateClientsCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/admin/update_clients.jsp", CommandResult.ResponseType.FORWARD);
        try {
            List<Client> clients = ClientServiceImpl.getInstance().findAll();
            commandResult.addAttribute("clients", clients);
            return commandResult;
        } catch (ServiceException e) {
            commandResult = new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "Our server working hard, but something wrong... Try again later");
            return commandResult;
        }

    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.Ride;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToShowClientRidesCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/client/show_rides.jsp", CommandResult.ResponseType.FORWARD);
        Client client = (Client) servletRequest.getSession().getAttribute("client");
        try {
            List<Ride> ridesByClientId = ClientServiceImpl.getInstance().findRidesByClientId(client.getId());
            commandResult.addAttribute("rides", ridesByClientId);
        } catch (ServiceException e) {
            commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "something was wrong");
        }
        return commandResult;
    }
}

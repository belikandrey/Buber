package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.BankCard;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToClientHomeCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/client/home.jsp", CommandResult.ResponseType.FORWARD);
        Client client = (Client) servletRequest.getSession().getAttribute("client");

        try {
            List<BankCard> allClientCard = ClientServiceImpl.getInstance().findAllClientCard(client.getLogin());
            servletRequest.setAttribute("cards", allClientCard);
        } catch (ServiceException e) {
            commandResult.addAttribute("message", "Something was wrong");
        }
        return commandResult;
    }
}

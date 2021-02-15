package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.BankCard;
import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.ClientService;
import com.epam.jwd.service.impl.ClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CurrentPaymentCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(CurrentPaymentCommand.class);
    private final ClientService clientService = ClientServiceImpl.getInstance();
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("client?command=to_client_home", CommandResult.ResponseType.REDIRECT);
        String type = servletRequest.getParameter("type");
        Client client = (Client) servletRequest.getSession().getAttribute("client");
        if(type.equals("cash")){
            servletRequest.getSession().setAttribute("currentPaymentType", "Cash");
            logger.info("Current payment type selected successful");
        }else {
            try {
                List<BankCard> cards = clientService.findAllClientCard(client.getLogin());
                BankCard bankCard = cards.stream().filter((p) -> p.getNumber().equals(type)).findAny().get();
                servletRequest.getSession().setAttribute("currentPaymentType", bankCard.getNumber());
                logger.info("Current payment type selected successful");
            } catch (ServiceException e) {
                logger.error("Current payment type not selected : "+e.getMessage());
                commandResult.addAttribute("message", "Something was wrong");
            }
        }
        return commandResult;
    }
}

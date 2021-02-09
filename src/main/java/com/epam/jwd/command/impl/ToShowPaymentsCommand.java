package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Payment;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToShowPaymentsCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult;
        List<Payment> allPayments = null;
        try {
            allPayments = AdminServiceImpl.getInstance().findAllPayments();
            commandResult = new CommandResult("WEB-INF/jsp/admin/show_payments.jsp", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("payments", allPayments);
        } catch (ServiceException e) {
            commandResult = new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "Something were wrong. Try again");
        }
        return commandResult;
    }
}

package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.Payment;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToShowPaymentsCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(ToShowPaymentsCommand.class);
    private final AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult;
        List<Payment> allPayments ;
        try {
            allPayments = adminService.findAllPayments();
            commandResult = new CommandResult("WEB-INF/jsp/admin/show_payments.jsp", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("payments", allPayments);
        } catch (ServiceException e) {
            logger.error("Exception in find all payments : "+e.getMessage());
            commandResult = new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.FORWARD);
            commandResult.addAttribute("message", "Something were wrong. Try again");
        }
        return commandResult;
    }
}

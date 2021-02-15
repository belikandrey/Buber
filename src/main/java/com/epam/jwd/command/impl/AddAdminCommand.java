package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.User;
import com.epam.jwd.domain.impl.UserRole;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.AbstractFactory;
import com.epam.jwd.factory.impl.UserFactory;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

public class AddAdminCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(AddAdminCommand.class);
    private final AdminService adminService = AdminServiceImpl.getInstance();
    private final AbstractFactory<User> userFactory = UserFactory.getInstance();
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("WEB-INF/jsp/admin/add_admin.jsp", CommandResult.ResponseType.FORWARD);
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        if(login!=null && password!=null ){
            try {
                User user = userFactory.create(login, password, Collections.singletonList(UserRole.ADMIN));
                adminService.addAdmin(user);
                commandResult.addAttribute("message", "Successful!");
                logger.info("Admin added successful");
                return commandResult;
            } catch (FactoryException e) {
                logger.error("Admin not added : "+e);
                commandResult.addAttribute("message", "Impossible to create admin");
            } catch (ServiceException e) {
                logger.info("Login is busy : "+login+"\tMessage from exception : "+e.getMessage());
                commandResult.addAttribute("message", "This login is busy");
            } catch (ValidationException e) {
                logger.info("Invalid login or password");
                commandResult.addAttribute("message", "Invalid login or password");
            }
        }else{
            logger.info("Invalid data");
            commandResult.addAttribute("message", "You should enter info");
        }
        return commandResult;
    }
}

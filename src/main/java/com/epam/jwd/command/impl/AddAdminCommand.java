package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.User;
import com.epam.jwd.domain.impl.UserRole;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.UserFactory;
import com.epam.jwd.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

public class AddAdminCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        CommandResult commandResult = new CommandResult("admin?command=to_add_admin", CommandResult.ResponseType.FORWARD);
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        if(login!=null && password!=null ){
            try {
                User user = UserFactory.getInstance().create(login, password, Collections.singletonList(UserRole.ADMIN));
                AdminServiceImpl.getInstance().addAdmin(user);
                commandResult.addAttribute("message", "successful!");
                return commandResult;
            } catch (FactoryException e) {
                commandResult.addAttribute("message", "Impossible to create admin");
            } catch (ServiceException e) {
                commandResult.addAttribute("message", "This login is busy");
            } catch (ValidationException e) {
                commandResult.addAttribute("message", "Invalid login or password");
            }
        }else{
            commandResult.addAttribute("message", "You should enter info");
        }
        return commandResult;
    }
}

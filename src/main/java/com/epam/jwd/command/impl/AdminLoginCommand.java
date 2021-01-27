package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.User;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AdminLoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        CommandResult commandResult;
        String message = null;
        try {
            Optional<User> adminOptional = AdminServiceImpl.getInstance().findByLogin(login);
            if(adminOptional.isPresent() && adminOptional.get().getPassword().equals(password)){
                HttpSession session = servletRequest.getSession();
                session.setAttribute("userRoles", adminOptional.get().getRoles());
                commandResult =  new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.REDIRECT);
                session.setAttribute("admin", adminOptional.get());
                return commandResult;
            }
            message = "Incorrect login or password";
        } catch (ServiceException e) {
            message = "Sorry, aliens attacked our server ... But they left some incomprehensible message : "+e+"...\n Please, try again later";
        } catch (ValidationException e) {
            message = "Invalid login";
        }
        commandResult = new CommandResult("WEB-INF/jsp/common/admin_login.jsp", CommandResult.ResponseType.FORWARD);
        servletRequest.setAttribute("message", message);
        return commandResult;
    }
}

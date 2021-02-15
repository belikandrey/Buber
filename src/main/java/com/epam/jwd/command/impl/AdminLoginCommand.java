package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.User;
import com.epam.jwd.domain.impl.UserRole;
import com.epam.jwd.exception.ServiceException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.AdminService;
import com.epam.jwd.service.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AdminLoginCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(AdminLoginCommand.class);
    private final AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest servletRequest) {
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        CommandResult commandResult;
        String message;
        try {
            Optional<User> adminOptional = adminService.findByLogin(login);

            if (adminOptional.isPresent()) {
                User user = adminOptional.get();
                if (user.getPassword().equals(password) && user.getRoles().contains(UserRole.ADMIN)) {
                    HttpSession session = servletRequest.getSession();
                    session.setAttribute("userRoles", user.getRoles());
                    commandResult = new CommandResult("admin?command=to_admin_home", CommandResult.ResponseType.REDIRECT);
                    session.setAttribute("admin", user);
                    logger.info("Admin log in successful");
                    return commandResult;
                }
            }
            logger.info("Wrong login : " + login);
            message = "Incorrect login or password";
        } catch (ServiceException e) {
            logger.error("Admin not log in : " + e.getMessage());
            message = "Please, try again later";
        } catch (ValidationException e) {
            logger.info("Invalid login : " + login);
            message = "You should enter login and password";
        }
        commandResult = new CommandResult("WEB-INF/jsp/common/admin_log_in.jsp", CommandResult.ResponseType.FORWARD);
        servletRequest.setAttribute("message", message);
        return commandResult;
    }
}

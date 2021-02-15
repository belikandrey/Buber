package com.epam.jwd.filter;

import com.epam.jwd.domain.impl.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter {
    private final Logger logger = LoggerFactory.getLogger(AdminFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<UserRole> roles = (List<UserRole>) req.getSession().getAttribute("userRoles");
        if (roles == null || !roles.contains(UserRole.ADMIN)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home?command=to_admin_log_in");
            req.setAttribute("message", "You must log in before start");
            logger.info("Not authorized user goes as admin");
            requestDispatcher.forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }
}

package com.epam.jwd.filter;

import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.DriverStatus;
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

@WebFilter("/driver/*")
public class DriverFilter extends HttpFilter {
    private final Logger logger = LoggerFactory.getLogger(AdminFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<UserRole> roleList = (List<UserRole>) req.getSession().getAttribute("userRoles");
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        if (roleList == null || !roleList.contains(UserRole.DRIVER)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home?command=to_join");
            req.setAttribute("message", "You must log in before start");
            logger.info("Not authorized user goes as driver");
            requestDispatcher.forward(req, res);
        } else if (driver != null && driver.getStatus() == DriverStatus.NOT_VERIFIED) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home?command=to_verify_email");
            req.setAttribute("message", "You should verify email before start using app");
            logger.info("Not verified user goes as driver");
            requestDispatcher.forward(req, res);
        } else if (driver != null && driver.getStatus() == DriverStatus.WAITING_CONFIRM) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home?command=to_join");
            req.setAttribute("message", "Wait a confirm before start ride");
            logger.info("Waiting confirm driver tried to use app");
            requestDispatcher.forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }
}

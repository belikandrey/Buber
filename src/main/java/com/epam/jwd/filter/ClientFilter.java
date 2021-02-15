package com.epam.jwd.filter;

import com.epam.jwd.domain.impl.Client;
import com.epam.jwd.domain.impl.ClientStatus;
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

@WebFilter("/client/*")
public class ClientFilter extends HttpFilter {
    private final Logger logger = LoggerFactory.getLogger(ClientFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<UserRole> roleList = (List<UserRole>) req.getSession().getAttribute("userRoles");
        Client client = (Client) req.getSession().getAttribute("client");
        if (roleList == null || !roleList.contains(UserRole.CLIENT)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home?command=to_join");
            req.setAttribute("message", "You must log in before start");
            logger.info("Not authorized user goes as client");
            requestDispatcher.forward(req, res);
        } else if (client != null && client.getStatus() == ClientStatus.NOT_VERIFIED) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home?command=to_client_verify_email");
            req.setAttribute("message", "You should verify email before start using app");
            logger.info("Not verified user goes as client");
            requestDispatcher.forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }
}

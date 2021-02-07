package com.epam.jwd.filter;

import com.epam.jwd.domain.impl.UserRole;

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
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<UserRole> roles = (List<UserRole>) req.getSession().getAttribute("userRoles");
        if(roles==null || !roles.contains(UserRole.ADMIN)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home?command=to_admin_login");
            req.setAttribute("message", "You must log in before start");
            requestDispatcher.forward(req, res);
        }
        chain.doFilter(req, res);
    }
}
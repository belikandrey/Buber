package com.epam.jwd.filter;

import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.domain.impl.DriverStatus;
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

@WebFilter("/driver/*")
public class DriverFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<UserRole> roleList = (List<UserRole>) req.getSession().getAttribute("userRoles");
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        if(roleList==null || !roleList.contains(UserRole.DRIVER)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home?command=to_driver_common");
            req.setAttribute("message", "You must log in before start");
            requestDispatcher.forward(req, res);
        }else if(driver!=null && driver.getStatus()== DriverStatus.NOT_VERIFIED){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("home?command=to_driver_verify_email");
            req.setAttribute("message", "You should verify email before start using app");
            requestDispatcher.forward(req, res);
        }else {
            chain.doFilter(req, res);
        }
    }
}

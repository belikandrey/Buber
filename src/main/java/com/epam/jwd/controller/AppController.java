package com.epam.jwd.controller;

import com.epam.jwd.command.CommandFactory;
import com.epam.jwd.command.CommandResult;
import com.epam.jwd.domain.impl.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/home", "/client", "/driver", "/admin"})
public class AppController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        System.out.println("\n\n");
        System.out.println("Method : "+req.getMethod());
        String command = req.getParameter("command");
        System.out.println("Command : "+command);

        CommandResult commandResult = CommandFactory.command(command).execute(req);
        List<UserRole> userRoles = (List<UserRole>) req.getSession().getAttribute("userRoles");
        System.out.println("Roles from session : "+userRoles);
        System.out.println("REQUEST PARAMETERS : ");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            System.out.println(s+" : " +req.getParameter(s));
        }
        System.out.println("REQUEST ATTRIBUTES : ");
        Enumeration<String> attributeNames1 = req.getAttributeNames();
        while (attributeNames1.hasMoreElements()){
            String s = attributeNames1.nextElement();
            System.out.println(s+" : "+req.getAttribute(s));
        }
        System.out.println("SESSEION PARAMETERS : ");
        Enumeration<String> attributeNames = req.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String s = attributeNames.nextElement();
            System.out.println(s+" : "+req.getSession().getAttribute(s));
        }

        System.out.println("NEW PARAMETERS FROM COMMAND RESULT");
        for(Map.Entry<String, Object> entry:commandResult.getAttributes().entrySet()){
            System.out.println(entry.getKey()+ " : " + entry.getValue());
        }

        for(Map.Entry<String, Object> entry:commandResult.getAttributes().entrySet()){
            req.setAttribute(entry.getKey(), entry.getValue());
        }
        if (commandResult.getResponseType().equals(CommandResult.ResponseType.FORWARD)) {
            req.getRequestDispatcher(commandResult.getPage()).forward(req, resp);
        } else {
            resp.sendRedirect(commandResult.getPage());
        }
    }
}

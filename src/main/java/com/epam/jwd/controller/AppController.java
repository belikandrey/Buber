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
        String command = req.getParameter("command");

        CommandResult commandResult = CommandFactory.command(command).execute(req);

        for (Map.Entry<String, Object> entry : commandResult.getAttributes().entrySet()) {
            req.setAttribute(entry.getKey(), entry.getValue());
        }
        if (commandResult.getResponseType().equals(CommandResult.ResponseType.FORWARD)) {
            req.getRequestDispatcher(commandResult.getPage()).forward(req, resp);
        } else {
            resp.sendRedirect(commandResult.getPage());
        }
    }
}

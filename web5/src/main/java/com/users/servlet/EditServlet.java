package com.users.servlet;

import com.users.exception.DBException;
import com.users.model.User;
import com.users.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private String userId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userId = req.getParameter("userId");
        req.setAttribute("userId", userId);
        getServletContext().getRequestDispatcher("/edit-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String value = null;
        req.setAttribute("value", value);
        String name = req.getParameter("name");
        String surName = req.getParameter("surName");
        try {
            if ((!"".equals(name) && !"".equals(surName)) && (name != null && surName != null)) {
                Long id = Long.parseLong(userId);
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setSurName(surName);
                UserService.getUserService().updateUser(user);
                value = "not null";
                req.setAttribute("value", value);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/edit-user.jsp").forward(req, resp);
    }
}

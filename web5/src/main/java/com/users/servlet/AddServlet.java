package com.users.servlet;

import com.users.dao.DBException;
import com.users.model.User;
import com.users.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surName = req.getParameter("surName");
        User user = new User();
        user.setName(name);
        user.setSurName(surName);
        String value = null;
        req.setAttribute("value", value);
        try {
            if ((!"".equals(name) && !"".equals(surName)) && (name != null && surName != null)) {
                new UserService().addUser(user);
                value = "not null";
                req.setAttribute("value", value);
            }
        } catch (DBException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/add-user.jsp").forward(req, resp);
    }
}

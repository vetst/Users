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
import java.util.List;

@WebServlet("/main")
public class GeneralServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List userList = new UserService().getAllUser();
            req.setAttribute("userList", userList);
            getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        try {
            Long id = Long.parseLong(userId);
            User user = new User();
            user.setId(id);
            new UserService().deleteUser(user);
            List userList = new UserService().getAllUser();
            req.setAttribute("userList", userList);
            getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}

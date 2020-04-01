package com.users.servlet;

import com.users.exception.DBException;
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
            List userList = UserService.getUserService().getAllUser();
            req.setAttribute("userList", userList);
            getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }


}

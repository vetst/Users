package com.users.servlet;

import com.users.model.User;
import com.users.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private String userId = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userId = req.getParameter("userId");
        String message = "Вы действительно хотите удалить пользователя ?";
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/delete-user.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String someValue = req.getParameter("value");
        try {
            Long id = Long.parseLong(userId);
            int intSomeValue = 0;
            if (someValue != null) {
                intSomeValue = Integer.parseInt(someValue);
            }
            if (id != 0 && intSomeValue == 1) {
                User user = new User();
                user.setId(id);
                UserService.getUserService().deleteUser(user);
                req.setAttribute("userIdRedirect", userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/delete-user.jsp").forward(req, resp);
    }
}

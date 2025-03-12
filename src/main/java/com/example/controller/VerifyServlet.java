package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.dao.UserDAO;

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");

        UserDAO userDAO = new UserDAO();
        if (userDAO.verifyUser(token)) {
            response.sendRedirect("login.jsp?verified=1");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}

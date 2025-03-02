package com.example.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

import com.example.dao.UserDAO;
import com.example.model.User;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cin = request.getParameter("cin");
        String type = request.getParameter("type");

        User user = new User(fullName, email, password, cin, type);
        UserDAO userDAO = new UserDAO();
        
        if (userDAO.registerUser(user)) {
            response.sendRedirect("login.jsp?success=1");
        } else {
            response.sendRedirect("signup.jsp?error=1");
        }
    }
}

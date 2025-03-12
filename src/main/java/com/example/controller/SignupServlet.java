package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.utils.EmailUtil;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cin = request.getParameter("cin");
        String type = request.getParameter("type");
        System.out.println("Email: " + email);
        String token = UUID.randomUUID().toString();
        User user = new User(fullName, email, password, cin, type);
        UserDAO userDAO = new UserDAO();

        if (userDAO.registerUser(user, token)) {
            String verificationLink = "http://localhost:8085/app-1.0.0/verify?token=" + token;
            EmailUtil.sendEmail(email, "Verify Your Account", "Click the link to verify: " + verificationLink);
            response.sendRedirect("login.jsp?verify=1");
        } else {
            response.sendRedirect("signup.jsp?error=1");
        }
    }
}

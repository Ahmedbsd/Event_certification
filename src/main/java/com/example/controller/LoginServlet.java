package com.example.controller;




import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.example.dao.UserDAO;
import com.example.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.loginUser(email, password);
        
        if (user != null) {
        	String p = "participant";
        	String ad = "admin";
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getIdUser());
            if(user.getType().equals(ad)) {
            	response.sendRedirect(request.getContextPath() + "/EventServlet");
            }else if(user.getType().equals(p)) {
            	response.sendRedirect(request.getContextPath() + "/EventParticipant");
            }else {
            	System.out.println(user.getType());
            }
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}

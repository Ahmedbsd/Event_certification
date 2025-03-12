package com.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.example.dao.EventDAO;
import com.example.model.Event;

@WebServlet("/EditEventServlet")
public class EditEventServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Event event = eventDAO.getEventById(id);
            request.setAttribute("event", event);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editEvent.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String date = request.getParameter("date");
        String period = request.getParameter("period");

        HttpSession session = request.getSession();
        int managerId = (Integer) session.getAttribute("user_id");

        Event updatedEvent = new Event(id, title, description, date, period, managerId);
        
        try {
            eventDAO.updateEvent(updatedEvent);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("EventServlet");
    }
}

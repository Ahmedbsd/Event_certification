package com.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import com.example.dao.EventDAO;
import com.example.dao.ParticipantDAO;
import com.example.model.Event;
import com.example.model.Participant;

@WebServlet("/EventParticipant")
@MultipartConfig
public class ParticipantEventServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();
    private ParticipantDAO participantDAO = new ParticipantDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
        	HttpSession session = request.getSession();
            int ParticipantId = (Integer) session.getAttribute("user_id");
            List<Event> events = eventDAO.getEventByParticipant(ParticipantId);
            request.setAttribute("events", events);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ParticipantEvent.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

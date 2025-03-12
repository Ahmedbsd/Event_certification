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

@WebServlet("/EventServlet")
@MultipartConfig
public class EventServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();
    private ParticipantDAO participantDAO = new ParticipantDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                eventDAO.deleteEvent(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("edit".equals(action)) { // Load event details for editing
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Event event = eventDAO.getEventById(id);
                request.setAttribute("event", event);
                RequestDispatcher dispatcher = request.getRequestDispatcher("editEvent.jsp");
                dispatcher.forward(request, response);
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            List<Event> events = eventDAO.getAllEvents();
            request.setAttribute("events", events);
            RequestDispatcher dispatcher = request.getRequestDispatcher("events.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        int managerId = (Integer) session.getAttribute("user_id");

        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String date = request.getParameter("date");
            String period = request.getParameter("period");
            Part filePart = request.getPart("participantsFile");

            Event event = new Event(title, description, date, period, managerId);
            try {
                int eventId = eventDAO.addEvent(event);

                if (filePart != null && filePart.getSize() > 0) {
                    parseAndInsertParticipants(filePart, eventId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("update".equals(action)) { // Handle event update
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String date = request.getParameter("date");
            String period = request.getParameter("period");

            Event updatedEvent = new Event(id, title, description, date, period, managerId);
            try {
                eventDAO.updateEvent(updatedEvent);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("EventServlet");
    }

    private void parseAndInsertParticipants(Part filePart, int eventId) throws IOException {
        try (InputStream inputStream = filePart.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String firstName = data[0].trim();
                    String secondName = data[1].trim();
                    String cin = data[2].trim();
                    String role = data[3].trim();

                    Participant participant = new Participant(firstName, secondName, cin, role, eventId);
                    participantDAO.addParticipant(participant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.example.controller;




import com.example.dao.ParticipantDAO;
import com.example.model.Participant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/participants")
public class ParticipantServlet extends HttpServlet {
    private ParticipantDAO participantDAO;

    @Override
    public void init() throws ServletException {
        participantDAO = new ParticipantDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Participant> participants = participantDAO.getAllParticipants();
            request.setAttribute("participants", participants);
            request.getRequestDispatcher("participants.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                Participant participant = new Participant(0, request.getParameter("first_name"),
                        request.getParameter("second_name"), request.getParameter("cin"),
                        request.getParameter("role"), Integer.parseInt(request.getParameter("id_event")));
                participantDAO.addParticipant(participant);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                participantDAO.deleteParticipant(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("participants");
    }
}

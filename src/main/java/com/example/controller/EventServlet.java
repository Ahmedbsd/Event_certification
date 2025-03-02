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
	import java.util.List;

import com.example.dao.EventDAO;
import com.example.model.Event;
	
	
	
	@WebServlet("/EventServlet")
	public class EventServlet extends HttpServlet {
	    private EventDAO eventDAO = new com.example.dao.EventDAO();
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");
			HttpSession session = request.getSession();
			int managerId = (Integer) session.getAttribute("user_id");
	        if ("add".equals(action)) {
	            String title = request.getParameter("title");
	            String description = request.getParameter("description");
	            String date = request.getParameter("date");
	            String period = request.getParameter("period");
	
	            Event event = new Event(title, description, date, period, managerId);
	            try {
	                eventDAO.addEvent(event);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else if ("update".equals(action)) {
	            int id = Integer.parseInt(request.getParameter("id"));
	            String title = request.getParameter("title");
	            String description = request.getParameter("description");
	            String date = request.getParameter("date");
	            String period = request.getParameter("period");
	
	            Event event = new Event(id, title, description, date, period, 0); // Manager ID not needed for update
	            try {
	                eventDAO.updateEvent(event);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else if ("delete".equals(action)) {
	            int id = Integer.parseInt(request.getParameter("id"));
	            try {
	            	System.out.println(id);
	                eventDAO.deleteEvent(id);
	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	
	        response.sendRedirect("EventServlet");
	    }
	
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String action = request.getParameter("action");
	    	if ("delete".equals(action)) {
	            int id = Integer.parseInt(request.getParameter("id"));
	            try {
	            	System.out.println(id);
	                eventDAO.deleteEvent(id);
	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }}
	        try {
	            List<Event> events = eventDAO.getAllEvents();
	            request.setAttribute("events", events);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("events.jsp");
	            dispatcher.forward(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

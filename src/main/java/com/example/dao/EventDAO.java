package com.example.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Event;

public class EventDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
    public void addEvent(Event event) throws SQLException {
        String query = "INSERT INTO events (title, description, date, period, manager_id) VALUES (?, ?, ?, ?, ?)";
        try {
        	conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, event.getTitle());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getDate());
            stmt.setString(4, event.getPeriod());
            stmt.setInt(5, event.getManagerId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";
        try {
        	 conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("id_event"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("date"),
                        rs.getString("period"),
                        rs.getInt("manager_id")
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return events;
    }

    
    public void updateEvent(Event event) throws SQLException {
        String query = "UPDATE events SET title=?, description=?, date=?, period=? WHERE id_event=?";
        try {
        	 conn = DBConnection.getConnection();
             stmt = conn.prepareStatement(query);
            stmt.setString(1, event.getTitle());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getDate());
            stmt.setString(4, event.getPeriod());
            stmt.setInt(5, event.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
    }

    public void deleteEvent(int id) throws SQLException {
        String query = "DELETE FROM events WHERE id_event=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

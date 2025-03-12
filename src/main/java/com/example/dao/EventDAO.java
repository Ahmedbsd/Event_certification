package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Event;

public class EventDAO {

    public int addEvent(Event event) throws SQLException {
        String query = "INSERT INTO events (title, description, date, period, manager_id) VALUES (?, ?, ?, ?, ?)";
        int eventId = -1; // Default value if insertion fails

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, event.getTitle());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getDate());
            stmt.setString(4, event.getPeriod());
            stmt.setInt(5, event.getManagerId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        eventId = rs.getInt(1); // Retrieve generated event ID
                    }
                }
            }
        }
        return eventId;
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

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
        }
        return events;
    }

    public void updateEvent(Event event) throws SQLException {
        String query = "UPDATE events SET title=?, description=?, date=?, period=? WHERE id_event=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, event.getTitle());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getDate());
            stmt.setString(4, event.getPeriod());
            stmt.setInt(5, event.getId());

            stmt.executeUpdate();
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
    
    public Event getEventById(int id) throws SQLException {
        String sql = "SELECT * FROM events WHERE id_event = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Event(
                        rs.getInt("id_event"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("date"),
                        rs.getString("period"),
                        rs.getInt("manager_id")
                    );
                }
            }
        }
        return null;
    }
}

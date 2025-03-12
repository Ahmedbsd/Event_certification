package com.example.dao;

import com.example.model.Participant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    public void addParticipant(Participant participant) throws SQLException {
        String query = "INSERT INTO participant (first_name, second_name, cin, role, id_event) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, participant.getFirstName());
            stmt.setString(2, participant.getSecondName());
            stmt.setString(3, participant.getCin());
            stmt.setString(4, participant.getRole());
            stmt.setInt(5, participant.getIdEvent());

            stmt.executeUpdate();
        }
    }

    public List<Participant> getAllParticipants() throws SQLException {
        List<Participant> participants = new ArrayList<>();
        String query = "SELECT * FROM participant";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                participants.add(new Participant(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("second_name"),
                        rs.getString("cin"),
                        rs.getString("role"),
                        rs.getInt("id_event")
                ));
            }
        }
        return participants;
    }

    public Participant getParticipantById(int id) throws SQLException {
        String query = "SELECT * FROM participant WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Participant(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("second_name"),
                            rs.getString("cin"),
                            rs.getString("role"),
                            rs.getInt("id_event")
                    );
                }
            }
        }
        return null;
    }

    public void updateParticipant(Participant participant) throws SQLException {
        String query = "UPDATE participant SET first_name = ?, second_name = ?, cin = ?, role = ?, id_event = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, participant.getFirstName());
            stmt.setString(2, participant.getSecondName());
            stmt.setString(3, participant.getCin());
            stmt.setString(4, participant.getRole());
            stmt.setInt(5, participant.getIdEvent());
            stmt.setInt(6, participant.getId());

            stmt.executeUpdate();
        }
    }

    public void deleteParticipant(int id) throws SQLException {
        String query = "DELETE FROM participant WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void importParticipantsFromCSV(String filePath, int eventId) throws SQLException, IOException {
        String query = "INSERT INTO participant (first_name, second_name, cin, role, id_event) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    stmt.setString(1, values[0].trim()); // First Name
                    stmt.setString(2, values[1].trim()); // Second Name
                    stmt.setString(3, values[2].trim()); // CIN
                    stmt.setString(4, values[3].trim()); // Role
                    stmt.setInt(5, eventId); // Event ID
                    stmt.addBatch();
                }
            }
            stmt.executeBatch(); // Execute batch insert
        }
    }
}

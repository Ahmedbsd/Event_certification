package com.example.dao;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.User;

public class UserDAO {

    // Signup (Register User)
    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (fullname, email, password, cin, type) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword()); // Store hashed password
            stmt.setString(4, user.getCin());
            stmt.setString(5, user.getType());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login (Authenticate User)
    public User loginUser(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, email);
            stmt.setString(2, password); // Compare plaintext (Use hashing in production)

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id_user"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("cin"),
                        rs.getString("type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found
    }
}

package lk.gearrentpro.coursework.dao;

import lk.gearrentpro.coursework.entity.User;
import lk.gearrentpro.coursework.util.DBConnection;
import java.sql.*;

public class UserDAO {
    public User validateUser(String username, String password) throws Exception {
        String sql = "SELECT * FROM Users WHERE username = ? AND password_hash = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password); // Note: In production, use BCrypt for hashing
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("role"),
                    (Integer) rs.getObject("assigned_branch_id")
                );
            }
        }
        return null;
    }
}
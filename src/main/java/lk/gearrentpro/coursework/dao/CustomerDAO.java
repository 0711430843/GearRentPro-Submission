package lk.gearrentpro.coursework.dao;

import lk.gearrentpro.coursework.util.DBConnection;
import java.sql.*;

public class CustomerDAO {

    /**
     * Calculates the sum of all active security deposits for a specific customer.
     * This is required to enforce the LKR 500,000 limit rule.
     * @param customerId
     * @return 
     * @throws java.sql.SQLException
     */
    public double getTotalActiveDeposits(int customerId) throws SQLException {
        String sql = "SELECT SUM(security_deposit) FROM Rentals WHERE customer_id = ? AND rental_status IN ('Active', 'Overdue')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0.0;
    }
}
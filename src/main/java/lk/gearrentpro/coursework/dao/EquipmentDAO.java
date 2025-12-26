package lk.gearrentpro.coursework.dao;

import lk.gearrentpro.coursework.entity.Equipment;
import lk.gearrentpro.coursework.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {

    // Method to save new equipment into the database
    public boolean saveEquipment(Equipment e, int branchId, int categoryId) throws SQLException {
        String sql = "INSERT INTO Equipment (category_id, brand, model, base_daily_price, " +
                     "security_deposit, current_status, branch_id) VALUES (?, ?, ?, ?, ?, 'Available', ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, categoryId);
            stmt.setString(2, e.getBrand());
            stmt.setString(3, e.getModel());
            stmt.setDouble(4, e.getBaseDailyPrice());
            stmt.setDouble(5, e.getSecurityDeposit());
            stmt.setInt(6, branchId);
            
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Equipment> getEquipmentByBranch(int branchId) throws Exception {
        List<Equipment> list = new ArrayList<>();
        String sql = "SELECT * FROM Equipment WHERE branch_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, branchId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Equipment e = new Equipment();
                e.setEquipmentId(rs.getInt("equipment_id"));
                e.setBrand(rs.getString("brand"));
                e.setModel(rs.getString("model"));
                e.setBaseDailyPrice(rs.getDouble("base_daily_price"));
                e.setSecurityDeposit(rs.getDouble("security_deposit"));
                e.setStatus(rs.getString("current_status"));
                list.add(e);
            }
        }
        return list;
    }
}
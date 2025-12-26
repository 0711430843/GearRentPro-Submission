package lk.gearrentpro.coursework.dao;

import lk.gearrentpro.coursework.util.DBConnection;
import java.sql.*;

public class RentalDAO {

    /**
     * Issues a new rental and updates equipment status within a single transaction.
     * Uses a transaction to ensure both the rental record and equipment status are updated together.
     * @param equipmentId
     */
    public boolean issueRental(int equipmentId, int customerId, int branchId, 
                              Date start, Date end, double amount, double deposit, 
                              double memDiscount, double longDiscount, double finalTotal) {
        
        String insertRentalSql = "INSERT INTO Rentals (equipment_id, customer_id, branch_id, start_date, " +
                                "end_date, calculated_rental_amount, security_deposit, " +
                                "membership_discount_amount, long_rental_discount_amount, " +
                                "final_payable_amount, rental_status) VALUES (?,?,?,?,?,?,?,?,?,?,'Active')";
        
        String updateEquipSql = "UPDATE Equipment SET current_status = 'Rented' WHERE equipment_id = ?";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start Transaction

            // 1. Insert Rental Record
            try (PreparedStatement rentalStmt = conn.prepareStatement(insertRentalSql)) {
                rentalStmt.setInt(1, equipmentId);
                rentalStmt.setInt(2, customerId);
                rentalStmt.setInt(3, branchId);
                rentalStmt.setDate(4, start);
                rentalStmt.setDate(5, end);
                rentalStmt.setDouble(6, amount);
                rentalStmt.setDouble(7, deposit);
                rentalStmt.setDouble(8, memDiscount);
                rentalStmt.setDouble(9, longDiscount);
                rentalStmt.setDouble(10, finalTotal);
                rentalStmt.executeUpdate();
            }

            // 2. Update Equipment Status to 'Rented'
            try (PreparedStatement equipStmt = conn.prepareStatement(updateEquipSql)) {
                equipStmt.setInt(1, equipmentId);
                equipStmt.executeUpdate();
            }

            conn.commit(); // Save all changes
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    /**
     * Processes a rental return and settles charges. 
     * Requirement: Use Transactions for returning equipment and updating its status.
     */
    public boolean processReturn(int rental_id, int equipment_id, Date actual_return_date, 
                                double late_fee, double damage_charge, String damage_desc, String newStatus) {
        
        String updateRentalSql = "UPDATE Rentals SET actual_return_date = ?, late_fee_charged = ?, " +
                                "damage_charge = ?, damage_description = ?, rental_status = 'Returned' " +
                                "WHERE rental_id = ?";
        
        String updateEquipSql = "UPDATE Equipment SET current_status = ? WHERE equipment_id = ?";

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start Transaction

            // 1. Update Rental Settlement details
            try (PreparedStatement rentalStmt = conn.prepareStatement(updateRentalSql)) {
                rentalStmt.setDate(1, actual_return_date);
                rentalStmt.setDouble(2, late_fee);
                rentalStmt.setDouble(3, damage_charge);
                rentalStmt.setString(4, damage_desc);
                rentalStmt.setInt(5, rental_id);
                rentalStmt.executeUpdate();
            }

            // 2. Update Equipment status (Available or Under Maintenance)
            try (PreparedStatement equipStmt = conn.prepareStatement(updateEquipSql)) {
                equipStmt.setString(1, newStatus);
                equipStmt.setInt(2, equipment_id);
                equipStmt.executeUpdate();
            }

            conn.commit(); 
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}
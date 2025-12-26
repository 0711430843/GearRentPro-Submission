package lk.gearrentpro.coursework.controller;

import lk.gearrentpro.coursework.service.RentalService;
import lk.gearrentpro.coursework.dao.RentalDAO;
import java.time.LocalDate;
import java.sql.Date;

public class RentalController {
    private final RentalService rentalService = new RentalService();
    private final RentalDAO rentalDAO = new RentalDAO();

    public String handleIssueRental(int equipmentId, int customerId, int branchId, 
                                   LocalDate start, LocalDate end, double basePrice, double factor) {
        try {
            // 1. Validate the 30-day duration rule in the Service Layer
            rentalService.validateDuration(start, end);

            // 2. Calculate the total amount using business rules
            double amount = rentalService.calculateRentalAmount(basePrice, factor, 1.2, start, end);

            // 3. Save to database using a Transaction in the DAO
            boolean success = rentalDAO.issueRental(equipmentId, customerId, branchId, 
                                                    Date.valueOf(start), Date.valueOf(end), 
                                                    amount, 5000.0, 0, 0, amount);
            
            return success ? "Success: Rental created." : "Error: Database transaction failed.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
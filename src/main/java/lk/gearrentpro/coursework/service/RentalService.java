package lk.gearrentpro.coursework.service;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;

public class RentalService {

    /**
     * Calculates the final rental price based on business rules.
     * Formula: finalDailyPrice = equipmentBasePrice * categoryFactor * weekendMultiplier
     * @param basePrice
     * @param categoryFactor
     * @param weekendMultiplier
     * @param start
     * @param end
     * @return 
     * @throws java.lang.Exception
     */
    public double calculateRentalAmount(double basePrice, double categoryFactor, double weekendMultiplier, LocalDate start, LocalDate end) throws Exception {
        validateDuration(start, end);
        
        double totalAmount = 0;
        // Iterate through each day of the rental period
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            double multiplier = 1.0;
            
            // Apply weekend multiplier if the day is Saturday or Sunday
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                multiplier = weekendMultiplier;
            }
            
            totalAmount += (basePrice * categoryFactor * multiplier);
        }
        
        return totalAmount;
    }

    /**
     * Applies a long-rental discount if the duration is 7 days or more.
     * Rule: Apply discount on total rental amount excluding deposit.
     * @param totalAmount
     * @param discountPercentage
     * @param duration
     * @return 
     */
    public double applyLongRentalDiscount(double totalAmount, double discountPercentage, long duration) {
        if (duration >= 7) {
            return totalAmount * (discountPercentage / 100);
        }
        return 0;
    }

    /**
     * Validates that the rental duration does not exceed 30 days.
     * @param start
     * @param end
     * @throws java.lang.Exception
     */
    public void validateDuration(LocalDate start, LocalDate end) throws Exception {
        long days = ChronoUnit.DAYS.between(start, end);
        if (days > 30) {
            throw new Exception("Rental duration cannot exceed 30 days.");
        }
        if (days <= 0) {
            throw new Exception("Invalid date range: End date must be after start date.");
        }
    }

    /**
     * Validates that the customer's total active security deposits do not exceed LKR 500,000.
     * @param currentTotalDeposits
     * @param newDeposit
     * @throws java.lang.Exception
     */
    public void validateDepositLimit(double currentTotalDeposits, double newDeposit) throws Exception {
        double limit = 500000.00; 
        if ((currentTotalDeposits + newDeposit) > limit) {
            throw new Exception("Security deposit limit exceeded for this customer.");
        }
    }
}
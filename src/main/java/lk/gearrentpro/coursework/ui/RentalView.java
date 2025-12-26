package lk.gearrentpro.coursework.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lk.gearrentpro.coursework.service.RentalService;
import lk.gearrentpro.coursework.dao.RentalDAO;
import java.time.LocalDate;
import java.sql.Date;

public class RentalView {
    private final RentalService rentalService = new RentalService();
    private final RentalDAO rentalDAO = new RentalDAO();

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("New Rental Transaction");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(12);
        grid.setHgap(10);

        // UI Components
        TextField txtCustId = new TextField();
        TextField txtEquipId = new TextField();
        DatePicker startPicker = new DatePicker(LocalDate.now());
        DatePicker endPicker = new DatePicker(LocalDate.now().plusDays(3));
        Label lblTotal = new Label("Total: LKR 0.00");
        Button btnCalculate = new Button("Calculate Cost");
        Button btnConfirm = new Button("Confirm Rental");

        // Layout Organization
        grid.add(new Label("Customer ID:"), 0, 0);
        grid.add(txtCustId, 1, 0);
        grid.add(new Label("Equipment ID:"), 0, 1);
        grid.add(txtEquipId, 1, 1);
        grid.add(new Label("Start Date:"), 0, 2);
        grid.add(startPicker, 1, 2);
        grid.add(new Label("End Date:"), 0, 3);
        grid.add(endPicker, 1, 3);
        grid.add(btnCalculate, 0, 4);
        grid.add(lblTotal, 1, 4);
        grid.add(btnConfirm, 1, 5);

        // Business Logic Integration
        btnCalculate.setOnAction(e -> {
            try {
                // Mock values for demo - these should eventually come from the DB
                double basePrice = 5000.0; 
                double factor = 1.0;
                double multiplier = 1.2; // weekend multiplier

                double total = rentalService.calculateRentalAmount(
                    basePrice, factor, multiplier, startPicker.getValue(), endPicker.getValue()
                );
                lblTotal.setText("Total: LKR " + String.format("%.22f", total));
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        // Transaction Execution
        btnConfirm.setOnAction(e -> {
            boolean success = rentalDAO.issueRental(
                Integer.parseInt(txtEquipId.getText()),
                Integer.parseInt(txtCustId.getText()),
                1, // Branch ID
                Date.valueOf(startPicker.getValue()),
                Date.valueOf(endPicker.getValue()),
                5000.0, 25000.0, 0, 0, 5000.0 // Simplified values
            );
            if(success) {
                new Alert(Alert.AlertType.INFORMATION, "Rental Issued!").show();
                stage.close();
            }
        });

        stage.setScene(new Scene(grid, 400, 350));
        stage.show();
    }
}
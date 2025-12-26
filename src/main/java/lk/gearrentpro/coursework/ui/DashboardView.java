package lk.gearrentpro.coursework.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.gearrentpro.coursework.entity.User;

public class DashboardView {
    private User loggedInUser;

    public DashboardView(User user) {
        this.loggedInUser = user;
    }

    public void start(Stage stage) {
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(20));
        
        Label lblWelcome = new Label("Welcome, " + loggedInUser.getUsername() + " (" + loggedInUser.getRole() + ")");
        lblWelcome.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Button btnRent = new Button("Issue Rental");
        Button btnReturn = new Button("Process Return");
        Button btnManageInventory = new Button("Manage Inventory");
        Button btnAdminPanel = new Button("System Settings (Admin)");

        // Set buttons to full width for better UI
        btnRent.setMaxWidth(Double.MAX_VALUE);
        btnReturn.setMaxWidth(Double.MAX_VALUE);
        btnManageInventory.setMaxWidth(Double.MAX_VALUE);
        btnAdminPanel.setMaxWidth(Double.MAX_VALUE);

        // ROLE-BASED ACCESS CONTROL
        btnAdminPanel.setDisable(!loggedInUser.getRole().equalsIgnoreCase("Admin"));
        btnManageInventory.setDisable(loggedInUser.getRole().equalsIgnoreCase("Staff"));

        // BUTTON ACTIONS
        btnManageInventory.setOnAction(e -> {
            new EquipmentView().show();
        });

        btnRent.setOnAction(e -> {
            // This will open the RentalIssueView once you create it
            new RentalView().show();
        });

        btnReturn.setOnAction(e -> {
            // Future step: new ReturnView().show();
            System.out.println("Opening Return Screen...");
        });

        menu.getChildren().addAll(lblWelcome, btnRent, btnReturn, btnManageInventory, btnAdminPanel);

        stage.setScene(new Scene(menu, 350, 300));
        stage.setTitle("GearRent Pro - Dashboard");
        stage.show();
    }
}
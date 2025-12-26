package lk.gearrentpro.coursework.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lk.gearrentpro.coursework.entity.Equipment;
import lk.gearrentpro.coursework.dao.EquipmentDAO;
import java.sql.SQLException;

public class EquipmentView {

    private final EquipmentDAO equipmentDAO = new EquipmentDAO();

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Manage Inventory - Register Equipment");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Form Fields
        TextField txtBrand = new TextField();
        TextField txtModel = new TextField();
        TextField txtYear = new TextField();
        ComboBox<String> cmbCategory = new ComboBox<>();
        cmbCategory.getItems().addAll("Camera", "Lens", "Tripod", "Lighting", "Audio");
        
        TextField txtBasePrice = new TextField();
        TextField txtDeposit = new TextField();
        Button btnSave = new Button("Add to Inventory");

        // Layout Organization
        grid.add(new Label("Category:"), 0, 0);
        grid.add(cmbCategory, 1, 0);
        grid.add(new Label("Brand:"), 0, 1);
        grid.add(txtBrand, 1, 1);
        grid.add(new Label("Model:"), 0, 2);
        grid.add(txtModel, 1, 2);
        grid.add(new Label("Purchase Year:"), 0, 3);
        grid.add(txtYear, 1, 3);
        grid.add(new Label("Daily Base Price:"), 0, 4);
        grid.add(txtBasePrice, 1, 4);
        grid.add(new Label("Security Deposit:"), 0, 5);
        grid.add(txtDeposit, 1, 5);
        grid.add(btnSave, 1, 6);

        // Save Logic
        btnSave.setOnAction(e -> {
            try {
                Equipment eq = new Equipment();
                eq.setBrand(txtBrand.getText());
                eq.setModel(txtModel.getText());
                eq.setBaseDailyPrice(Double.parseDouble(txtBasePrice.getText()));
                eq.setSecurityDeposit(Double.parseDouble(txtDeposit.getText()));
                
                // Note: You will need a method in EquipmentDAO to handle the INSERT
                boolean success = saveEquipment(eq, cmbCategory.getValue());
                
                if (success) {
                    new Alert(Alert.AlertType.INFORMATION, "Equipment Added Successfully!").show();
                    stage.close();
                }
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Invalid Input: " + ex.getMessage()).show();
            }
        });

        stage.setScene(new Scene(grid, 450, 400));
        stage.show();
    }

    // Temporary helper method to bridge to DAO
    private boolean saveEquipment(Equipment eq, String category) {
        // This should ideally call your Controller
        System.out.println("Saving " + eq.getBrand() + " " + eq.getModel());
        return true; 
    }
}
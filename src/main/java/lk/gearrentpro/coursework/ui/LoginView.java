package lk.gearrentpro.coursework.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.gearrentpro.coursework.service.UserService;
import lk.gearrentpro.coursework.entity.User;

public class LoginView extends Application {
    private UserService userService = new UserService();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GearRent Pro - Login");

        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Username");
        
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Password");

        Button btnLogin = new Button("Login");
        
        btnLogin.setOnAction(e -> {
            try {
                User user = userService.login(txtUsername.getText(), txtPassword.getText());
                if (user != null) {
                    // Navigate to Dashboard and pass the user object for role-based access
                    new DashboardView(user).start(new Stage());
                    primaryStage.close();
                }
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
            }
        });

        VBox layout = new VBox(10, new Label("Login to GearRent Pro"), txtUsername, txtPassword, btnLogin);
        layout.setPadding(new Insets(20));
        
        primaryStage.setScene(new Scene(layout, 300, 200));
        primaryStage.show();
    }
}
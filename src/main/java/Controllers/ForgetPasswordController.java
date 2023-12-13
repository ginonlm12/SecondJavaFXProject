package Controllers;

import Model.User;
import Services.UserService;
import applicationMain.Main;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.sql.*;

public class ForgetPasswordController {

    public User user;
    @FXML
    private PasswordField CPCFpassword;
    @FXML
    private TextField CPemail;
    @FXML
    private TextField CPname;
    @FXML
    private PasswordField CPpassword;
    @FXML
    private TextField CPusername;
    @FXML
    private Button ChangePassword;
    @FXML
    private Button toLogIn;
    @FXML
    private Button toSignUp;
    @FXML
    private Label wrongChange;

    @FXML
    public void AfterChangePassword(ActionEvent event) throws SQLException, ClassNotFoundException {
        changeUser();
    }
    private void changeUser() throws SQLException, ClassNotFoundException {
        String name = CPname.getText();
        String username = CPusername.getText();
        String email = CPemail.getText();
        String password = CPpassword.getText();
        String CFpassword = CPCFpassword.getText();

        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || CFpassword.isEmpty()) {
            wrongChange.setText("Please fill out all required information.");
            //wrongChange.setStyle("-fx-font-size: 13; -fx-font-weight: bold; -fx-font-style: italic");
            return;
        }

        if (!password.equals(CFpassword)) {
            wrongChange.setText("Please re-enter your password to confirm.");
            return;
        }

        user = UserService.changeUsertoDatabase(new User(username, password, name, email));
        if (user == null) {
            wrongChange.setText("Please check your information and try again.");
        } else {
            wrongChange.setText("Successful!");

            PauseTransition pause = new PauseTransition(Duration.seconds(0.1)); // 1 seconds
            pause.setOnFinished(event -> {
                Main m = new Main();
                try {
                    m.changeScene("duringLogin.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            pause.play();
        }
    }

    @FXML
    public void ClicktoLogIn(ActionEvent event) throws Exception {
        Main m = new Main();
        m.changeScene("duringLogin.fxml");
    }

    @FXML
    public void ClicktoSingUp(ActionEvent event) throws Exception {
        Main m = new Main();
        m.changeScene("duringRegistration.fxml");
    }
}
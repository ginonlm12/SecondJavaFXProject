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

public class LogInController {

    public LogInController() {
    }

    @FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin;

    @FXML
    private Button button1;

    @FXML
    private Button button11;

    @FXML
    private Button forgetPassword;

    public User loggedInUser;

    public User user;

    @FXML
    public void userLogin(ActionEvent event) throws Exception {
        String UN = username.getText();
        String PW = password.getText();

        if (UN.isEmpty() || PW.isEmpty()) {
            wrongLogin.setText("Username or password cannot be left blank");
            return;
        }
        User Ha = new User("", "", UN, PW, "", "", "", "", 0.0,"");
        user = UserService.getAuthenticatedUser(Ha);

        if(user != null){
            checkLogin();
        }
        else{
            wrongLogin.setText("Wrong password or username!");

            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(eventt -> {
                try {
                    wrongLogin.setText("Enter your password and username");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            pause.play();
        }
    }

    private void checkLogin() throws Exception {
        Main m = new Main();

        if (username.getText().equals(user.username) && password.getText().equals(user.password)) {
            wrongLogin.setText("Successful");

            PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
            pause.setOnFinished(event -> {
                try {
                    m.setUser(user);
                    m.changeScene("information.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            pause.play();
        } else {
            wrongLogin.setText("Wrong password or username!");
        }
    }

    @FXML
    public void userSignUp(ActionEvent actionEvent) throws Exception {
        Main m = new Main();
        m.changeScene("duringRegistration.fxml");
    }

    @FXML
    public void userForgetPassword(ActionEvent event) throws Exception {
        Main m = new Main();
        m.changeScene("duringForgetPassword.fxml");
    }
}

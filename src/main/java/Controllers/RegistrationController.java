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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController {

    public User user;
    @FXML
    private Button AHAAbutton;
    @FXML
    private Button SignUpButton;
    @FXML
    private PasswordField RCFpassword;
    @FXML
    private TextField Remail;
    @FXML
    private TextField Rname;
    @FXML
    private PasswordField Rpassword;
    @FXML
    private TextField Rusername;
    @FXML
    private Label wrongSignup;

    @FXML
    private Label wrongUsername;

    @FXML
    private Label wrongCFpassword;

    @FXML
    private Label wrongPassword;
    @FXML
    public void backtoLogIn() throws Exception {
        Main m = new Main();
        m.changeScene("duringLogin.fxml");
    }

    public void Register() throws SQLException, ClassNotFoundException {
        registerUser();
    }

    public static boolean isValidUsername(String username) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d).+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private void registerUser() throws SQLException, ClassNotFoundException {
        String name = Rname.getText();
        String username = Rusername.getText();
        String email = Remail.getText();
        String password = Rpassword.getText();
        String CFpassword = RCFpassword.getText();

        if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || CFpassword.isEmpty()) {
            wrongSignup.setText("Please fill out all required information.");
            return;
        }

        if (!isValidUsername(username)) {
            wrongSignup.setText("Invalid username");
            wrongUsername.setVisible(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(eventt -> {
                try {
                    wrongUsername.setVisible(false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            pause.play();
            return;
        }

        if (!isValidPassword(password)) {
            wrongSignup.setText("Invalid password");
            wrongPassword.setVisible(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(eventt -> {
                try {
                    wrongPassword.setVisible(false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            pause.play();
            return;
        }

        if (!password.equals(CFpassword)) {
            wrongSignup.setText("Please re-enter your password to confirm.");
            wrongCFpassword.setVisible(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(eventt -> {
                try {
                    wrongCFpassword.setVisible(false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            return;
        }

        user = UserService.addUsertoDatabase(new User(username, password, name, email));

        if (user == null) {
            wrongSignup.setText("A new username is required.");
            wrongUsername.setText("Entered username has been used");
            wrongUsername.setVisible(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(eventt -> {
                try {
                    wrongUsername.setVisible(false);
                    wrongUsername.setText("At least 5 characters, one uppercase and one lowercase letter.");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            pause.play();
        } else {
            wrongSignup.setText("Successful!");

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
    public void backtoChangePassword(ActionEvent event) throws Exception {
        Main m = new Main();
        m.changeScene("duringForgetPassword.fxml");
    }
}

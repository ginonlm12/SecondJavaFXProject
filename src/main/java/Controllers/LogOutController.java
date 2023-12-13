package Controllers;

import Model.User;
import Services.UserService;
import applicationMain.Main;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LogOutController implements Initializable {
    Main m = new Main();
    ObservableList<String> SUAusername = FXCollections.observableArrayList();
    @FXML
    private Button logout;

    @FXML
    private Button FMI;

    @FXML
    private Button FYI;
    @FXML
    private final User LogInUser = m.getUser();
    @FXML
    private Label LOuserwel;
    @FXML
    private ComboBox<String> SUAchoice;
    public void userLogout(ActionEvent event) throws Exception{
        m.setUser(null);
        m.changeScene("duringLogin.fxml");
    }
    @FXML
    private Text SUAtext;
    @FXML
    private Button SUAsetupbutton;

    public LogOutController() throws Exception {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LOuserwel.setVisible(true);
            if (LogInUser.name == null)
                LOuserwel.setText("Welcome " + LogInUser.username + " to HoangLam's second JavaFX Project!");
            if (LogInUser.name != null)
                LOuserwel.setText("Welcome " + LogInUser.name + " to HoangLam's second JavaFX Project!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (LogInUser.role.equals("Admin")) {
            SUAtext.setVisible(true);
            SUAchoice.setVisible(true);
            SUAsetupbutton.setVisible(true);

            try {
                SUAchoice.setItems(UserService.ShowUsername());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void formore(ActionEvent actionEvent) throws Exception {
        if (LogInUser.getCpa() == 0) FMI.setText("Please complete the form");
        else {
            System.out.println(LogInUser.getCpa());
            m.changeScene("CPA.fxml");
        }
    }

    public void forinformation(Event event) throws Exception {
        if (LogInUser == null) {
            FYI.setText("Mistaken");
        } else {
            FYI.setText("Wait a while!");
            PauseTransition pause = new PauseTransition(Duration.seconds(0.1)); // 1 seconds
            pause.setOnFinished(eventt -> {
                try {
                    m.setUser(LogInUser);
                    m.changeScene("information.fxml");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            pause.play();
        }
    }

    @FXML
    void SUAsetup(ActionEvent event) throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setUsername(SUAchoice.getValue());

        if (UserService.setAdmin(user)){
            SUAtext.setText("Set " + user.getUsername() + " as an admin successfully!");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(eventt -> {
            try {
                SUAtext.setText("Set up admin privileges");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            });
            pause.play();
        }
        else{
            SUAtext.setText("Chosen user " + user.getUsername() + " is already an admin!");
        }
    }
}

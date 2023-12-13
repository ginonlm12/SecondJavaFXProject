package Controllers;

import Model.User;
import Services.UserService;
import applicationMain.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InformationController implements Initializable {
    Main m = new Main();
    @FXML
    private Label wrongSave;
    @FXML
    private Button inforBTLI;
    @FXML
    private Button inforBTLO;
    @FXML
    private TextField inforEmail;
    @FXML
    private TextField inforName;
    @FXML
    private TextField inforUsername;
    @FXML
    private TextField inforProvince;
    @FXML
    private Button inforSavebutton;
    @FXML
    private TextField inforCPA;
    @FXML
    private Label inforNote;
    ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female", "Unknown");
    private User user;
    @FXML
    private DatePicker inforDOB;

    public boolean isConducted() {
        return conducted;
    }

    public void setConducted(boolean conducted) {
        this.conducted = conducted;
    }

    private boolean conducted = false;
    @FXML
    private ChoiceBox<String> inforGender = new ChoiceBox();
    ObservableList<String> universityList = FXCollections.observableArrayList();
    @FXML
    private Text inforDebug;
    @FXML
    private ChoiceBox<String> inforUniversity;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showInfor();
            try (Scanner scanner = new Scanner(new File("src/main/resources/HN_University/HN_University.txt"))) {
                while (scanner.hasNextLine()) {
                    universityList.add(scanner.nextLine());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            inforGender.setItems(genderList);
            inforUniversity.setItems(universityList);

            if (user.role.equals("Admin")) {
                inforDebug.setText("Admin: " + user.name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void inforBackToLogIn(ActionEvent event) throws Exception {
        m.setUser(null);
        m.changeScene("duringLogin.fxml");
    }

    public void showInfor() throws Exception {
        user = m.getUser();
        user = UserService.showInfor(user);
        if (user.cpa != 0 && !user.province.equals("Unknown") &&
                !user.gender.equals("N/A") && !user.dob.equals("1900-01-01") &&
                !user.university.equals("Unknown")) {
            conducted = true;
            inforSavebutton.setText("Home");
        }

        inforEmail.setText(user.email);
        inforUsername.setText(user.username);
        inforName.setText(user.name);
        if (user.cpa != 0) inforCPA.setText(String.valueOf(user.cpa));
        if (!user.province.equals("Unknown")) inforProvince.setText(user.province);
        if (!user.gender.equals("N/A")) inforGender.setValue(user.gender);
        if (!user.dob.equals("1900-01-01")) {
            LocalDate dob = LocalDate.parse(user.dob);
            inforDOB.setValue(dob);
        }
        if (!user.university.equals("Unknown")) inforUniversity.setValue(user.university);

        m.setUser(user);
    }

    @FXML
    void inforBackToLogOut(ActionEvent event) throws Exception {
        m.changeScene("afterLogin.fxml");
    }

    @FXML
    private void inforSave(ActionEvent event) throws Exception {
        user = m.getUser();

        if (inforSavebutton.getText().equals("Home")) {
            inforBackToLogOut(event);
        }
        if (!conducted) {
            String username = user.username;
            String cpa = inforCPA.getText();
            String province = inforProvince.getText();
            String gender = inforGender.getValue();
            String dob = String.valueOf(inforDOB.getValue());
            String university = inforUniversity.getValue();

            if (cpa.isEmpty() || province.isEmpty() ||
                    gender == null || dob.isEmpty() ||
                    university == null) {
                inforSavebutton.setText("Try again");
                return;
            }

            user.setCpa(Double.parseDouble(inforCPA.getText()));
            user.setProvince(inforProvince.getText());
            user.setGender(inforGender.getValue());
            user.setDob(String.valueOf(inforDOB.getValue()));
            user.setUniversity(inforUniversity.getValue());

            user = UserService.inforSave(user);
            if(user != null){
                if (wrongSave.getText().equals("Other information")) {
                    wrongSave.setText("Please verify that all the details you have provided are correct.");
                    inforSavebutton.setText("Verify");
                } else if (wrongSave.getText().equals("Please verify that all the details you have provided are correct.")) {
                    inforSavebutton.setText("Home");
                    wrongSave.setText("Save the information successfully!");
                    inforNote.setText("Once the form has been completed, you will not be able to make any changes to the information you have provided.");
                }
            }
            else{
                user.setCpa(0);
                user.setProvince("Unknown");
                user.setGender("Unknown");
                user.setDob("1/1/1990");
                user.setUniversity("Unknown");
            }
        }
    }
}

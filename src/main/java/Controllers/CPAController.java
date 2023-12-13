package Controllers;

import Model.User;
import Services.UserService;
import applicationMain.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CPAController implements Initializable {
    Main m = new Main();
    ObservableList<String> universityList = FXCollections.observableArrayList();
    private User Cuser;
    private final User LogInUser = m.getUser();
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ObservableList<User> data;
    @FXML
    private TableView<User> CPAtable;
    @FXML
    private TableColumn<User, String> CPAname;
    @FXML
    private TableColumn<User, Double> CPAshow;
    @FXML
    private TableColumn<User, String> CPADOB;
    @FXML
    private TableColumn<User, String> CPAprovince;
    @FXML
    private Label CPAuniversity;
    @FXML
    private ChoiceBox<String> CPAuniversityselect;
    @FXML
    private Text CPAtext;

    public CPAController() throws Exception {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        CPAtable.setVisible(false);
        data = FXCollections.observableArrayList();
        setCellTable();
        try {
            if (LogInUser.role.equals("N/A")) {
                CPAtext.setVisible(true);
                CPAtext.setText(LogInUser.university);
                CPAtable.setVisible(true);
                CPAuniversity.setVisible(false);
                CPAuniversityselect.setVisible(false);
                if (!LogInUser.university.equals("Unknown") || LogInUser.cpa != 0.0) {
                    CPAtable.setVisible(true);
                    User Ha = new User("", "", "", "", "", "", "", "Unknown", 0.0, LogInUser.getUniversity());
                    CPAtable.setItems(UserService.loadDatafromDatabase(Ha));
                }
            }
            if (LogInUser.role.equals("Admin")) {
                CPAtext.setVisible(false);
                CPAuniversity.setVisible(true);
                CPAuniversityselect.setVisible(true);
                universityList.add("All universities in HaNoi");
                try (Scanner scanner = new Scanner(new File("src/main/resources/HN_University/HN_University.txt"))) {
                    while (scanner.hasNextLine()) {
                        universityList.add(scanner.nextLine());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CPAuniversityselect.setItems(universityList);

                CPAuniversityselect.setOnAction(event -> {
                    try {
                        User Ha = new User("", "", "", "", "", "", "", "Admin", 0.0, CPAuniversityselect.getValue());
                        CPAtable.setVisible(true);
                        CPAtable.setItems(UserService.loadDatafromDatabase(Ha));
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellTable() {
        CPAname.setCellValueFactory(new PropertyValueFactory<>("name"));
        CPAshow.setCellValueFactory(new PropertyValueFactory<>("cpa"));
        CPADOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        CPAprovince.setCellValueFactory(new PropertyValueFactory<>("province"));
    }

    @FXML
    void CPAbacktoHome(ActionEvent event) throws Exception {
        m.changeScene("afterLogin.fxml");
    }

    @FXML
    void CPAsignout(ActionEvent event) throws Exception {
        m.setUser(null);
        m.changeScene("duringLogin.fxml");
    }

    @FXML
    void getSelected(MouseEvent event) {
        int index = CPAtable.getSelectionModel().getSelectedIndex();
        if (index <= -1) return;
        String name = CPAname.getCellData(index);
        System.out.println(name);

        String province = CPAprovince.getCellData(index);
        System.out.println(province);

        String dob = CPADOB.getCellData(index);
        System.out.println(dob);

        double cpa = Double.parseDouble(CPAshow.getCellData(index).toString());
        System.out.println(cpa);

        Cuser = new User(name, cpa, dob, province);
    }

    public User getCUser() throws Exception {
        return Cuser;
    }

    public void setCUser(User US) throws Exception {
        Cuser = US;
    }
}

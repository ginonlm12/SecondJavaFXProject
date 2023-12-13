package applicationMain;

import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stg;

    private static User LoginUser;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("duringLogin.fxml"));
        primaryStage.setTitle("Hoàng Lâm");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void changeScene(String fxml) throws Exception{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public User getUser() throws Exception {
        return LoginUser;
    }

    public void setUser(User US) throws Exception {
        LoginUser = US;
    }

}

//    var urll = "jdbc:mysql://localhost:3306/mydatabase";
//    var US = "root";
//    var PW = "";
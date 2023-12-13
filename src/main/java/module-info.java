module com.example.secondjavafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires jdk.jconsole;

    opens applicationMain to javafx.fxml;
    exports applicationMain;
    exports Test;
    opens Test to javafx.fxml;
    exports Model;
    opens Model to javafx.fxml;
    exports Controllers;
    opens Controllers to javafx.fxml;
}
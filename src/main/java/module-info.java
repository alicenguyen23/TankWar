module edu.tcu.cs.tankwar {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.tcu.cs.tankwar to javafx.fxml;
    exports edu.tcu.cs.tankwar;
}
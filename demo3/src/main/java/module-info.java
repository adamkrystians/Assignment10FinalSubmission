module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens Main to javafx.fxml;
    exports Main;
}
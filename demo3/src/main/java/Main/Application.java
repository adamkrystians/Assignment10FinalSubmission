package Main;

import Services.ViewSwitcher;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {

        // • Pre-load all FXML files.
        ViewSwitcher.loadView("login", "/Main/LogIn.fxml");
        ViewSwitcher.loadView("home", "/Main/Home.fxml");
        ViewSwitcher.loadView("list", "/Main/GoalList.fxml");
        ViewSwitcher.loadView("editor", "/Main/GoalEditor.fxml");

        // • Load the LogIn UI.
        ViewSwitcher.switchTo("login");

        // • Create our Scene
        Scene scene = new Scene(ViewSwitcher.getRoot(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("A&D Habit Tracker");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
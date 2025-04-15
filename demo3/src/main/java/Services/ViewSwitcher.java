// ■  Package
package Services;

// ■  Classes
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.util.HashMap;

public class ViewSwitcher {

    //════[VARIABLES]════//
    private static final StackPane root = new StackPane(); // • Container which will contain all of our loaded FXML files.
    private static final HashMap<String, Parent> viewFiles = new HashMap<>();

    //════[METHODS]════//
    // • Initialization method which pre-loads a given FXML file.
    public static void loadView(String name, String fxmlPath) {
        try {

            // • Retrieve and load the FXML file
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(fxmlPath));
            Parent view = loader.load();
            System.out.println("Controller: " + loader.getController());

            // • Add the loaded FXML file into the viewFiles list.
            viewFiles.put(name, view);

            // • Add the loaded FXML file into the rootContainer (StackPane) so that we can make it visible.
            root.getChildren().add(view);

            // • Save the FXML loader to ControllerService, so we can easily retrieve it's controller.
            ControllerService.saveLoader(name, loader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // • Switches to the given view. Eg: switchTo("home");
    public static void switchTo(String name) {
        for (Parent view : viewFiles.values()) {
            view.setVisible(false);
        }
        Parent selected = viewFiles.get(name);
        if (selected != null) {
            selected.setVisible(true);
            selected.toFront();
        }
    }

    // • Returns the root element. (Stackpane which contains all loaded FXML files.)
    public static StackPane getRoot() {
        return root;
    }
}
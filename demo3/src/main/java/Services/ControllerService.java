package Services;

import Main.HomeController;
import javafx.fxml.FXMLLoader;
import java.util.HashMap;

public class ControllerService {

    //════[VARIABLES]════//
    private static final HashMap<String, FXMLLoader> fxmlLoaders = new HashMap<>(); // • Hashmap containing all FXML loaders for easy access to controllers.

    //════[METHODS]════//
    // • Saves each loader into fxmlLoaders, so their controller can later be retrieved.
    public static void saveLoader(String name, FXMLLoader fxmlLoader){
        fxmlLoaders.put(name, fxmlLoader);
    }

    public static <T> T getController(String name) {

        // • Retrieve the correct FXML loader
        FXMLLoader loader = fxmlLoaders.get(name);

        if (loader == null) {
            System.err.println("⚠ No loader found for: " + name);
            return null;
        }else{
            return (T) loader.getController();
        }
    }
}

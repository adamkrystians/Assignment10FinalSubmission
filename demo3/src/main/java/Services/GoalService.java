
/** ➤ GoalService
 Description:
 */

// ■  Package
package Services;

// ■  Imported Classes
import Classes.Goal;
import Main.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class GoalService {

    //════[VARIABLES]════//
    private static List<Goal> goalList = new ArrayList<>();

    //════[METHODS]════//
    public static List<Goal> getGoalList(){
      return goalList;
    };

    // Returns all the goals in an ObservableList. Ready to be displayed anywhere.
    public static ObservableList<Goal> getObservableGoalList(ListView<Goal> goals){

        // • Clear all items initially so we have a clean table.
        System.out.println(goalList);
        // • Create an ObservableList object and put all goals in it. (For display)
        ObservableList<Goal> goalsToLoad = FXCollections.observableArrayList();
        for (Goal goal : goalList){
            goalsToLoad.add(goal);
            System.out.println("Added: " + goal + goalsToLoad);
        }

       return goalsToLoad;
    }

    public static Goal getGoalInfo(Integer ID){
        for (Goal goal : goalList){
            if (goal.ID == ID) {
                return goal;
            }
        }
        return null;
    }

    public static void saveGoal(String name, String description, Integer ID){

        // ENSURE VALID INPUT
        if (name.length() < 2){
            System.out.println("Error: Length must be at least two characters.");
            return;
        }

        // • Goal doesn't exist. Create new one
        if (ID == null || ID == -1){
            Goal createdGoal = new Goal(name, description);
            goalList.add(createdGoal);
            createdGoal.completed.addListener((obs, wasSelected, isNowSelected) -> {
                HomeController homeController = ControllerService.getController("home");
                homeController.loadProgressBar();
            });

        // Goal exists. Find it and update it.
        }else {
            for (Goal goal : goalList){
                if (goal.name == name){
                    goal.description = description;
                }
            }
        }

        // Load the home page again
        HomeController homeController = ControllerService.getController("home");
        homeController.loadGoalList();
        homeController.loadProgressBar();
        ViewSwitcher.switchTo("home");
    }

    public static void deleteGoal(Integer ID){
        for (Goal goal : goalList){
            if (goal.ID.equals(ID)) {
                goalList.remove(goal);
                HomeController homeController = ControllerService.getController("home");
                homeController.loadProgressBar();
            }
        }
    }
}

// ■  Package
package Main;

// ■  Import Classes
import Classes.Goal;
import Services.ControllerService;
import Services.GoalService;
import Services.ViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HomeController {

    //════[VARIABLES]════//
    // ■  FXML
    @FXML Text userWelcome;
    @FXML Text currentDate;
    @FXML Text progress;
    @FXML Button newHabit;
    @FXML Button editHabit;
    @FXML Button newDay;
    @FXML ListView<Goal> goalList;

    // ■  IMPORTANT
    public Integer completedGoals = 0;
    public LocalDate today = LocalDate.now();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d", Locale.ENGLISH);

    //════[INITIALIZE]════//
    public void init(String firstName){
        userWelcome.setText("Welcome, " + firstName);
        currentDate.setText(today.format(formatter));

        // • Define what each added cell to the goal list will look like.
        goalList.setCellFactory(lv -> new CheckBoxListCell<Goal>(goal -> goal.completed) {
            @Override
            public void updateItem(Goal goal, boolean empty) {
                super.updateItem(goal, empty);
                if (!empty && goal != null) {
                    setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
                    setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 14));
                } else {
                    setStyle("-fx-background-color: transparent;");
                }
            }
        });
    }

    //════[METHODS]════//
    public void loadGoalList(){

        // • Clear all items initially so we have a clean table.
        goalList.getItems().clear();

        // • Create an ObservableList object and put all goals in it. (For display)
        ObservableList<Goal> goalsToLoad = FXCollections.observableArrayList();
        for (Goal goal : GoalService.getGoalList()){
            goalsToLoad.add(goal);
        }

        goalList.setItems(goalsToLoad);
    }

    public void loadProgressBar(){

        // • Get completed goals
        completedGoals = 0;
        for (Goal goal:GoalService.getGoalList()){
            if (goal.completed.getValue()){
                completedGoals += 1;
            }
        }

        // • Update progress bar
        double progressValue = ((double) completedGoals / GoalService.getGoalList().size()) * 100;
        progress.setText(String.format("%.0f%%", progressValue));    }

    //════[EVENTS]════//
    public void onNewHabitPressed(){
        // • Load the Goal Editor UI
        GoalEditorController goalEditorController = ControllerService.getController("editor");
        goalEditorController.editHeaderText("Create Goal");
        goalEditorController.setSelectedGoalID(-1);
        goalEditorController.clearText();
        ViewSwitcher.switchTo("editor");
    }

    public void onEditHabitPressed(){
        GoalListController goalListController = ControllerService.getController("list");
        goalListController.onOpened();
        ViewSwitcher.switchTo("list");
    }

    public void onNewDayPressed(){
        today = today.plusDays(1);
        currentDate.setText(today.format(formatter));
        loadProgressBar();
        for (Goal goal:GoalService.getGoalList()){
            goal.completed.setValue(false);
        }
    }
}
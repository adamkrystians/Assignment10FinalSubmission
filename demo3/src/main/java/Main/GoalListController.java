package Main;

import Classes.Goal;
import Services.ControllerService;
import Services.GoalService;
import Services.ViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GoalListController implements Initializable {

    //════[VARIABLES]════//
    // ■  FXML
    @FXML Text header;
    @FXML ListView<Goal> goalList;
    @FXML Button edit;
    @FXML Button exit;

    //════[INITIALIZE]════//
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        goalList.setCellFactory(lv -> new TextFieldListCell<>(){

            @Override
            public void updateItem(Goal goal, boolean empty){
                super.updateItem(goal, empty);

                if (!empty){
                    setText(goal.name); // display name
                    setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
                    setFont(Font.font("Trebuchet MS", FontWeight.NORMAL, 14));
                    goalList.setStyle("-fx-background-color: #1e1e1e; -fx-control-inner-background: #1e1e1e;");
                }
            }
        });

        goalList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                GoalEditorController goalEditorController = ControllerService.getController("editor");
                goalEditorController.setSelectedGoalID(newVal.ID);
            }
        });
    }

    // Launched whenever the GoalList tab is opened.
    public void onOpened(){
        GoalEditorController goalEditorController = ControllerService.getController("editor");
        goalEditorController.setSelectedGoalID(-1);
        goalList.getItems().clear();
        ObservableList<Goal> goalsToLoad = GoalService.getObservableGoalList(goalList);
        System.out.println(goalsToLoad);
        goalList.setItems(goalsToLoad);
    }

    public void onExitPressed(){
        ViewSwitcher.switchTo("home");
    }

    public void onEditPressed(){
        GoalEditorController goalEditorController = ControllerService.getController("editor");
        if (!goalEditorController.selectedGoalID.equals(-1)){
            ViewSwitcher.switchTo("editor");
        }
    }
}

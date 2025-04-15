package Main;

import Classes.Goal;
import Services.ControllerService;
import Services.GoalService;
import Services.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class GoalEditorController {

    @FXML Text header;
    @FXML TextArea goalName;
    @FXML TextArea goalDescription;
    @FXML Button save;
    @FXML Button exit;

    public Integer selectedGoalID;

    public void fillGoalInfo(){
        Goal foundGoal = GoalService.getGoalInfo(selectedGoalID);

        if(foundGoal != null){
            goalName.setText(foundGoal.name);
            goalDescription.setText(foundGoal.description);
            header.setText("Edit Goal");
        }
    }

    public void setSelectedGoalID(int goalID){
        this.selectedGoalID = goalID;
    }

    public void editHeaderText(String newText){
        header.setText(newText);
    }

    public void clearText(){
        this.goalName.setText("");
        this.goalDescription.setText("");
    }

    public void onSavePressed(){
        GoalService.saveGoal(goalName.getText(), goalDescription.getText(), selectedGoalID);
    }

    public void onExitPressed(){
        ViewSwitcher.switchTo("home");
    }

    public void onDeletePressed(){
        if (selectedGoalID == -1){
            ViewSwitcher.switchTo("home");
        }else {
            GoalService.deleteGoal(selectedGoalID);
            HomeController homeController = ControllerService.getController("home");
            homeController.loadGoalList();
            ViewSwitcher.switchTo("home");
        }
    }
}

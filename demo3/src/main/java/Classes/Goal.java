package Classes;

import Services.GoalService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Goal {

    //════[VARIABLES]════//
    public String name;
    public String description;
    public BooleanProperty completed = new SimpleBooleanProperty(false);
    public Integer ID;

    //════[CONSTRUCTOR]════//
    public Goal(String name, String description){
        this.name = name;
        this.description = description;
        this.completed.set(false);
        ID = GoalService.getGoalList().size() + 1;
    }

    //════[METHODS]════//
    public void markCompleted(){

    }

    public void unmarkCompleted(){

    }

    @Override
    public String toString() {
        return name;
    }
}
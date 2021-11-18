package ch.saschaschumacher.notenrechnergui.gui;

import ch.saschaschumacher.notenrechnergui.StateModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SortPane extends StackPane {


    public SortPane(Stage stage, StateModel stateModel) {

        CheckBox checkBox = new CheckBox("Sort by grade");

        EventHandler<ActionEvent> event = e -> stateModel.setSortByGrade(checkBox.isSelected());
        checkBox.setOnAction(event);

        this.getChildren().add(checkBox);
    }
}

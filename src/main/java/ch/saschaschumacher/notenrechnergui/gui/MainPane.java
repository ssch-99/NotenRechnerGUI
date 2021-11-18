package ch.saschaschumacher.notenrechnergui.gui;

import ch.saschaschumacher.notenrechnergui.StateModel;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Map;

public class MainPane extends StackPane {

    private Map<String, String> majorMap;

    public MainPane(Stage stage, StateModel stateModel){

        GraphicsPane graphicsPane = new GraphicsPane(stateModel);
        ControlPane controlPane = new ControlPane(stage,stateModel);
        SortPane sortPane = new SortPane(stage,stateModel);
// Layout the components
//-----------------------

        final SplitPane split = new SplitPane();
        split.setOrientation(Orientation.VERTICAL);
        split.setDividerPosition(0, 0.1);
        split.getItems().addAll(sortPane,graphicsPane);


        final SplitPane verticalSplitPane = new SplitPane();
        verticalSplitPane.setOrientation(Orientation.HORIZONTAL);
        verticalSplitPane.setDividerPosition(0, 0.3);
        verticalSplitPane.getItems().addAll(controlPane, split);
        getChildren().add(verticalSplitPane);


    }


}

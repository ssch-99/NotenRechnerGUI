package ch.saschaschumacher.notenrechnergui.gui;

import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class MainPane extends StackPane {

    private Map<String, String> majorMap;

    public MainPane(Map<String, String> majorMap){

        GraphicsPane graphicsPane = new GraphicsPane();
        ControlPane controlPane = new ControlPane(majorMap, graphicsPane);
// Layout the components
//-----------------------

        final SplitPane verticalSplitPane = new SplitPane();
        verticalSplitPane.setOrientation(Orientation.HORIZONTAL);
        verticalSplitPane.setDividerPosition(0, 0.3);
        verticalSplitPane.getItems().addAll(controlPane, graphicsPane);
        getChildren().add(verticalSplitPane);


    }


}

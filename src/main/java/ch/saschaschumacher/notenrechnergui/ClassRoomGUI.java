package ch.saschaschumacher.notenrechnergui;

import ch.saschaschumacher.notenrechnergui.gui.MainPane;
import ch.saschaschumacher.notenrechnergui.io.MajorMapReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class ClassRoomGUI extends Application {
    @Override
    public void start(Stage stage) {

        MajorMapReader mapReader = new MajorMapReader("daten/major-map.txt");
        Map<String, String> majorMap = mapReader.readMap();
        // Create the components (well, just the main one, the rest is in there)
        Pane mainPane = new MainPane(majorMap);
        // Kick-off and wait for events...
        StackPane rootPane = new StackPane(mainPane);
        Scene scene = new Scene(rootPane, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Classroom App");
        stage.show();
    }
    public static void main(String[] args) {

        launch();
    }
}

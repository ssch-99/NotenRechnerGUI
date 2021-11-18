package ch.saschaschumacher.notenrechnergui.gui;

import ch.saschaschumacher.notenrechnergui.StateModel;
import ch.saschaschumacher.notenrechnergui.io.CourseDataReader;
import ch.saschaschumacher.notenrechnergui.io.CsvDataReader;
import ch.saschaschumacher.notenrechnergui.io.TagValueDataReader;
import ch.saschaschumacher.notenrechnergui.logic.Course;
import ch.saschaschumacher.notenrechnergui.logic.Student;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public class ControlPane extends StackPane {


    public ControlPane(Stage stage, StateModel stateModel){

        Button loadButton = new Button("Load Data...");
        Label numberLabel = new Label("Number of Students: ");
        Label numberLabelValue = new Label("x");
        ListView<String> list = new ListView<String>();
        Slider preGradeFactorSlider = new Slider(0, 1, 0.5);

        loadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File dataFile = fileChooser.showOpenDialog(stage);
            if (dataFile != null) {
  //[...get a reader]
                Optional<CourseDataReader> dataReader = getReader(dataFile.getPath());
                if (dataReader.isPresent()) {
                    Optional<Course> courseData = dataReader.get().readData();
                    if (courseData.isPresent()) {
                        stateModel.setCourse(courseData.get());
                        refreshText(numberLabelValue, list,stateModel);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Unable to read course data from file " + dataFile.getName());
                                alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Unknown File format " + dataFile.getName());
                    alert.showAndWait();
                }
            }
        });

        // For a slider, we need to attach an event listener to the value property of the slider
        preGradeFactorSlider.valueProperty().addListener(observable -> {
            stateModel.setPreGradeFactor(preGradeFactorSlider.getValue());
            refreshText(numberLabelValue, list,stateModel);

        });

        VBox mainPane = new VBox();
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.getChildren().addAll(loadButton,numberLabel,numberLabelValue,preGradeFactorSlider,list);


        this.getChildren().addAll(mainPane);


    }

    private void refreshText(Label numberLabelValue, ListView<String> listView, StateModel stateModel) {
        numberLabelValue.setText(String.valueOf(stateModel.getCourse().getStudents().size()));
        listView.getItems().clear();
        for (Student student : stateModel.getCourse().getStudents()){
            listView.getItems().add(student + "("+ stateModel.getMajorMap().get(student.getMajor())+"):" + student.getFinalGrade(stateModel.getPreGradeFactor()));
        }
        /*this.graphicsPane.setPreGradeFactor(value);
        this.graphicsPane.setStudents(course.getStudents());*/

    }

    private static Optional<CourseDataReader> getReader(String fileName) {
        if (fileName.endsWith(".csv")) {
            return Optional.of(new CsvDataReader(fileName));
        } else if (fileName.endsWith(".txt")) {
            return Optional.of(new TagValueDataReader(fileName));
        }
        return Optional.empty();
    }
}

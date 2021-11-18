package ch.saschaschumacher.notenrechnergui.gui;

import ch.saschaschumacher.notenrechnergui.StateModel;
import ch.saschaschumacher.notenrechnergui.logic.Student;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GraphicsPane extends Pane {

    private StateModel stateModel;

    public GraphicsPane(StateModel stateModel) {
        this.stateModel = stateModel;

        stateModel.addObserver(this::drawGraphics);

    }

    private void drawGraphics() {

        if(this.stateModel.isSortByGrade()){
            this.stateModel.getCourse().getStudents().sort(Comparator.comparingDouble((Student s) -> s.getFinalGrade(this.stateModel.getPreGradeFactor())).reversed());
        }else{
            this.stateModel.getCourse().getStudents().sort(Comparator.comparing(Student::getName));
        }

        this.getChildren().clear();

        double barWidth = getWidth() / this.stateModel.getCourse().getStudents().size();

        for (int i = 0; i < this.stateModel.getCourse().getStudents().size(); i++) {
            Student student = this.stateModel.getCourse().getStudents().get(i);
            double barHeight = student.getFinalGrade(this.stateModel.getPreGradeFactor()) / 6.0 * getHeight();
            double x = i * barWidth;
            double y = getHeight() - barHeight; // die y-Koordinaten von JavaFX stehen auf de Kopf
            Rectangle gradeBar = new Rectangle(x , y, barWidth, barHeight);

            if (student.getFinalGrade(this.stateModel.getPreGradeFactor()) >= 4) {
                gradeBar.setFill(Color.CORNFLOWERBLUE);
            } else {
                gradeBar.setFill(Color.INDIANRED);
            }
            getChildren().add(gradeBar);

            Text nameText = new Text(student.getName());
            nameText.getTransforms().add(new Translate(x + barWidth/2, getHeight() - 10));
            nameText.getTransforms().add(new Rotate(-90));
            getChildren().add(nameText);
        }

        double lineHeight = getHeight()/6 * 2;
        Line line = new Line();
        line.setStartX(0);
        line.setStartY(lineHeight);
        line.setEndX(getWidth());
        line.setEndY(lineHeight);
        line.setStrokeWidth(5);
        line.setStroke(Color.RED);
        getChildren().add(line);


    }

}

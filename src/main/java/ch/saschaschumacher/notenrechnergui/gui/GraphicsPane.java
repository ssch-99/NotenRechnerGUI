package ch.saschaschumacher.notenrechnergui.gui;

import ch.saschaschumacher.notenrechnergui.logic.Student;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.List;

public class GraphicsPane extends Pane {

    private List<Student> students = new ArrayList<>();
    private Double preGradeFactor;


    private void drawGraphics() {

        this.getChildren().clear();

        double barWidth = getWidth() / students.size();

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            double barHeight = student.getFinalGrade(this.preGradeFactor) / 6.0 * getHeight();
            double x = i * barWidth;
            double y = getHeight() - barHeight; // die y-Koordinaten von JavaFX stehen auf de Kopf
            Rectangle gradeBar = new Rectangle(x , y, barWidth, barHeight);

            if (student.getFinalGrade(preGradeFactor) >= 4) {
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


    public void setStudents(List<Student> students) {
        this.students = students;
        this.drawGraphics();
    }

    public void setPreGradeFactor(Double preGradeFactor) {
        this.preGradeFactor = preGradeFactor;
        this.drawGraphics();
    }
}

package ch.saschaschumacher.notenrechnergui.io;

import ch.saschaschumacher.notenrechnergui.logic.Course;
import ch.saschaschumacher.notenrechnergui.logic.RegularStudent;
import ch.saschaschumacher.notenrechnergui.logic.RepeatingStudent;
import ch.saschaschumacher.notenrechnergui.logic.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagValueDataReader implements CourseDataReader {
    private final File courseDataFile;

    public TagValueDataReader(String courseDataFile) {
        this.courseDataFile = new  File(courseDataFile);
    }

    public Optional<Course> readData() {

        Course course = new Course();
        try {
            FileInputStream fstream = new FileInputStream(this.courseDataFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            int count = 1;

            String name = "";
            String major = "";
            boolean isRepeating = false;
            double examGrade = 0.0;
            List<Double> preGrades = new ArrayList<>();


            int countLines = 0;

            while ((strLine = br.readLine()) != null) {

                if (count == 1) {
                    course.setId(strLine.split(": ")[1].trim());

                } else if (count == 2) {
                    course.setName(strLine.split(": ")[1].trim());
                } else {


                    if(strLine.contains("name:")){
                        name = strLine.split("name:")[1].trim();
                    }
                    if(strLine.contains("major:")){
                        major = strLine.split("major:")[1].trim();
                    }
                    if(strLine.contains("is_repeating:")){
                        isRepeating = Boolean.parseBoolean(strLine.split("is_repeating:")[1].trim());
                    }
                    if(strLine.contains("exam-grade:")){
                        examGrade = Double.parseDouble(strLine.split("exam-grade:")[1].trim());
                    }
                    if(strLine.contains("pre-grade:")){
                        String[] grades = strLine.split("pre-grade:")[1].split(",");
                        for (String grade : grades) {
                            preGrades.add(Double.parseDouble(grade));
                        }
                    }
                    ++countLines;

                    if((countLines == 4 && isRepeating) || countLines == 5){

                        Student student;
                        if (isRepeating) {
                            student = new RepeatingStudent(name,major,examGrade);
                        } else {
                            student = new RegularStudent(name, major, preGrades, examGrade);
                            preGrades = new ArrayList<>();

                        }
                        course.getStudents().add(student);
                        countLines = 0;
                    }
                }
                count++;
            }
            fstream.close();

            return Optional.of(course);

        } catch (IOException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

}

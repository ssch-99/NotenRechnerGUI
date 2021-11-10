package ch.saschaschumacher.notenrechnergui.io;


import ch.saschaschumacher.notenrechnergui.logic.Course;
import ch.saschaschumacher.notenrechnergui.logic.RegularStudent;
import ch.saschaschumacher.notenrechnergui.logic.RepeatingStudent;
import ch.saschaschumacher.notenrechnergui.logic.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvDataReader implements CourseDataReader {
    private final File courseDataFile;

    public CsvDataReader(String courseDataFile) {
        this.courseDataFile = new  File(courseDataFile);
    }

    public Optional<Course> readData() {

        Course course = new Course();
        try {
            FileInputStream fstream = new FileInputStream(this.courseDataFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            int count = 1;

            while ((strLine = br.readLine()) != null) {

                if (count == 1) {
                    course.setId(strLine.trim());

                } else if (count == 2) {
                    course.setName(strLine.trim());
                } else {

                    String[] splittedArray = strLine.split(",");

                    String isRepeating = splittedArray[2].trim();

                    String name = splittedArray[0].trim();
                    String major = splittedArray[1].trim();
                    double examGrade =  Double.parseDouble(splittedArray[3].trim());

                    Student student;
                    if (isRepeating.equals("r")) {
                        student = new RepeatingStudent(name,major,examGrade);
                    } else {
                        List<Double> studentGrades = new ArrayList<>();
                        for (int i = 4; i < splittedArray.length; i++) {
                            double grade = Double.parseDouble(splittedArray[i]);
                            studentGrades.add(grade);
                        }
                        student = new RegularStudent(name, major, studentGrades, examGrade);
                    }
                    course.getStudents().add(student);

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

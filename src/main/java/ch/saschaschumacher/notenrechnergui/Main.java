package ch.saschaschumacher.notenrechnergui;


import ch.saschaschumacher.notenrechnergui.io.CourseDataReader;
import ch.saschaschumacher.notenrechnergui.io.CsvDataReader;
import ch.saschaschumacher.notenrechnergui.io.MajorMapReader;
import ch.saschaschumacher.notenrechnergui.io.TagValueDataReader;
import ch.saschaschumacher.notenrechnergui.logic.Course;
import ch.saschaschumacher.notenrechnergui.logic.Student;

import java.util.Map;
import java.util.Optional;

public class Main {


    public static void main(String[] args) {
        MajorMapReader mapReader = new MajorMapReader("daten/major-map.txt");
        Map<String, String> majorMap = mapReader.readMap();

        String fileName = "daten/grades-data-files-v04/grades-v04.txt";
        Optional<CourseDataReader> dataReader = getReader(fileName);
        if (dataReader.isPresent()) {
            Optional<Course> course = dataReader.get().readData();

            final double PREGRADE_FACTOR = 0.3;
            if (course.isPresent()) {
                displayAverageGrades(majorMap, course.get(), PREGRADE_FACTOR);
            } else {
                System.out.println("Unable to read student data, sorry.");
            }
        } else {
            System.out.println("File format unknown for file " + fileName);
        }
    }

    private static Optional<CourseDataReader> getReader(String fileName) {
        if (fileName.endsWith(".csv")) {
            return Optional.of(new CsvDataReader(fileName));
        } else if (fileName.endsWith(".txt")) {
            return Optional.of(new TagValueDataReader(fileName));
        }
        return Optional.empty();
    }


    private static void displayAverageGrades(Map<String,String> majorMap, Course course, double factor) {

        System.out.printf("Grades for: %s (%s)%n", course.getName(),course.getId());
        System.out.println("---------------------------------------------------");

        for (Student student : course.getStudents()) {
            String major;
            // Just to be safe, we first check if the key is present in the map
            if (majorMap.containsKey(student.getMajor())) {
                major = majorMap.get(student.getMajor());
            } else {
                // ...and if not then we just use the code instead of the description
                major = student.getMajor();

            }

                System.out.println("The final grade for " + student + " (" + major + ") is: " + student.getFinalGrade(factor));

        }
        System.out.println("---------------------------------------------------");

    }
}

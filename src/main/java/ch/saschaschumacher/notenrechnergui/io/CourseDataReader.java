package ch.saschaschumacher.notenrechnergui.io;

import ch.saschaschumacher.notenrechnergui.logic.Course;

import java.util.Optional;

public interface CourseDataReader {

    Optional<Course> readData();
}

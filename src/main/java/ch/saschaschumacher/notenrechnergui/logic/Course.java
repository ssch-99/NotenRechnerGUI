package ch.saschaschumacher.notenrechnergui.logic;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private List<Student> students = new ArrayList<>();

    public Course() {
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }
}

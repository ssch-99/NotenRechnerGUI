package ch.saschaschumacher.notenrechnergui;

import ch.saschaschumacher.notenrechnergui.logic.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StateModel {
    private Course course;
    private double preGradeFactor;
    private boolean sortByGrade;
    private Map<String,String> majorMap;
    List<StateObserver> observers;
    public StateModel() {
        observers = new ArrayList<>();
    }
    public void setCourse(Course course) {
        this.course = course;
        sendStateChangedEvent();
    }

    public void addObserver(StateObserver observer) {
        observers.add(observer);
    }
    private void sendStateChangedEvent() {
        for (StateObserver observer : observers) {
            observer.stateChanged();
        }
    }

    public Course getCourse() {
        return course;
    }

    public boolean isSortByGrade() {
        return sortByGrade;
    }

    public void setSortByGrade(boolean sortByGrade) {
        this.sortByGrade = sortByGrade;
        sendStateChangedEvent();
    }

    public double getPreGradeFactor() {
        return preGradeFactor;
    }

    public void setPreGradeFactor(double preGradeFactor) {
        this.preGradeFactor = preGradeFactor;
        sendStateChangedEvent();
    }

    public Map<String, String> getMajorMap() {
        return majorMap;
    }

    public void setMajorMap(Map<String, String> majorMap) {
        this.majorMap = majorMap;
        sendStateChangedEvent();
    }
}
package ch.saschaschumacher.notenrechnergui.logic;

import java.util.List;

public class RegularStudent extends Student {

    private List<Double> grades;
    private double examGrade;

    public RegularStudent(String name, String major, List<Double> grades, double examGrade) {
        super(name, major);
        this.grades = grades;
        this.examGrade = examGrade;
    }

    private double computeGradeAverage(){

        return grades.stream().sorted().skip(1).
                mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);
    }

    @Override
    public double getFinalGrade(double preGradeFactor) {
        return Math.round((this.computeGradeAverage() * preGradeFactor + (1-preGradeFactor) * this.examGrade) * 10.0) / 10.0;

    }

    @Override
    public String toString() {
        return super.getName();
    }
}

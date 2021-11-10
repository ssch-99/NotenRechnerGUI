package ch.saschaschumacher.notenrechnergui.logic;


public class RepeatingStudent extends Student {


    private double examGrade;


    public RepeatingStudent(String name, String major, double examGrade) {
        super(name, major);
        this.examGrade = examGrade;
    }

    @Override
    public double getFinalGrade(double preGradeFactor) {

        return this.examGrade;


    }

    @Override
    public String toString() {
        return super.getName() + "*";
    }
}

package com.company;

public class Student extends UniversityMember implements ICloneable {
    private int year;
    private int group;

    public int getYear() {
        return year;
    }

    public int getGroup() {
        return group;
    }

    public Student(String name, String surname, int year, int group) throws Exception {
        super(name, surname);
        if (group < 0 || year < 0) {
            throw new Exception();
        } else {
            this.year = year;
            this.group = group;
        }
    }

    private Student(String name,int year, String surname, int group) {
        super(name, surname);
        this.year = year;
        this.group = group;

    }

    public Student clone() {
        return new Student(name,year,surname,group);
    }

    @Override
    public String toString() {
        return "Student: " + super.toString() + "year: " + year + " group: " + group;
    }
}

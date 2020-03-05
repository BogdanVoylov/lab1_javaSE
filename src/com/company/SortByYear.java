package com.company;

import java.util.Comparator;

public class SortByYear implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getYear()-s2.getYear();
    }
}

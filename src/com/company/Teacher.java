package com.company;

import java.util.stream.IntStream;

public class Teacher extends UniversityMember implements ICloneable {
    private int[] groups;

    public Teacher(String name, String surname, int[] groups) {
        super(name, surname);
        this.groups = groups;
    }

    public boolean isInGroup(int group) {
        return IntStream.of(groups).anyMatch(x -> x == group);
    }

    public Teacher clone() {
        return new Teacher(name, surname, groups.clone());
    }

    @Override
    public String toString() {
        String groupsToString = "[";
        int i = 0;
        for (i = 0; i < groups.length - 1; i++) {
            groupsToString += groups[i] + ",";
        }
        groupsToString += groups[i] + "]";
        return "Teacher\n" + super.toString() + " Groups: " + groupsToString;
    }
}

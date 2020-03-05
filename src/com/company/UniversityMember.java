package com.company;

public abstract class UniversityMember {
    protected String name;
    protected String surname;

    public UniversityMember(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    @Override
    public String toString() {
        return "name: " + name + "; surname: " + surname + "; ";
    }
}

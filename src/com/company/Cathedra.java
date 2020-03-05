package com.company;

import java.util.Arrays;

public class Cathedra {
    private String name;
    private Student[] students;
    private Teacher[] teachers;
    public Cathedra(String name){
        this.name = name;
        students = new Student[0];
        teachers = new Teacher[0];
    }

    public void addStudent(Student student){
        students = Arrays.copyOf(Resizer.addValue(students,student),students.length+1,Student[].class);
    }

    public void removeStudent(int index){
        students = Arrays.copyOf(Resizer.removeValue(students,index),students.length-1,Student[].class);
    }

    public void addTeacher(Teacher teacher){
        teachers = Arrays.copyOf(Resizer.addValue(teachers,teacher),teachers.length+1,Teacher[].class);
    }

    public void removeTeacher(int index){
        teachers = Arrays.copyOf(Resizer.removeValue(teachers,index),teachers.length-1,Teacher[].class);
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getTeachersList(){
        String res = new String();
        for(int i = 0; i < teachers.length; i++){
            res+=i+"."+teachers[i]+"\n";
        }
        return res;
    }

    public String getStudentsList(){
        String res = new String();
        for(int i = 0; i < students.length; i++){
            res+=i+"."+students[i]+"\n";
        }
        return res;
    }

    public String getStudentsByName(String name){
        String res = new String();
        for(Student student:students){
            if(name.equals(student.getName())){
                res+=student+"\n";
            }
        }
        return res;
    }

    public String getStudentsBySurname(String surname){
        String res = new String();
        for(Student student:students){
            if(surname.equals(student.getSurname())){
                res+=student+"\n";
            }
        }
        return res;
    }

    public String getStudentsByYear(int year){
        String res = new String();
        for(Student student:students){
            if(year == student.getYear()){
                res+=student+"\n";
            }
        }
        return res;
    }

    public String getStudentsByGroup(int group){
        String res = new String();
        for(Student student:students){
            if(group == student.getGroup()){
                res+=student+"\n";
            }
        }
        return res;
    }

    public String getTeachersByName(String name){
        String res = new String();
        for(Teacher teacher:teachers){
            if(name.equals(teacher.getName())){
                res+=teacher+"\n";
            }
        }
        return res;
    }

    public String getTeachersBySurname(String surname){
        String res = new String();
        for(Teacher teacher:teachers){
            if(surname.equals(teacher.getSurname())){
                res+=teacher+"\n";
            }
        }
        return res;
    }

    public String getTeachersByGroup(int group){
        String res = new String();
        for(Teacher teacher:teachers){
            if(teacher.isInGroup(group)){
                res+=teacher+"\n";
            }
        }
        return res;
    }

    public Student[] getStudents() {
        return Arrays.copyOf(Resizer.clone(students),students.length,Student[].class);
    }

    @Override
    public String toString(){
        String studentsString = new String();
        for(Student student:students){
            studentsString+=student.toString()+"\n";
        }
        String teachersString = new String();
        for(Teacher teacher:teachers){
            teachersString+=teacher.toString()+"\n";
        }
        return "Cathedra: "+name+"\nStudents: "+studentsString+"\nTeachers: "+teachersString;
    }
}

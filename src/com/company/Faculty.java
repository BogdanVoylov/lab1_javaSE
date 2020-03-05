package com.company;

import java.util.Arrays;

public class Faculty {
    private String name;
    private Cathedra[] cathedras;
    public Faculty(String name){
        this.name = name;
        cathedras = new Cathedra[0];
    }

    public void addCathedra(Cathedra cathedra){
        cathedras = Arrays.copyOf(Resizer.addValue(cathedras,cathedra),cathedras.length+1,Cathedra[].class);
    }

    public void removeCathedra(int index){
        cathedras = Arrays.copyOf(Resizer.removeValue(cathedras,index),cathedras.length-1,Cathedra[].class);
    }

    public void addTeacherToCathedra(int cathedraIndex,Teacher teacher){
        cathedras[cathedraIndex].addTeacher(teacher);
    }

    public void addStudentToCathedra(int cathedraIndex, Student student){
        cathedras[cathedraIndex].addStudent(student);
    }

    public void removeTeacherFromCathedra(int cathedraIndex, int teacherIndex){
        cathedras[cathedraIndex].removeTeacher(teacherIndex);
    }

    public void removeStudentFromCathedra(int cathedraIndex, int studentIndex){
        cathedras[cathedraIndex].removeStudent(studentIndex);
    }

    public String getListOfTeachersFromCathedra(int index){
        return cathedras[index].getTeachersList();
    }

    public String getListOfStudentsFromCathedra(int index){
        return cathedras[index].getStudentsList();
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setNameToCathedra(int cathedraIndex, String newName){
        cathedras[cathedraIndex].setName(newName);
    }

    public String getListOfCathedras(){
        String cathedrasList = new String();
        for(int i = 0; i < cathedras.length; i++){
            cathedrasList+=i+"."+cathedras[i].getName()+"\n";
        }
        return cathedrasList;
    }

    public String findStudentsByName(String name){
        String res = new String();
        for(Cathedra cathedra:cathedras){
            res+=cathedra.getStudentsByName(name);
        }
        return res;
    }
    public String findStudentsBySurname(String surname){
        String res = new String();
        for(Cathedra cathedra:cathedras){
            res+=cathedra.getStudentsBySurname(surname);
        }
        return res;
    }
    public String findStudentsByYear(int year){
        String res = new String();
        for(Cathedra cathedra:cathedras){
            res+=cathedra.getStudentsByYear(year);
        }
        return res;
    }
    public String findStudentsByGroup(int group){
        String res = new String();
        for(Cathedra cathedra:cathedras){
            res+=cathedra.getStudentsByGroup(group);
        }
        return res;
    }
    public String findTeachersByName(String name){
        String res = new String();
        for(Cathedra cathedra:cathedras){
            res+=cathedra.getTeachersByName(name);
        }
        return res;
    }
    public String findTeachersBySurname(String surname){
        String res = new String();
        for(Cathedra cathedra:cathedras){
            res+=cathedra.getTeachersBySurname(surname);
        }
        return res;
    }
    public String findTeachersByGroup(int group){
        String res = new String();
        for(Cathedra cathedra:cathedras){
            res+=cathedra.getTeachersByGroup(group);
        }
        return res;
    }

    public Student[] getStudents(){
        Student[] res = new Student[0];
        for(Cathedra cathedra:cathedras){
            Object[] combination = Resizer.combine(res,cathedra.getStudents());
            res = Arrays.copyOf(combination,combination.length,Student[].class);
        }
        return res;
    }

    @Override
    public String toString(){
        String cathedrasString = new String();
        for(Cathedra cathedra:cathedras){
            cathedrasString+=cathedra.toString()+"\n";
        }

        return "Faculty: "+name+"\nCathedras:\n"+cathedrasString;
    }
}

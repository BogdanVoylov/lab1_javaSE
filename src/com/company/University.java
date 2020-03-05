package com.company;

import java.util.Arrays;

public class University {
    private Faculty[] faculties;
    public University(){
        faculties = new Faculty[0];
    }

    public void addFaculty(Faculty faculty){
        faculties = Arrays.copyOf(Resizer.addValue(faculties,faculty),faculties.length+1,Faculty[].class);
    }

    public void removeFaculty(int index){
        faculties = Arrays.copyOf(Resizer.removeValue(faculties,index),faculties.length-1,Faculty[].class);
    }

    public boolean checkIfFacultyExist(int index){
        if(faculties[index]!=null){
            return true;
        }else {
            return false;
        }
    }

    public void addCathedraToFaculty(int facultyIndex,Cathedra cathedra){
        faculties[facultyIndex].addCathedra(cathedra);
    }

    public void setNameToFaculty(int facultyIndex, String newName){
        faculties[facultyIndex].setName(newName);
    }

    public void setNameToCathedra(int facultyIndex,int cathedraIndex, String newName) {
        faculties[facultyIndex].setNameToCathedra(cathedraIndex,newName);
    }

    public void removeCathedra(int facultyIndex, int cathedraIndex){
        faculties[facultyIndex].removeCathedra(cathedraIndex);
    }

    public String getListOfFaculties(){
        String facultiesList = new String();
        for(int i = 0; i < faculties.length; i++){
            facultiesList+=i+"."+faculties[i].getName()+"\n";
        }
        return facultiesList;
    }

    public String getListOfCathedras(int facultyIndex){
        return faculties[facultyIndex].getListOfCathedras();
    }

    public void addTeacherToCathedra(int facultyIndex,int cathedraIndex,Teacher teacher){
        faculties[facultyIndex].addTeacherToCathedra(cathedraIndex,teacher);
    }

    public void removeTeacherFromCathedra(int facultyIndex, int cathedraIndex, int teacherIndex){
        faculties[facultyIndex].removeTeacherFromCathedra(cathedraIndex,teacherIndex);
    }

    public String getListOfTeachersFromCathedra(int facultyIndex, int cathedraIndex){
        return faculties[facultyIndex].getListOfTeachersFromCathedra(cathedraIndex);
    }

    public void addStudentToCathedra(int facultyIndex, int cathedraIndex, Student student){
        faculties[facultyIndex].addStudentToCathedra(cathedraIndex,student);
    }

    public void removeStudentFromCathedra(int facultyIndex, int cathedraIndex, int studentIndex){
        faculties[facultyIndex].removeStudentFromCathedra(cathedraIndex,studentIndex);
    }

    public String getListOfStudentsFromCathedra(int facultyIndex, int cathedraIndex){
        return faculties[facultyIndex].getListOfStudentsFromCathedra(cathedraIndex);
    }

    public String findStudentsByName(String name){
        String res = new String();
        for(Faculty faculty:faculties){
            res+=faculty.findStudentsByName(name);
        }
        return res;
    }
    public String findStudentsBySurname(String surname){
        String res = new String();
        for(Faculty faculty:faculties){
            res+=faculty.findStudentsBySurname(surname);
        }
        return res;
    }
    public String findStudentsByYear(int year){
        String res = new String();
        for(Faculty faculty:faculties){
            res+=faculty.findStudentsByYear(year);
        }
        return res;
    }
    public String findStudentsByGroup(int group){
        String res = new String();
        for(Faculty faculty:faculties){
            res+=faculty.findStudentsByGroup(group);
        }
        return res;
    }
    public String findTeachersByName(String name){
        String res = new String();
        for(Faculty faculty:faculties){
            res+=faculty.findTeachersByName(name);
        }
        return res;
    }
    public String findTeachersBySurname(String surname){
        String res = new String();
        for(Faculty faculty:faculties){
            res+=faculty.findTeachersBySurname(surname);
        }
        return res;
    }
    public String findTeachersByGroup(int group){
        String res = new String();
        for(Faculty faculty:faculties){
            res+=faculty.findTeachersByGroup(group);
        }
        return res;
    }

    public Student[] getAllStudents(){
        Student[] res = new Student[0];
        for(Faculty faculty:faculties){
            Object[] combination = Resizer.combine(res,faculty.getStudents());
            res = Arrays.copyOf(combination,combination.length,Student[].class);
        }
        return res;
    }

    public Student[] allStudentsSortedByYear(){
        Student[] students = getAllStudents();
        Arrays.sort(students,new SortByYear());
        return students;
    }

    @Override
    public String toString(){
        String facultiesString = new String();
        for(Faculty faculty:faculties){
            facultiesString+=faculty.toString()+"\n";
        }

        return "Faculties:\n"+facultiesString;
    }
}

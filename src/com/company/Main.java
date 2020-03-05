package com.company;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final String CHOICE_STRING = "Enter your choice: ";

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("У програмі реалізовано наступні можливості\n");
        System.out.println("  1.Створити/видалити/редагувати факультет\n");
        System.out.println("  2.Створити/видалити/редагувати кафедру факультета.\n");
        System.out.println("  3.Додати/видалити/редагувати студента/викладача до кафедри.\n");
        System.out.println("  4.Знайти студента/викладача за ПІБ, курсом або групою.\n");
        System.out.println("  5.Вивести всіх студентів впорядкованих за курсами.\n");
        System.out.println("  6.Вивести всіх студентів/викладачів факультета впорядкованих за алфавітом.\n");
        System.out.println("  7.Вивести всіх студентів кафедри впорядкованих за курсами.\n");
        System.out.println("  8.Вивести всіх студентів/викладачів кафедри впорядкованих за алфавітом.\n");
        System.out.println("  9.Вивести всіх студентів кафедри вказаного курсу.\n");
        System.out.println("  10.Вивести всіх студентів кафедри вказаного курсу впорядкованих за алфавітом.");
        System.out.println("  'all' Вивести все.");
        University university = new University();
        String input;
        while (true) {
            try {
                System.out.print("(main menu)" + CHOICE_STRING);
                input = bufferedReader.readLine();
                if (input.equals("1")) {
                    handleFacultyMenu(bufferedReader, university);
                } else if (input.equals("2")) {
                    handleCathedraMenu(bufferedReader, university);
                } else if (input.equals("3")) {
                    handleStudentTeacherMenu(bufferedReader, university);
                } else if (input.equals("4")) {
                    handleFindStudentTeacher(bufferedReader, university);
                } else if (input.equals("5")) {
                    handleSortAllStudentsByYear(bufferedReader, university);
                } else if (input.equals("6")) {
                    handleSortStudentTeacherByAlphabet(bufferedReader, university);
                } else if (input.equals("7")) {
                    handleSortCathedraStudentsByYear(bufferedReader, university);
                } else if (input.equals("8")) {
                    handleSortCathedraStudentsTeachersByAlphabet(bufferedReader, university);
                } else if (input.equals("9")) {
                    handleCathedraStudentsOfYear(bufferedReader, university);
                } else if (input.equals("10")) {
                    handleCathedraStudentsOfYearByAlphabet(bufferedReader, university);
                } else if (input.equals("all")) {
                    System.out.println(university);
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Wrong input");
            }
        }
    }

    private static void handleCathedraStudentsOfYearByAlphabet(BufferedReader bufferedReader, University university) throws Exception {
        String menuPointer = "(sorted students of chosen year)";
        Pair<Integer, Integer> pair = getFacultyAndCathedraIndexes(bufferedReader, university, menuPointer);
        int facultyIndex = pair.getKey();
        int cathedraIndex = pair.getValue();
        System.out.print("Enter a studying year: ");
        System.out.print("Enter a studying year: ");
        try {
            int year = Integer.parseInt(bufferedReader.readLine());
            Student[] students = university.getStudentsFromCathedraOfYearByAlphabet(facultyIndex, cathedraIndex, year);
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    private static void handleCathedraStudentsOfYear(BufferedReader bufferedReader, University university) throws Exception {
        String menuPointer = "(cathedra students of chosen year)";
        Pair<Integer, Integer> pair = getFacultyAndCathedraIndexes(bufferedReader, university, menuPointer);
        int facultyIndex = pair.getKey();
        int cathedraIndex = pair.getValue();
        System.out.print("Enter a studying year: ");
        try {
            int year = Integer.parseInt(bufferedReader.readLine());
            Student[] students = university.getStudentsFromCathedraOfYear(facultyIndex, cathedraIndex, year);
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    private static void handleSortCathedraStudentsTeachersByAlphabet(BufferedReader bufferedReader, University university) throws Exception {
        String menuPointer = "(sort cathedra members by alphabet)";
        Pair<Integer, Integer> pair = getFacultyAndCathedraIndexes(bufferedReader, university, menuPointer);
        int facultyIndex = pair.getKey();
        int cathedraIndex = pair.getValue();
        boolean isStudent = chooseStudentTeacher(bufferedReader, menuPointer);
        if (isStudent) {
            Student[] sortedStudents = university.cathedraStudentsByAlphabet(facultyIndex, cathedraIndex);
            for (Student student : sortedStudents)
                System.out.println(student);
        } else {
            Teacher[] sortedTeachers = university.cathedraTeachersByAlphabet(facultyIndex, cathedraIndex);
            for (Teacher teacher : sortedTeachers)
                System.out.println(teacher);
        }
    }

    private static void handleSortCathedraStudentsByYear(BufferedReader bufferedReader, University university) throws Exception {
        String menuPointer = "(sort cathedra students by year)";
        int index = smartGetFacultyIndex(bufferedReader, university, menuPointer);
        Pair<Integer, Integer> pair = getFacultyAndCathedraIndexes(bufferedReader, university, menuPointer);
        int facultyIndex = pair.getKey();
        int cathedraIndex = pair.getValue();
        Student[] students = university.cathedraStudentsSortedByYear(facultyIndex, cathedraIndex);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void handleSortStudentTeacherByAlphabet(BufferedReader bufferedReader, University university) throws Exception {
        String menuPointer = "(sort by alphabet)";
        boolean isStudent = chooseStudentTeacher(bufferedReader, menuPointer);
        if (isStudent) {
            int index = smartGetFacultyIndex(bufferedReader, university, menuPointer);
            Student[] sortedStudents = university.facultyStudentsSortedByAlphabet(index);
            for (Student student : sortedStudents) {
                System.out.println(student);
            }
        } else {
            int index = smartGetFacultyIndex(bufferedReader, university, menuPointer);
            Teacher[] sortedTeachers = university.facultyTeachersSortedByAlphabet(index);
            for (Teacher teacher : sortedTeachers) {
                System.out.println(teacher);
            }
        }

    }

    private static void handleSortAllStudentsByYear(BufferedReader bufferedReader, University university) {
        Student[] sortedStudents = university.allStudentsSortedByYear();
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
    }

    private static void handleFindStudentTeacher(BufferedReader bufferedReader, University university) throws Exception {
        String findByNameCommand = "fbn";
        String findBySurnameCommand = "fbs";
        String findByGroupCommand = "fbg";
        String findByYearCommand = "fby";
        String exitCommand = "exit";
        String menuPointer = "(find menu)";
        String input;
        while (true) {
            System.out.print(menuPointer + CHOICE_STRING);
            input = bufferedReader.readLine();
            if (input.equals("help")) {
                System.out.println("'" + findByNameCommand + "' for find by name\n'" + findBySurnameCommand + "' for find by surname\n'" + findByYearCommand + "' for find by year\n" + findByGroupCommand + "' for find by group\n'" + exitCommand + "'for exit");
            } else if (input.equals(findByNameCommand)) {
                boolean isStudent = chooseStudentTeacher(bufferedReader, menuPointer);
                System.out.print(menuPointer + "Enter name: ");
                input = bufferedReader.readLine();
                if (isStudent) {
                    System.out.println(university.findStudentsByName(input));
                } else {
                    System.out.println(university.findTeachersByName(input));
                }
            } else if (input.equals(findBySurnameCommand)) {
                boolean isStudent = chooseStudentTeacher(bufferedReader, menuPointer);
                System.out.print(menuPointer + "Enter surname: ");
                input = bufferedReader.readLine();
                if (isStudent) {
                    System.out.println(university.findStudentsBySurname(input));
                } else {
                    System.out.println(university.findTeachersBySurname(input));
                }
            } else if (input.equals(findByGroupCommand)) {
                boolean isStudent = chooseStudentTeacher(bufferedReader, menuPointer);
                System.out.print(menuPointer + "Enter group: ");
                input = bufferedReader.readLine();
                int group = Integer.parseInt(input);
                if (isStudent) {
                    System.out.println(university.findStudentsByGroup(group));
                } else {
                    System.out.println(university.findTeachersByGroup(group));
                }
            } else if (input.equals(findByYearCommand)) {
                System.out.print(menuPointer + "Enter year: ");
                input = bufferedReader.readLine();
                int year = Integer.parseInt(input);
                System.out.println(university.findStudentsByYear(year));
            } else {
                throw new Exception();
            }
        }
    }

    public static void handleFacultyMenu(BufferedReader bufferedReader, University university) throws Exception {
        String addCommand = "add";
        String editCommand = "edit";
        String removeCommand = "remove";
        String exitCommand = "exit";
        String menuPointer = "(faculty menu)";
        String input;
        while (true) {
            System.out.print(menuPointer + CHOICE_STRING);
            input = bufferedReader.readLine();
            if (input.equals("help")) {
                System.out.println("'" + addCommand + "' for add faculty\n'" + editCommand + "' for edit faculty\n'" + removeCommand + "' for remove faculty\n'" + exitCommand + "' for exit");
            } else if (input.equals(addCommand)) {
                System.out.print(menuPointer + "Enter name: ");
                input = bufferedReader.readLine();
                university.addFaculty(new Faculty(input));
            } else if (input.equals(editCommand)) {
                System.out.println(university.getListOfFaculties());
                System.out.print(menuPointer + "Enter index: ");
                input = bufferedReader.readLine();
                int index = Integer.parseInt(input);
                System.out.print(menuPointer + "Enter new name: ");
                input = bufferedReader.readLine();
                university.setNameToFaculty(index, input);
            } else if (input.equals(removeCommand)) {
                System.out.println(university.getListOfFaculties());
                System.out.print(menuPointer + "Enter index: ");
                input = bufferedReader.readLine();
                int index = Integer.parseInt(input);
                university.removeFaculty(index);
            } else if (input.equals(exitCommand)) {
                break;
            } else {
                throw new Exception();
            }
        }
    }

    public static void handleCathedraMenu(BufferedReader bufferedReader, University university) throws Exception {
        String addCommand = "add";
        String editCommand = "edit";
        String removeCommand = "remove";
        String exitCommand = "exit";
        String menuPointer = "(cathedra menu)";
        String input;
        while (true) {
            System.out.print(menuPointer + CHOICE_STRING);
            input = bufferedReader.readLine();
            if (input.equals("help")) {
                System.out.println("'" + addCommand + "' for add cathedra\n'" + editCommand + "' for edit cathedra\n'" + removeCommand + "' for remove cathedra\n'" + exitCommand + "' for exit");
            } else if (input.equals(addCommand)) {
                int index = smartGetFacultyIndex(bufferedReader, university, menuPointer);
                System.out.print(menuPointer + "Enter cathedra name: ");
                input = bufferedReader.readLine();
                university.addCathedraToFaculty(index, new Cathedra(input));
            } else if (input.equals(editCommand)) {
                Pair<Integer, Integer> pair = getFacultyAndCathedraIndexes(bufferedReader, university, menuPointer);
                int facultyIndex = pair.getKey();
                int cathedraIndex = pair.getValue();
                System.out.print(menuPointer + "Enter new name: ");
                input = bufferedReader.readLine();
                university.setNameToCathedra(facultyIndex, cathedraIndex, input);
            } else if (input.equals(removeCommand)) {
                Pair<Integer, Integer> pair = getFacultyAndCathedraIndexes(bufferedReader, university, menuPointer);
                int facultyIndex = pair.getKey();
                int cathedraIndex = pair.getValue();
                university.removeCathedra(facultyIndex, cathedraIndex);
            } else if (input.equals(exitCommand)) {
                break;
            } else {
                throw new Exception();
            }
        }
    }

    public static Pair<Integer, Integer> getFacultyAndCathedraIndexes(BufferedReader bufferedReader, University university, String menuPointer) throws Exception {
        int facultyIndex = smartGetFacultyIndex(bufferedReader, university, menuPointer);
        System.out.print(university.getListOfCathedras(facultyIndex));
        System.out.print(menuPointer + "Enter cathedra index: ");
        String input = bufferedReader.readLine();
        int cathedraIndex = Integer.parseInt(input);
        return new Pair<>(facultyIndex, cathedraIndex);
    }

    public static int smartGetFacultyIndex(BufferedReader bufferedReader, University university, String menuPointer) throws Exception {
        System.out.println(university.getListOfFaculties());
        System.out.print(menuPointer + "Enter faculty index: ");
        String input = bufferedReader.readLine();
        int facultyIndex = Integer.parseInt(input);
        if (!university.checkIfFacultyExist(facultyIndex)) {
            throw new Exception();
        }
        return facultyIndex;
    }

    public static boolean chooseStudentTeacher(BufferedReader bufferedReader, String menuPointer) throws Exception {
        System.out.print(menuPointer + "Enter 0 for student or 1 for teacher: ");
        String input = bufferedReader.readLine();
        if (input.equals("0")) {
            return true;
        } else if (input.equals("1")) {
            return false;
        } else {
            throw new Exception();
        }
    }

    public static Student getStudent(BufferedReader bufferedReader, String menuPointer) throws Exception {
        System.out.print(menuPointer + "Enter name: ");
        String name = bufferedReader.readLine();
        System.out.print(menuPointer + "Enter surname: ");
        String surname = bufferedReader.readLine();
        System.out.print(menuPointer + "Enter year: ");
        String input = bufferedReader.readLine();
        int year = Integer.parseInt(input);
        System.out.print(menuPointer + "Enter group number: ");
        input = bufferedReader.readLine();
        int groupNumber = Integer.parseInt(input);
        return new Student(name, surname, year, groupNumber);
    }

    public static Teacher getTeacher(BufferedReader bufferedReader, String menuPointer) throws IOException {
        System.out.print(menuPointer + "Enter name: ");
        String name = bufferedReader.readLine();
        System.out.print(menuPointer + "Enter surname: ");
        String surname = bufferedReader.readLine();
        System.out.print(menuPointer + "Enter groups as 'g1,g2,g3...': ");
        String groupsString = bufferedReader.readLine();
        int[] groups = Arrays.stream(groupsString.split(",")).mapToInt(Integer::parseInt).toArray();
        return new Teacher(name, surname, groups);
    }

    public static void handleStudentTeacherMenu(BufferedReader bufferedReader, University university) throws Exception {
        String addCommand = "add";
        String editCommand = "edit";
        String removeCommand = "remove";
        String exitCommand = "exit";
        String menuPointer = "(student/teacher menu)";
        String input;
        while (true) {
            System.out.print(menuPointer + CHOICE_STRING);
            input = bufferedReader.readLine();
            if (input.equals("help")) {
                System.out.println("'" + addCommand + "' for add student/teacher\n'" + editCommand + "' for edit student/teacher\n'" + removeCommand + "' for remove student/teacher\n'" + exitCommand + "' for exit");
            } else if (input.equals(addCommand)) {
                Pair<Integer, Integer> pair = getFacultyAndCathedraIndexes(bufferedReader, university, menuPointer);
                int facultyIndex = pair.getKey();
                int cathedraIndex = pair.getValue();
                boolean isStudent = chooseStudentTeacher(bufferedReader, menuPointer);
                if (isStudent) {
                    university.addStudentToCathedra(facultyIndex, cathedraIndex, getStudent(bufferedReader, menuPointer));
                } else {
                    university.addTeacherToCathedra(facultyIndex, cathedraIndex, getTeacher(bufferedReader, menuPointer));
                }
            } else if (input.equals(editCommand)) {
                Pair<Integer, Integer> pair = getFacultyAndCathedraIndexes(bufferedReader, university, menuPointer);
                int facultyIndex = pair.getKey();
                int cathedraIndex = pair.getValue();
                boolean isStudent = chooseStudentTeacher(bufferedReader, menuPointer);
                if (isStudent) {
                    System.out.println(university.getListOfStudentsFromCathedra(facultyIndex, cathedraIndex));
                    System.out.print(menuPointer + "Enter student index: ");
                    input = bufferedReader.readLine();
                    int studentIndex = Integer.parseInt(input);
                    Student student = getStudent(bufferedReader, menuPointer);
                    university.removeStudentFromCathedra(facultyIndex, cathedraIndex, studentIndex);
                    university.addStudentToCathedra(facultyIndex, cathedraIndex, student);
                } else {
                    System.out.print(university.getListOfTeachersFromCathedra(facultyIndex, cathedraIndex));
                    System.out.print(menuPointer + "Enter teacher index: ");
                    input = bufferedReader.readLine();
                    int teacherIndex = Integer.parseInt(input);
                    Teacher teacher = getTeacher(bufferedReader, menuPointer);
                    university.removeTeacherFromCathedra(facultyIndex, cathedraIndex, teacherIndex);
                    university.addTeacherToCathedra(facultyIndex, cathedraIndex, teacher);
                }
            } else if (input.equals(removeCommand)) {
                Pair<Integer, Integer> pair = getFacultyAndCathedraIndexes(bufferedReader, university, menuPointer);
                int facultyIndex = pair.getKey();
                int cathedraIndex = pair.getValue();
                boolean isStudent = chooseStudentTeacher(bufferedReader, menuPointer);
                if (isStudent) {
                    System.out.println(university.getListOfStudentsFromCathedra(facultyIndex, cathedraIndex));
                    System.out.print(menuPointer + "Enter student index: ");
                    input = bufferedReader.readLine();
                    int index = Integer.parseInt(input);
                    university.removeStudentFromCathedra(facultyIndex, cathedraIndex, index);
                } else {
                    System.out.println(university.getListOfTeachersFromCathedra(facultyIndex, cathedraIndex));
                    System.out.print(menuPointer + "Enter teacher index: ");
                    input = bufferedReader.readLine();
                    int index = Integer.parseInt(input);
                    university.removeTeacherFromCathedra(facultyIndex, cathedraIndex, index);
                }
            } else if (input.equals(exitCommand)) {
                break;
            } else {
                throw new Exception();
            }
        }
    }
}

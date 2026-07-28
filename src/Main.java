import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static char getLetterGrade(int grade) {
        if (grade >= 85) return 'A';
        else if (grade >= 75) return 'B';
        else if (grade >= 65) return 'C';
        else if (grade >= 50) return 'D';
        else return 'F';
    }

    public static void displayStudentNames(String[] studentNames){
        for (int i = 0 ; i < studentNames.length ; i++)
            System.out.println(studentNames[i]);
    }

    public static void displayStudentGrades(String[] studentNames , int[][] studentGrades){
        for (int i = 0; i < studentGrades.length; i++) {
            System.out.println(studentNames[i] + "'s grades:");
            for (int j = 0; j < studentGrades[i].length; j++) {
                System.out.println("  Subject " + (j + 1) + ": " + studentGrades[i][j] + " (" + getLetterGrade(studentGrades[i][j]) + ")");
            }
        }
    }

    public static void findStudent(String searchTerm , String[] studentNames , int[][] studentGrades){
        boolean found = false;
        for (int i = 0; i < studentNames.length; i++) {
            if (studentNames[i].equalsIgnoreCase(searchTerm)){
                System.out.println(studentNames[i] + "'s grades:");
                for (int j = 0; j < studentGrades[i].length; j++) {
                    System.out.println("  Subject " + (j + 1) + ": " + studentGrades[i][j] + " (" + getLetterGrade(studentGrades[i][j]) + ")");
                }
                found = true;
            }
        }
        if (!found){
            System.out.println("Student not found");
        }
    }

    public static int countPassedStudents(int[][] studentGrades){
        int passedCount = 0;
        for (int i = 0; i < studentGrades.length; i++) {
            int sum = 0;
            for (int j = 0; j < studentGrades[i].length; j++) {
                sum += studentGrades[i][j];
            }
            double average = sum / (double) 3;
            if (average >= 50) {
                passedCount++;
            }
        }
        return passedCount;
    }

    public static void displaySubjectsAverageGrades(int[][] studentsGrades){
        int studentsNumber = 5;
        int subjectsNumber = 3;
        for (int i = 0 ; i < subjectsNumber ; i++){
            int sum = 0;
            for (int j = 0 ; j < studentsNumber ; j++){
                sum += studentsGrades[j][i];
            }
            double average = sum / (double) studentsNumber;
            System.out.println("Subject " + (i + 1) + " average: " + average);
        }
    }

    public static void displayMaxGradePerSubject(int[][] studentGrades){
        for (int i = 0 ; i < 3 ; i++){
            int max = studentGrades[0][i];
            for (int j = 0 ; j < 5 ; j++){
                if (studentGrades[j][i] > max)
                    max = studentGrades[j][i];
            }
            System.out.println("Subject " + (i+1) + " max grade is: " +max);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] studentNames = new String[5];
        int[][] studentGrades = new int[5][3];

        System.out.println("=== Student grade management system ===");
        System.out.println("Please enter each student name and his grades in order");

        for (int i = 0 ; i < 5 ; i++){
            while (true){
                System.out.println("Please enter the name");
                String name = sc.nextLine();

                if (name.matches("[a-zA-Z]+")){
                    studentNames[i] = name;
                    break;
                }else{
                    System.out.println("Please enter a valid name");
                }
            }

            System.out.println("Please enter student's grades");

            for (int j = 0 ; j < 3 ; j++){
                while(true){
                    if (sc.hasNextInt()){
                        int grade = sc.nextInt();
                        if (grade >= 0 && grade<= 100 ){
                            studentGrades[i][j] = grade;
                            break;
                        }else{
                            System.out.println("Please enter a valid grade");
                        }
                    }else{
                        System.out.println("Please enter a number");
                        sc.next();
                    }
                }
            }
            sc.nextLine();
        }

        int userChoice;
        do {
            System.out.println("""
                    --- Main Menu ---
                        1. Show All Students names.
                        2. Show all Students grades in each subject.
                        3. Search Student by name.
                        4. Count Passed Students
                        0. Exit
                    """);
            while(true){
                if (sc.hasNextInt()){
                    int choice = sc.nextInt();
                    if (choice >= 0 && choice <= 4){
                        userChoice = choice ;
                        break;
                    }else {
                        System.out.println("Please choose a valid choice");
                    }
                }else {
                    System.out.println("Please choose a number");
                    sc.next();
                }
            }
            switch (userChoice){
                case 1 ->{
                    displayStudentNames(studentNames);
                }
                case 2 -> {
                    displayStudentGrades(studentNames, studentGrades);
                }
                case 3 ->{
                    sc.nextLine();
                    String term;
                    while (true) {
                        System.out.println("Please enter the name you want to search for");
                        term = sc.nextLine();
                        if (term.matches("[a-zA-Z]+")) {
                            break;
                        } else {
                            System.out.println("Please enter a valid name");
                        }
                    }
                    findStudent(term,studentNames,studentGrades);
                }
                case 4 -> {
                    int passedStudents = countPassedStudents(studentGrades);
                    System.out.println("Number of passed students: " + passedStudents);
                }
                case 0 -> {
                    System.out.println("Thank You, Goodbye");
                }
            }

        }while (userChoice != 0);
        displaySubjectsAverageGrades(studentGrades);
        displayMaxGradePerSubject(studentGrades);
    }
}
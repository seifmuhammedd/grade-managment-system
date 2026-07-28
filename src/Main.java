import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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
                    for (int i = 0 ; i < studentNames.length ; i++)
                        System.out.println(studentNames[i]);
                }
                case 2 ->{
                        for (int i = 0; i < studentGrades.length; i++) {
                            System.out.println(studentNames[i] + "'s grades:");
                            for (int j = 0; j < studentGrades[i].length; j++) {
                                System.out.println("  Subject " + (j + 1) + ": " + studentGrades[i][j]);
                            }
                        }
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
                    boolean found = false;
                    for (int i = 0; i < studentNames.length; i++) {
                        if (studentNames[i].equalsIgnoreCase(term)){
                            System.out.println(studentNames[i] + "'s grades:");
                            for (int j = 0; j < studentGrades[i].length; j++) {
                                System.out.println("  Subject " + (j + 1) + ": " + studentGrades[i][j]);
                            }
                            found = true;
                        }
                    }
                    if (!found){
                        System.out.println("Student not found");
                    }
                }
                case 4 -> {
                    int passedCount = 0;
                    for (int i = 0; i < studentGrades.length; i++) {
                        int sum = 0;
                        for (int j = 0; j < studentGrades[i].length; j++) {
                            sum += studentGrades[i][j];
                        }
                        double average = sum / 3;
                        if (average >= 50) {
                            passedCount++;
                        }
                    }
                    System.out.println("Number of passed students: " + passedCount);
                }
                case 0 -> {
                    System.out.println("Thank You, Goodbye");
                }
            }

        }while (userChoice != 0);
    }
}
import java.util.Scanner;

public class StudentManagementSystem {

    static class Student {

        int studentID;
        String name;
        int age;
        String department;

        double[] marks;
        double total;
        double average;
        String grade;
        String status;

        // Constructor
        Student(int studentID, String name, int age,
                String department, double[] marks) {

            this.studentID = studentID;
            this.name = name;
            this.age = age;
            this.department = department;
            this.marks = marks;

            // Calculate total
            for (int i = 0; i < marks.length; i++) {
                total += marks[i];
            }

            // Calculate average
            average = total / marks.length;

            // Determine grade and status
            if (average >= 80) {
                grade = "A";
                status = "Passed";
            } else if (average >= 70) {
                grade = "B";
                status = "Passed";
            } else if (average >= 60) {
                grade = "C";
                status = "Passed";
            } else if (average >= 50) {
                grade = "D";
                status = "Passed";
            } else {
                grade = "F";
                status = "Failed";
            }
        }

        // Display student information
        public void displayStudent() {

            System.out.println("\n----- Student Information -----");

            System.out.println("Student ID: " + studentID);
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Department: " + department);

            System.out.println("Marks:");

            for (int i = 0; i < marks.length; i++) {
                System.out.println(
                    "Subject " + (i + 1) + ": " + marks[i]
                );
            }

            System.out.println("Total: " + total);
            System.out.println("Average: " + average);
            System.out.println("Grade: " + grade);
            System.out.println("Status: " + status);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("How many students do you want to register? ");
        int numberOfStudents = scanner.nextInt();

        Student[] students = new Student[numberOfStudents];

        for (int i = 0; i < numberOfStudents; i++) {

            System.out.println("\n===== Register Student " + (i + 1) + " =====");

            // Student ID
            System.out.print("Enter Student ID: ");
            int studentID = scanner.nextInt();

            scanner.nextLine();

            // Name
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            // Age validation
            int age;

            do {
                System.out.print("Enter Student Age (16 - 60): ");
                age = scanner.nextInt();

                if (age < 16 || age > 60) {
                    System.out.println(
                        "Invalid age. Age must be between 16 and 60."
                    );
                }

            } while (age < 16 || age > 60);

            scanner.nextLine();

            // Department
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();

            // Five subjects
            double[] marks = new double[5];

            for (int j = 0; j < 5; j++) {

                do {
                    System.out.print(
                        "Enter marks for Subject " + (j + 1) + " (0 - 100): "
                    );

                    marks[j] = scanner.nextDouble();

                    if (marks[j] < 0 || marks[j] > 100) {
                        System.out.println(
                            "Invalid mark. Mark must be between 0 and 100."
                        );
                    }

                } while (marks[j] < 0 || marks[j] > 100);
            }

            // Create Student object using constructor
            students[i] = new Student(
                studentID,
                name,
                age,
                department,
                marks
            );
        }

        // Display all students
        System.out.println("\n\n---------ALL REGISTERED STUDENTS---------");

        for (int i = 0; i < students.length; i++) {
            students[i].displayStudent();
        }

        scanner.close();
    }
}
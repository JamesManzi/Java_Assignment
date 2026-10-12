
import java.util.Scanner;

// Employee class
class Employee {
    int employeeId;
    String name;
    String department;
    double basicSalary;
    double hoursWorked;
    double overtimeHours;

    double overtimePay;
    double grossSalary;
    double tax;
    double netSalary;

    // Constructor
    public Employee(int employeeId, String name, String department,
                    double basicSalary, double hoursWorked) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
        this.hoursWorked = hoursWorked;

        // Calculate overtime hours
        if (hoursWorked > 160) {
            overtimeHours = hoursWorked - 160;
        } else {
            overtimeHours = 0;
        }

        // Overtime rate = 1.5 times the normal hourly rate
        double hourlyRate = basicSalary / 160;
        overtimePay = overtimeHours * hourlyRate * 1.5;

        // Calculate gross salary
        grossSalary = basicSalary + overtimePay;

        // Calculate tax using salary ranges
        if (grossSalary <= 300000) {
            tax = grossSalary * 0.05;
        } else if (grossSalary <= 600000) {
            tax = grossSalary * 0.10;
        } else if (grossSalary <= 1000000) {
            tax = grossSalary * 0.15;
        } else {
            tax = grossSalary * 0.20;
        }

        // Calculate net salary
        netSalary = grossSalary - tax;
    }

    // Display employee payroll
    public void displayPayroll() {
        System.out.println("----------------------------------------");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.printf("Basic Salary: %.2f%n", basicSalary);
        System.out.printf("Hours Worked: %.2f%n", hoursWorked);
        System.out.printf("Overtime Hours: %.2f%n", overtimeHours);
        System.out.printf("Overtime Pay: %.2f%n", overtimePay);
        System.out.printf("Gross Salary: %.2f%n", grossSalary);
        System.out.printf("Tax: %.2f%n", tax);
        System.out.printf("Net Salary: %.2f%n", netSalary);
    }
}

// Main class
public class EmployeePayrollSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int numberOfEmployees;

        // Validate number of employees
        do {
            System.out.print("How many employees do you want to register? ");
            while (!input.hasNextInt()) {
                System.out.print("Enter a valid whole number: ");
                input.next();
            }
            numberOfEmployees = input.nextInt();

            if (numberOfEmployees <= 0) {
                System.out.println("Number of employees must be greater than zero.");
            }
        } while (numberOfEmployees <= 0);

        input.nextLine(); // Clear input buffer

        // Create an array to store employee objects
        Employee[] employees = new Employee[numberOfEmployees];

        // Register employees
        for (int i = 0; i < numberOfEmployees; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1));

            System.out.print("Employee ID: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.print("Employee Name: ");
            String name = input.nextLine();

            System.out.print("Department: ");
            String department = input.nextLine();

            double salary;

            // Validate basic salary
            do {
                System.out.print("Basic Salary: ");

                while (!input.hasNextDouble()) {
                    System.out.print("Enter a valid salary: ");
                    input.next();
                }

                salary = input.nextDouble();

                if (salary <= 0) {
                    System.out.println("Salary must be greater than zero.");
                }
            } while (salary <= 0);

            double hours;

            // Validate working hours
            do {
                System.out.print("Total Hours Worked (0-744): ");

                while (!input.hasNextDouble()) {
                    System.out.print("Enter valid working hours: ");
                    input.next();
                }

                hours = input.nextDouble();

                if (hours < 0 || hours > 744) {
                    System.out.println(
                        "Hours must be between 0 and 744."
                    );
                }
            } while (hours < 0 || hours > 744);

            // Create employee object using constructor
            employees[i] = new Employee(
                id, name, department, salary, hours
            );

            input.nextLine(); // Clear input buffer
        }

        // Display all payroll information
        System.out.println("\n========== EMPLOYEE PAYROLL REPORT ==========");

        for (int i = 0; i < employees.length; i++) {
            employees[i].displayPayroll();
        }

        // Find employee with the highest net salary
        Employee highestPaid = employees[0];

        for (int i = 1; i < employees.length; i++) {
            if (employees[i].netSalary > highestPaid.netSalary) {
                highestPaid = employees[i];
            }
        }

        System.out.println("\n========== HIGHEST NET SALARY ==========");
        System.out.println("Employee Name: " + highestPaid.name);
        System.out.println("Employee ID: " + highestPaid.employeeId);
        System.out.printf(
            "Net Salary: %.2f%n", highestPaid.netSalary
        );

        input.close();
    }
}

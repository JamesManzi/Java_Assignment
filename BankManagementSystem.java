import java.util.Scanner;

public class BankManagementSystem {

    // BankAccount class

    static class BankAccount {

        // Attributes
        private int accountNumber;
        private String accountHolderName;
        private int pin;
        private double balance;

        // Constructor
        BankAccount(int accountNumber, String accountHolderName,
                    int pin, double balance) {

            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.pin = pin;
            this.balance = balance;
        }

        // Check balance
        public void checkBalance() {
            System.out.println("Current Balance: " + balance);
        }

        // Deposit money
        public void deposit(double amount) {

            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit successful.");
                System.out.println("New Balance: " + balance);
            } else {
                System.out.println(
                    "Deposit amount must be greater than zero."
                );
            }
        }

        // Withdraw money
        public void withdraw(double amount) {

            if (amount <= 0) {

                System.out.println(
                    "Withdrawal amount must be greater than zero."
                );

            } else if (amount > balance) {

                System.out.println(
                    "Insufficient balance."
                );

            } else {

                balance -= amount;

                System.out.println("Withdrawal successful.");
                System.out.println("New Balance: " + balance);
            }
        }

        // Check whether PIN is correct
        public boolean authenticate(int enteredPin) {
            return enteredPin == pin;
        }

        // Display account information
        public void displayAccountInformation() {

            System.out.println("\n---------- ACCOUNT INFORMATION -----------");

            System.out.println(
                "Account Number: " + accountNumber
            );

            System.out.println(
                "Account Holder: " + accountHolderName
            );

            System.out.println(
                "Balance: " + balance
            );
        }
    }

    // Main method
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        
        // Account Number


        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        scanner.nextLine();

        
        // Account Holder Name
        

        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.nextLine();

        
        // PIN Validation
        

        int pin;

        do {

            System.out.print("Create a 4-digit PIN: ");
            pin = scanner.nextInt();

            if (pin < 1000 || pin > 9999) {

                System.out.println(
                    "Invalid PIN. PIN must contain exactly 4 digits."
                );
            }

        } while (pin < 1000 || pin > 9999);

        
        // Initial Balance Validation
       

        double balance;

        do {

            System.out.print("Enter Initial Balance: ");
            balance = scanner.nextDouble();

            if (balance <= 0) {

                System.out.println(
                    "Initial balance must be greater than zero."
                );
            }

        } while (balance <= 0);

       
        // Create Bank Account Object
        

        BankAccount account = new BankAccount(
            accountNumber,
            accountHolderName,
            pin,
            balance
        );

        System.out.println(
            "\nBank account created successfully!"
        );

        
        // PIN Authentication
       

        boolean authenticated = false;

        for (int attempt = 1; attempt <= 3; attempt++) {

            System.out.print(
                "\nEnter your PIN: "
            );

            int enteredPin = scanner.nextInt();

            if (account.authenticate(enteredPin)) {

                authenticated = true;

                System.out.println(
                    "Authentication successful!"
                );

                break;

            } else {

                System.out.println(
                    "Incorrect PIN."
                );

                System.out.println(
                    "Attempts remaining: " + (3 - attempt)
                );
            }
        }

       
        // Check Authentication
        

        if (authenticated) {

            int choice;

           
            // Banking Menu
            
            do {

                System.out.println(
                    "\n------------ BANK MENU -------------"
                );

                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Account Information");
                System.out.println("5. Exit");

                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                
                // Check Balance
                

                if (choice == 1) {

                    account.checkBalance();

                }

               
                // Deposit
                

                else if (choice == 2) {

                    System.out.print(
                        "Enter deposit amount: "
                    );

                    double amount = scanner.nextDouble();

                    account.deposit(amount);
                }

                
                // Withdraw

                else if (choice == 3) {

                    System.out.print(
                        "Enter withdrawal amount: "
                    );

                    double amount = scanner.nextDouble();

                    account.withdraw(amount);
                }

                
                // Account Information
                

                else if (choice == 4) {

                    account.displayAccountInformation();

                }

               
                // Exit
                

                else if (choice == 5) {

                    System.out.println(
                        "Thank you for using our bank."
                    );

                }

                
                // Invalid Menu Option
                
                else {

                    System.out.println(
                        "Invalid option. Please choose 1-5."
                    );
                }

            } while (choice != 5);

        } else {

            System.out.println(
                "\nAccount locked. Maximum PIN attempts exceeded."
            );
        }

        scanner.close();
    }
}
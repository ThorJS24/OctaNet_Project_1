import java.util.ArrayList;
import java.util.Scanner;

public class ATMMachine {
    // Initial balance for the account
    private static double balance = 1000.00;

    // Default PIN for the ATM card
    private static int pin = 1234;

    // Stores the transaction history
    private static ArrayList<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the ATM Machine");

        // Main loop to keep the ATM menu active until the user exits
        while (!exit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. PIN Change");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            // Switch case to handle user selections
            switch (choice) {
                case 1:
                    balanceInquiry(); // Call function to check account balance
                    break;
                case 2:
                    cashWithdrawal(scanner); // Call function to withdraw cash
                    break;
                case 3:
                    cashDeposit(scanner); // Call function to deposit cash
                    break;
                case 4:
                    pinChange(scanner); // Call function to change PIN
                    break;
                case 5:
                    showTransactionHistory(); // Call function to display transaction history
                    break;
                case 6:
                    exit = true; // Exit the loop and terminate the program
                    System.out.println("Thank you for using the ATM Machine.");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }

        scanner.close(); // Close the scanner to prevent resource leaks
    }

    // Function to display the current account balance
    private static void balanceInquiry() {
        System.out.println("Your current balance is: $" + balance);
        // Record the balance inquiry in the transaction history
        transactionHistory.add("Balance Inquiry: $" + balance);
    }

    // Function to withdraw cash from the account
    private static void cashWithdrawal(Scanner scanner) {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        // Check if there is sufficient balance to withdraw
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount; // Deduct the amount from the balance
            System.out.println("Please collect your cash: $" + amount);
            // Record the withdrawal in the transaction history
            transactionHistory.add("Cash Withdrawal: $" + amount);
        }
    }

    // Function to deposit cash into the account
    private static void cashDeposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        balance += amount; // Add the deposited amount to the balance
        System.out.println("Amount deposited: $" + amount);
        // Record the deposit in the transaction history
        transactionHistory.add("Cash Deposit: $" + amount);
    }

    // Function to change the PIN
    private static void pinChange(Scanner scanner) {
        System.out.print("Enter current PIN: ");
        int currentPin = scanner.nextInt();

        // Verify the current PIN before allowing a change
        if (currentPin == pin) {
            System.out.print("Enter new PIN: ");
            int newPin = scanner.nextInt();
            pin = newPin; // Update the PIN
            System.out.println("PIN successfully changed.");
            // Record the PIN change in the transaction history
            transactionHistory.add("PIN Change");
        } else {
            System.out.println("Incorrect current PIN.");
        }
    }

    // Function to display all transactions in the transaction history
    private static void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("Transaction History:");
            // Loop through the transaction history and display each transaction
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

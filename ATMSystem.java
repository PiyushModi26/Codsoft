import java.util.Scanner;

class ATM_INTERFACE {
    private double balance;

    public ATM_INTERFACE(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        } else if (amount > balance) {
            System.out.println("Insufficient balance for withdrawal.");
            return false;
        }
        balance -= amount;
        System.out.println("Withdrawal of Rs. " + amount + " successful.");
        return true;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        balance += amount;
        System.out.println("Deposit of Rs. " + amount + " successful.");
        return true;
    }

    public double getBalance() {
        return balance;
    }
}

// Class representing the ATM machine interface
class ATM {
    private ATM_INTERFACE account;
    private Scanner scanner;

    public ATM(ATM_INTERFACE account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    handleWithdraw();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleCheckBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
            System.out.println(); // Add spacing between transactions
        }
    }

    private void showMenu() {
        System.out.println("======= ATM Menu =======");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // invalid input
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = getAmountInput();
        account.withdraw(amount);
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = getAmountInput();
        account.deposit(amount);
    }

    private void handleCheckBalance() {
        System.out.printf("Current balance: Rs. %.2f\n", account.getBalance());
    }

    private double getAmountInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return 0;
        }
    }
}

// Main class to run the ATM program
public class ATMSystem {
    public static void main(String[] args) {
        // You can modify the initial balance if needed
        ATM_INTERFACE userAccount = new ATM_INTERFACE(1000.00);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}

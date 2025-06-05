import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATMData {
    private String accountNumber;
    private int pin;
    private double balance;
    private List<String> transactionHistory;

    public ATMData(String accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public void checkBalance(int enteredPin) {
        if (enteredPin == this.pin) {
            System.out.println("Account Balance: $" + this.balance);
        } else {
            System.out.println("Invalid PIN");
        }
    }

    public void withdrawCash(int enteredPin, double amount) {
        if (enteredPin == this.pin) {
            if (amount > 0 && amount <= this.balance) {
                this.balance -= amount;
                this.transactionHistory.add("Withdrawal: -$" + amount);
                System.out.println("Withdrawal Successful. New Balance: $" + this.balance);
            } else if (amount <= 0) {
                System.out.println("Invalid withdrawal amount");
            } else {
                System.out.println("Insufficient balance");
            }
        } else {
            System.out.println("Invalid PIN");
        }
    }

    public void depositCash(int enteredPin, double amount) {
        if (enteredPin == this.pin) {
            if (amount > 0) {
                this.balance += amount;
                this.transactionHistory.add("Deposit: +$" + amount);
                System.out.println("Deposit Successful. New Balance: $" + this.balance);
            } else {
                System.out.println("Invalid deposit amount");
            }
        } else {
            System.out.println("Invalid PIN");
        }
    }

    public void changePin(int oldPin, int newPin) {
        if (oldPin == this.pin) {
            this.pin = newPin;
            System.out.println("PIN changed successfully");
        } else {
            System.out.println("Invalid old PIN");
        }
    }

    public void viewTransactionHistory(int enteredPin) {
        if (enteredPin == this.pin) {
            if (transactionHistory.isEmpty()) {
                System.out.println("No transactions yet.");
            } else {
                for (String transaction : this.transactionHistory) {
                    System.out.println(transaction);
                }
            }
        } else {
            System.out.println("Invalid PIN");
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        ATMData atm = new ATMData("1234567890", 1234, 1000.0);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            int pin;
            double amount;

            switch (option) {
                case 1:
                    System.out.print("Enter PIN: ");
                    pin = scanner.nextInt();
                    atm.checkBalance(pin);
                    break;
                case 2:
                    System.out.print("Enter PIN: ");
                    pin = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    amount = scanner.nextDouble();
                    atm.withdrawCash(pin, amount);
                    break;
                case 3:
                    System.out.print("Enter PIN: ");
                    pin = scanner.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    amount = scanner.nextDouble();
                    atm.depositCash(pin, amount);
                    break;
                case 4:
                    System.out.print("Enter old PIN: ");
                    int oldPin = scanner.nextInt();
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    atm.changePin(oldPin, newPin);
                    break;
                case 5:
                    System.out.print("Enter PIN: ");
                    pin = scanner.nextInt();
                    atm.viewTransactionHistory(pin);
                    break;
                case 6:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}

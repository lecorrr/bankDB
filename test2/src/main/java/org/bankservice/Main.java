package org.bankservice;

import org.bankservice.persistence.EntityManagerFactoryProvider;
import org.bankservice.service.BankService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankService service = new BankService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== Bank Console =====");
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show balance");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            String opt = sc.nextLine().trim();

            try {
                switch (opt) {
                    case "1":
                        System.out.print("Account number: ");
                        String accNo = sc.nextLine().trim();
                        System.out.print("Owner name: ");
                        String owner = sc.nextLine().trim();
                        System.out.print("Initial balance: ");
                        double initBal = Double.parseDouble(sc.nextLine().trim());
                        service.createAccount(accNo, owner, initBal);
                        System.out.println("Account created.");
                        break;
                    case "2":
                        System.out.print("Account number: ");
                        accNo = sc.nextLine().trim();
                        System.out.print("Currency (USD, EUR, UAH): ");
                        String currencyInput = sc.nextLine().trim().toUpperCase();
                        Currency currency = Currency.valueOf(currencyInput);
                        System.out.print("Amount: ");
                        double dep = Double.parseDouble(sc.nextLine().trim());
                        service.deposit(accNo, dep, currency);
                        System.out.println("Deposit successful.");
                        break;
                    case "3":
                        System.out.print("Account number: ");
                        accNo = sc.nextLine().trim();
                        System.out.print("Amount: ");
                        double wd = Double.parseDouble(sc.nextLine().trim());
                        service.withdraw(accNo, wd);
                        System.out.println("Withdrawal successful.");
                        break;
                    case "4":
                        System.out.print("Account number: ");
                        accNo = sc.nextLine().trim();
                        double balance = service.getBalance(accNo);
                        System.out.println("Balance: " + balance);
                        break;
                    case "0":
                        System.out.println("Exiting...");
                        EntityManagerFactoryProvider.closeFactory();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }
    }
}

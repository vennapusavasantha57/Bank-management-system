package com.vasantha.bank;

import com.vasantha.bank.model.Account;
import com.vasantha.bank.model.Customer;
import com.vasantha.bank.service.BankService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static BankService bankService = new BankService();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Bank Management System ===");
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: createCustomerAndAccount(); break;
                case 2: deposit(); break;
                case 3: withdraw(); break;
                case 4: transfer(); break;
                case 5: viewAccount(); break;
                case 0: exit = true; break;
                default: System.out.println("Invalid option"); break;
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n1. Create Customer & Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. View Account");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void createCustomerAndAccount() {
        System.out.print("Customer name: ");
        String name = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        Customer c = new Customer();
        c.setName(name); c.setAddress(address); c.setPhone(phone); c.setEmail(email); c.setKycStatus("PENDING");
        int customerId = bankService.createCustomer(c);
        if (customerId > 0) {
            System.out.print("Account type (SAVINGS/CURRENT/FD): ");
            String type = sc.nextLine();
            System.out.print("Initial deposit: ");
            BigDecimal deposit = new BigDecimal(sc.nextLine());
            long accNo = bankService.createAccount(customerId, type.toUpperCase(), deposit);
            System.out.println("Created account number: " + accNo);
        } else {
            System.out.println("Failed to create customer.");
        }
    }

    private static void deposit() {
        System.out.print("Account number: ");
        long acc = Long.parseLong(sc.nextLine());
        System.out.print("Amount: ");
        BigDecimal amt = new BigDecimal(sc.nextLine());
        if (bankService.deposit(acc, amt)) System.out.println("Deposit successful.");
        else System.out.println("Deposit failed.");
    }

    private static void withdraw() {
        System.out.print("Account number: ");
        long acc = Long.parseLong(sc.nextLine());
        System.out.print("Amount: ");
        BigDecimal amt = new BigDecimal(sc.nextLine());
        if (bankService.withdraw(acc, amt)) System.out.println("Withdrawal successful.");
        else System.out.println("Withdrawal failed (insufficient funds or error).");
    }

    private static void transfer() {
        System.out.print("From account: ");
        long from = Long.parseLong(sc.nextLine());
        System.out.print("To account: ");
        long to = Long.parseLong(sc.nextLine());
        System.out.print("Amount: ");
        BigDecimal amt = new BigDecimal(sc.nextLine());
        if (bankService.transfer(from, to, amt)) System.out.println("Transfer successful.");
        else System.out.println("Transfer failed.");
    }

    private static void viewAccount() {
        System.out.print("Account number: ");
        long acc = Long.parseLong(sc.nextLine());
        Account a = bankService.getAccount(acc);
        if (a != null) System.out.println(a);
        else System.out.println("Account not found.");
    }
}

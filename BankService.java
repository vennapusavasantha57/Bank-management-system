package com.vasantha.bank.service;

import com.vasantha.bank.dao.AccountDAO;
import com.vasantha.bank.dao.CustomerDAO;
import com.vasantha.bank.dao.TransactionDAO;
import com.vasantha.bank.model.Account;
import com.vasantha.bank.model.Customer;
import com.vasantha.bank.model.TransactionRecord;

import java.math.BigDecimal;

public class BankService {
    private CustomerDAO customerDAO = new CustomerDAO();
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    public int createCustomer(Customer c) {
        return customerDAO.createCustomer(c);
    }

    public long createAccount(int customerId, String accountType, BigDecimal initialDeposit) {
        long accountNo = accountDAO.createAccount(customerId, accountType, initialDeposit);
        if (accountNo > 0) {
            TransactionRecord t = new TransactionRecord(accountNo, "DEPOSIT", initialDeposit, "Initial deposit");
            transactionDAO.addTransaction(t);
        }
        return accountNo;
    }

    public boolean deposit(long accountNo, BigDecimal amount) {
        Account acc = accountDAO.getAccount(accountNo);
        if (acc == null) return false;
        BigDecimal newBalance = acc.getBalance().add(amount);
        boolean ok = accountDAO.updateBalance(accountNo, newBalance);
        if (ok) {
            transactionDAO.addTransaction(new TransactionRecord(accountNo, "DEPOSIT", amount, "Deposit"));
        }
        return ok;
    }

    public boolean withdraw(long accountNo, BigDecimal amount) {
        Account acc = accountDAO.getAccount(accountNo);
        if (acc == null) return false;
        if (acc.getBalance().compareTo(amount) < 0) return false;
        BigDecimal newBalance = acc.getBalance().subtract(amount);
        boolean ok = accountDAO.updateBalance(accountNo, newBalance);
        if (ok) {
            transactionDAO.addTransaction(new TransactionRecord(accountNo, "WITHDRAW", amount, "Withdrawal"));
        }
        return ok;
    }

    public boolean transfer(long fromAcc, long toAcc, BigDecimal amount) {
        Account aFrom = accountDAO.getAccount(fromAcc);
        Account aTo = accountDAO.getAccount(toAcc);
        if (aFrom == null || aTo == null) return false;
        if (aFrom.getBalance().compareTo(amount) < 0) return false;

        boolean ok1 = accountDAO.updateBalance(fromAcc, aFrom.getBalance().subtract(amount));
        boolean ok2 = accountDAO.updateBalance(toAcc, aTo.getBalance().add(amount));
        if (ok1 && ok2) {
            transactionDAO.addTransaction(new TransactionRecord(fromAcc, "TRANSFER", amount, "Transfer to " + toAcc));
            transactionDAO.addTransaction(new TransactionRecord(toAcc, "TRANSFER", amount, "Transfer from " + fromAcc));
            return true;
        } else {
            // In a real system rollback or transaction management would be needed.
            return false;
        }
    }

    public Account getAccount(long accountNo) {
        return accountDAO.getAccount(accountNo);
    }

    public Customer getCustomer(int id) {
        return customerDAO.findById(id);
    }
}

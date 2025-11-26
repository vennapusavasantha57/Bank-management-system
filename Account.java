package com.vasantha.bank.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Account {
    private long accountNo;
    private int customerId;
    private String accountType;
    private BigDecimal balance;
    private Timestamp createdAt;

    public Account() {}

    public Account(long accountNo, int customerId, String accountType, BigDecimal balance) {
        this.accountNo = accountNo; this.customerId = customerId;
        this.accountType = accountType; this.balance = balance;
    }

    // getters & setters
    public long getAccountNo() { return accountNo; }
    public void setAccountNo(long accountNo) { this.accountNo = accountNo; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo=" + accountNo +
                ", customerId=" + customerId +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", createdAt=" + createdAt +
                '}';
    }
}

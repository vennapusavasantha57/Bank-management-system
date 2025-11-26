package com.vasantha.bank.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionRecord {
    private long txnId;
    private long accountNo;
    private String txnType;
    private BigDecimal amount;
    private Timestamp txnDate;
    private String remarks;

    public TransactionRecord() {}

    public TransactionRecord(long accountNo, String txnType, BigDecimal amount, String remarks) {
        this.accountNo = accountNo; this.txnType = txnType;
        this.amount = amount; this.remarks = remarks;
    }

    // getters & setters
    public long getTxnId() { return txnId; }
    public void setTxnId(long txnId) { this.txnId = txnId; }
    public long getAccountNo() { return accountNo; }
    public void setAccountNo(long accountNo) { this.accountNo = accountNo; }
    public String getTxnType() { return txnType; }
    public void setTxnType(String txnType) { this.txnType = txnType; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public Timestamp getTxnDate() { return txnDate; }
    public void setTxnDate(Timestamp txnDate) { this.txnDate = txnDate; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    @Override
    public String toString() {
        return "Transaction{" +
                "txnId=" + txnId +
                ", accountNo=" + accountNo +
                ", txnType='" + txnType + '\'' +
                ", amount=" + amount +
                ", txnDate=" + txnDate +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

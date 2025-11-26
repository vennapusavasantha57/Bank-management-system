package com.vasantha.bank.dao;

import com.vasantha.bank.model.Account;
import com.vasantha.bank.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

public class AccountDAO {

    public long createAccount(int customerId, String accountType, BigDecimal initialDeposit) {
        String sql = "INSERT INTO account (account_no, customer_id, account_type, balance) VALUES (?, ?, ?, ?)";
        long accountNo = generateAccountNumber();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, accountNo);
            ps.setInt(2, customerId);
            ps.setString(3, accountType);
            ps.setBigDecimal(4, initialDeposit);
            int affected = ps.executeUpdate();
            if (affected > 0) return accountNo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private long generateAccountNumber() {
        Random rnd = new Random();
        long num = 100000000000L + (long)(rnd.nextDouble() * 899999999999L);
        return num;
    }

    public Account getAccount(long accountNo) {
        String sql = "SELECT * FROM account WHERE account_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, accountNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account a = new Account();
                    a.setAccountNo(rs.getLong("account_no"));
                    a.setCustomerId(rs.getInt("customer_id"));
                    a.setAccountType(rs.getString("account_type"));
                    a.setBalance(rs.getBigDecimal("balance"));
                    a.setCreatedAt(rs.getTimestamp("created_at"));
                    return a;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateBalance(long accountNo, BigDecimal newBalance) {
        String sql = "UPDATE account SET balance = ? WHERE account_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, newBalance);
            ps.setLong(2, accountNo);
            int updated = ps.executeUpdate();
            return updated > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}

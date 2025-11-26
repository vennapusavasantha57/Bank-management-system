package com.vasantha.bank.dao;

import com.vasantha.bank.model.TransactionRecord;
import com.vasantha.bank.util.DBConnection;

import java.sql.*;

public class TransactionDAO {

    public boolean addTransaction(TransactionRecord txn) {
        String sql = "INSERT INTO transaction_record (account_no, txn_type, amount, remarks) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, txn.getAccountNo());
            ps.setString(2, txn.getTxnType());
            ps.setBigDecimal(3, txn.getAmount());
            ps.setString(4, txn.getRemarks());
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}

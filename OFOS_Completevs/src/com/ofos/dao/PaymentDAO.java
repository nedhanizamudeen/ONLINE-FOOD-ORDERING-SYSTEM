package com.ofos.dao;

import com.ofos.model.Payment;
import com.ofos.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {

    public boolean recordPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO Payment (PaymentID, Order_ID, Payment_Method, Payment_Status, Transaction_ID) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getPaymentId());
            stmt.setInt(2, payment.getOrderId());
            stmt.setString(3, payment.getMethod());
            stmt.setString(4, payment.getStatus());
            stmt.setString(5, payment.getTransactionId());
            return stmt.executeUpdate() > 0;
        }
    }
}

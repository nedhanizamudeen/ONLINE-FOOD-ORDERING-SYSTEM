package com.ofos.dao;

import com.ofos.model.Order;
import com.ofos.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public int placeOrder(Order order) throws SQLException {
        String sql = "INSERT INTO Orders (UserID, Total_Amount, Order_Status, Order_Time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUserId());
            stmt.setDouble(2, order.getTotalAmount());
            stmt.setString(3, order.getOrderStatus());
            stmt.setTimestamp(4, order.getOrderTime());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) return -1;

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // return generated order ID
            }
        }
        return -1;
    }

    public List<Order> getUserOrders(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE UserID = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("Order_ID"),
                    rs.getInt("UserID"),
                    rs.getDouble("Total_Amount"),
                    rs.getString("Order_Status"),
                    rs.getTimestamp("Order_Time")
                ));
            }
        }
        return orders;
    }
}

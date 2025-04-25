package com.ofos.dao;

import com.ofos.model.OrderItem;
import com.ofos.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDAO {

    public boolean addOrderItem(OrderItem item) throws SQLException {
        String sql = "INSERT INTO OrderItems (Order_ID, Food_ID, Quantity, Price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getOrderId());
            stmt.setInt(2, item.getFoodId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            return stmt.executeUpdate() > 0;
        }
    }
}

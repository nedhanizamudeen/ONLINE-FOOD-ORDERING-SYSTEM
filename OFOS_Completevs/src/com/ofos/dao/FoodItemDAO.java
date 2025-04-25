package com.ofos.dao;

import com.ofos.model.FoodItem;
import com.ofos.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodItemDAO {

    public List<FoodItem> getAllAvailableItems() throws SQLException {
        List<FoodItem> items = new ArrayList<>();
        String sql = "SELECT * FROM FoodItem WHERE Availability = 'Y'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FoodItem item = new FoodItem(
                    rs.getInt("FoodID"),
                    rs.getString("Name"),
                    rs.getString("Cuisine_Type"),
                    rs.getDouble("Price"),
                    rs.getString("Availability").equalsIgnoreCase("Y")
                );
                items.add(item);
            }
        }
        return items;
    }
}

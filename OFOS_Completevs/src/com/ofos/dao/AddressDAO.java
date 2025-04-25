package com.ofos.dao;

import com.ofos.model.Address;
import com.ofos.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressDAO {

    public boolean insertAddress(Address address) throws SQLException {
        String sql = "INSERT INTO Address (UserID, Street_Address, PIN_Code, City, State) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, address.getUserId());
            stmt.setString(2, address.getStreetAddress());
            stmt.setString(3, address.getPinCode());
            stmt.setString(4, address.getCity());
            stmt.setString(5, address.getState());
            return stmt.executeUpdate() > 0;
        }
    }
}
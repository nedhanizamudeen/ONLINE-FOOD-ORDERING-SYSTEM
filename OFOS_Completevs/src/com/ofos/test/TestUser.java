package com.ofos.test;

import com.ofos.dao.UserDAO;
import com.ofos.model.User;
import java.sql.SQLException;

public class TestUser {
    public static void main(String[] args) {
        try {
        	User user = new User(111, "Simran", "simran@gmail.com", "1234");
            UserDAO dao = new UserDAO();
            boolean result = dao.registerUser(user);
            System.out.println(result ? "✅ User registered" : "❌ Registration failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.ofos.model;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int userId;
    private double totalAmount;
    private String orderStatus;
    private Timestamp orderTime;

    public Order(int orderId, int userId, double totalAmount, String orderStatus, Timestamp orderTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
    }

    public int getOrderId() { return orderId; }
    public int getUserId() { return userId; }
    public double getTotalAmount() { return totalAmount; }
    public String getOrderStatus() { return orderStatus; }
    public Timestamp getOrderTime() { return orderTime; }
}

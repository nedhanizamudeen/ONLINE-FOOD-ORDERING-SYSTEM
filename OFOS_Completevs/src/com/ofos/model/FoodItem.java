package com.ofos.model;

public class FoodItem {
    private int foodId;
    private String name;
    private String cuisineType;
    private double price;
    private boolean availability;

    public FoodItem(int foodId, String name, String cuisineType, double price, boolean availability) {
        this.foodId = foodId;
        this.name = name;
        this.cuisineType = cuisineType;
        this.price = price;
        this.availability = availability;
    }

    public int getFoodId() { return foodId; }
    public String getName() { return name; }
    public String getCuisineType() { return cuisineType; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return availability; }
}

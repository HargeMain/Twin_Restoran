package com.restoran.Orders.Food;

public class Burger extends Meal {
    public Burger(String name, double price, int calories) {
        super(name, price, calories);
    }

    @Override
    public void description() {
        System.out.println("American food\nMade with beef and bread");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public int getCalories() {
        return super.getCalories();
    }

    @Override
    public String getType() {
        return super.getType();
    }
}

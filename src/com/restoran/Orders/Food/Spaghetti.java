package com.restoran.Orders.Food;

public class Spaghetti extends Meal {
    public Spaghetti(String name, double price, int calories) {
        super(name, price, calories);
    }

    @Override
    public void description() {
        System.out.println("Italian beatifull food\nMade with beef meat");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


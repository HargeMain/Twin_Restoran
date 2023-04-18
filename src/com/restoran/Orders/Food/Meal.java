package com.restoran.Orders.Food;

public class Meal {
    private String name;
    private double price;
    private int calories;
   private String type;
    public Meal(String name, double price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.type=getClass().getName();
    }

    public Meal() {
        this.name="Empty";
    }

    public static Meal choiseMeal(String name){
        return switch (name.toLowerCase()){
            case "pizza"->new Pizza("pizza",15,100);
            case "spaghetti"->new Spaghetti("spaghetti",12,80);
            case "burger"->new Burger("burger",9,70);
            default -> new Meal("s",1,2);
        };
    }
    public void description(){
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCalories() {
        return calories;
    }

    public String getType() {
        return type;
    }
}

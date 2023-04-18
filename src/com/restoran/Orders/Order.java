package com.restoran.Orders;

import com.restoran.Orders.Food.Meal;
import com.restoran.User;

import java.util.Objects;

public class Order {
    private Meal meal;
    private int timeOfOrder;

    public Order(Meal meal, int timeOfOrder) {
        this.meal = meal;
        this.timeOfOrder = timeOfOrder;
    }
    public Order(){
        this.meal=new Meal("Not reserved",0,0);
    }

    public Meal getMeal() {
        return meal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meal=" + meal +
                ", timeOfOrder=" + timeOfOrder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return timeOfOrder == order.timeOfOrder && Objects.equals(meal, order.meal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal, timeOfOrder);
    }
}

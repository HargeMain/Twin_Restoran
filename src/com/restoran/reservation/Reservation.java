package com.restoran.reservation;

public interface Reservation {
    public void reserve(String name,String tableName);
    public boolean isReserve(boolean status);
}

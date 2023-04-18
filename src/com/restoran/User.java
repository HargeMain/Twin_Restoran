package com.restoran;
import java.util.Objects;
import java.util.Scanner;

public class User implements Comparable<User> {
    private String name;
    private double wallet;

    public User(String name, double wallet) {
        this.name = name;
        this.wallet = wallet;
    }
    public void addToWallet() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Write a amout you want in the wallet");
            String money = s.nextLine();
            System.out.println("Do you wish to add more funds?\tPress any key/N");
            if(s.nextLine().equalsIgnoreCase("N")){
                break;
            }
            try {
                this.wallet = Double.parseDouble(money);
            } catch (NumberFormatException e) {
                System.out.println("Error wrong type\nDo you wish to try again?\nPress any key/N");
                if(s.nextLine().equalsIgnoreCase("N")){
                    break;
                }

            }
        }
    }

    public void pay(double money){
        this.wallet-=money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    @Override
    public int compareTo(User user) {
        return this.name.compareToIgnoreCase(user.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.wallet, wallet) == 0 && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wallet);
    }
}

package com.restoran.restoran_;

import com.restoran.Orders.Food.Meal;
import com.restoran.Orders.Order;
import com.restoran.User;
import com.restoran.reservation.Reservation;

import java.util.*;

public class Restoraunt implements Reservation {

     class tables implements Comparable<tables>{
        private String name;
        private String nameOfReserver;
        private boolean isReservedStatus;
        private int serialNumber;

        @Override
        public int compareTo(tables table) {
            return this.name.compareToIgnoreCase(table.getName());
        }

        public tables(String name) {
            this.name = name;
            this.nameOfReserver = "None reserver";
            this.isReservedStatus = false;
        }

         public void setSerialNumber(int serialNumber) {
             this.serialNumber = serialNumber;
         }

         public int getSerialNumber() {
             return serialNumber;
         }

         public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameOfReserver() {
            return nameOfReserver;
        }

        public void setNameOfReserver(String nameOfReserver) {
            this.nameOfReserver = nameOfReserver;
        }

        public boolean isReservedStatus() {
            return isReservedStatus;
        }

        public void setReservedStatus(boolean reservedStatus) {
            isReservedStatus = reservedStatus;
        }

    }
    private Map<User,Order> orderMap;
     private final List<User> users;
    private List<tables> tables;
    private String name;

    public Restoraunt(String name) {
        this.tables = new ArrayList<>();
        this.name = name;
        createTable();
        this.orderMap=new HashMap<>();
        this.users=new ArrayList<>();

    }
    public void addUser(){
        Scanner s=new Scanner(System.in);
        while(true){
            System.out.println("Press any key to add a user\nPress f to exit");
            if(s.nextLine().equalsIgnoreCase("F")){
                break;
            }
            System.out.print("Name: ");
            String name=s.nextLine();
            if(users.contains(new User(name,100))){
                System.out.println("This user already exist");
                continue;
            }
            users.add(new User(name,100));
            System.out.println("User succefully added\n100$ added to account");
        }
    }
    private void createTable(){
        Scanner s=new Scanner(System.in);
        String num=" ";
        int numTable=0;
        while(true){
            System.out.print("Enter the num of tables-> ");
            num=s.nextLine();
            try {
                numTable=Integer.parseInt(num);
                break;
            }catch (NumberFormatException e){
                System.out.println("Wrong type\nWould you try again?Y/N");
                if(s.nextLine().equalsIgnoreCase("Y")){
                    break;
                }
            }
        }
        if(numTable==0){
            System.out.println("Procces has been stoped pls try again");
            return;
        }
        for(int i=0;i<numTable;i++){
            String name=String.format("%dTable",i+1);
            this.tables.add(new tables(name));
            this.tables.get(i).setSerialNumber(i+1);
        }
    }


    private int binnarySearch(String name){
        tables newTable=new tables(name);
        Comparator<tables> comparator=new Comparator<Restoraunt.tables>() {
            @Override
            public int compare(tables t1, tables t2) {
                if(t1.getSerialNumber()>t2.getSerialNumber()){
                    return 1;
                }else if(t1.getSerialNumber()<t2.getSerialNumber()){
                    return -1;
                }else{
                    return 0;
                }
            }
        };
        tables.sort(comparator);
        int found= Collections.binarySearch(tables,newTable,null);
        if(found>=0){
            return found;
        } else {
            return -1;
        }
    }

    @Override
    public void reserve(String name,String tableName) {
        int userI=searchForUser(name);
        if(userI==-1){
            return;
        }
       int index=binnarySearch(tableName);
     if(index==-1){
         System.out.println("Table doesnt exist reserved");
         return;
     }
     if(isReserve(tables.get(index).isReservedStatus)){
         System.out.println("Already reserved");
         return;
     }else{
         if(users.get(userI).getWallet()<15){
             System.out.println("You dont have enough money to pay pls added and try again");
             return;
         }
         if(orderMap.containsKey(users.get(userI))){
             System.out.println("You already have reservation");
             return;
         }
         tables.get(index).setNameOfReserver(users.get(userI).getName());
         tables.get(index).setReservedStatus(true);
         Order order=createAOrder(users.get(userI));
         if(order.getMeal().getName().equalsIgnoreCase("Empty")){
             orderMap.put(users.get(userI),new Order());
         }else{
             users.get(userI).pay(order.getMeal().getPrice());
             orderMap.put(users.get(userI),order);

         }

     }

    }
    private Order createAOrder(User user){
        Scanner s=new Scanner(System.in);
        while (true){
            System.out.println("Type a name of food you like to eat\n1.Pizza\2.Spaghetti\3.Burger");
            Meal meal=Meal.choiseMeal(s.nextLine());
            if(meal.getName().equalsIgnoreCase("s")){
                System.out.println("Unknow food\nYou need to try again");
            }else{
                if(user.getWallet()<meal.getPrice()){
                    System.out.println("You cannot afford the order pls add money");
                    return new Order(new Meal(),0);
                }
                return new Order(meal,0);
            }

        }
    }

    private int searchForUser(String name){
        User newUser=new User(name,0);
        int found=Collections.binarySearch(users,newUser,null);
        if(found<0){
            System.out.println("User not found");
            return -1;
        }else{
           return 0;
        }
    }
    public void removeReservation(String name){
        int index=binnarySearch(name);
        if(index==-1){
            System.out.println("Table not found");
            return;
        }
        tables.get(index).setReservedStatus(false);
        tables.get(index).setNameOfReserver("None reserver");
    }
    public void printOutUserAndOrders(){
        for(User u:orderMap.keySet()){
            System.out.println("User: "+u.getName()+"\torder: "+orderMap.get(u));

        }
    }
    public void addFund(String name){
        int index=searchForUser(name);
        if(index==-1) {
            System.out.println("User not found");
        }else{
            users.get(index).addToWallet();
        }
    }

    public void printTable(){
        int counter=1;
        for(int i=0;i<tables.size();i++){
            System.out.print(String.format(" --%s reserved by %s-- ",tables.get(i).name,tables.get(i).getNameOfReserver()));
            counter++;
            if(counter==tables.size()/2){
                System.out.println();
                counter=1;

            }
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isReserve(boolean status) {
        if(status){
            return true;
        }else{
            return false;
        }
    }
}

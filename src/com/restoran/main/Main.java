package com.restoran.main;
import com.restoran.restoran_.Restoraunt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        Restoraunt r=new Restoraunt("Twin");
        while (true){
            System.out.println("\n\nWELCOME TO "+r.getName()+"\nHOW WE CAN HELP YOU?\n1.SEE TABLE\n2.RESERVE TABLE\n3.ADD USER\n4.SEE ORDERS\n5.REMOVE A RESERVATION\n6.OPEN A USER SERVICE MENU\n7.Exit\n\n\n");
            String choise=s.nextLine();
            try {
                switch (Integer.parseInt(choise)){
                    case 1->r.printTable();
                    case 2->{
                        System.out.println("TYPE A NAME OF TABLE YOU WANT RESERVE");
                        String name=s.nextLine();
                        System.out.println("TYPE A NAME OF USER STORED IN LIST OF USERS");
                        String nameOfUser=s.nextLine();
                        r.reserve(nameOfUser,name);
                    }
                    case 3->{
                        r.addUser();
                    }
                    case 4->{
                        r.printOutUserAndOrders();
                    }
                    case 5->{
                        System.out.println("TYPE A NAME OF TABLE YOU WANT TO REMOVE RESERVATION");
                        r.removeReservation(s.nextLine());
                    }
                    case 6->{
                        String control=" ";
                        Scanner s1=new Scanner(System.in);
                        do{
                            control=s1.nextLine();
                            System.out.println("WELCOME TO USER SERVICE MENU\n1.ADD FUNDS TO WALLET\n2.EXIT");
                            switch (Integer.parseInt(s.nextLine())){
                                case 1->{
                                    System.out.print("Type a name of user-> ");
                                    String name=s.nextLine();
                                    r.addFund(name);
                                }
                                case 2->{}
                                default -> {
                                    System.out.println("Wrong input");
                                }
                            }
                        }while (Integer.parseInt(control)!=2);
                    }
                    case 7->{
                        return;
                    }
                }
            }catch (NumberFormatException e){
                System.out.println("Wrong type");
            }

        }

    }
}


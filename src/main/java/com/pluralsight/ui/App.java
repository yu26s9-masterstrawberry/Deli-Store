package com.pluralsight.ui;

import com.pluralsight.models.*;

import java.io.IOException;
import java.util.List;

import static com.pluralsight.services.Helper.*;

public class App {
    //need to doublecheck the list
    public static final List<String> BREAD_OPTIONS  = List.of("white", "wheat", "rye", "wrap");
    public static final List<String> MEAT_OPTIONS   = List.of("steak", "ham", "salami", "roast beef", "chicken", "bacon");
    public static final List<String> CHEESE_OPTIONS = List.of("american", "provolone", "cheddar", "swiss");
    public static final List<String> TOPPING_OPTIONS = List.of(
            "lettuce", "peppers", "onions", "tomatoes", "jalapeños",
            "cucumbers", "pickles", "guacamole", "mushrooms");
    public static final List<String> SAUCE_OPTIONS  = List.of(
            "mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette");
    public static final List<String> SIDE_OPTIONS   = List.of("au jus", "sauce");
    public static final List<String> DRINK_SIZES    = List.of("small", "medium", "large");
    public static final List<String> DRINK_FLAVORS    = List.of("coke", "pepsi", "fanta", "dr. pepper", "lemonade");

    public static final List<String> CHIP_OPTIONS   = List.of(
            "doritos", "lays", "sun chips", "fritos", "takis");

    // Home Screen
    public void display() {
        int option;
        do {
            System.out.println("""

                    --------- HOME SCREEN ---------
                    1) New Order
                    0) Exit
                    """);
            option = Console.promptForInt("Select an option: ");
            switch (option) {
                case 1 -> orderScreen();
                case 0 -> System.out.println("Exiting");
                default -> System.out.println("Invalid option, please try again.");
            }
        } while (option != 0);
    }

    // Order Screen
    private void orderScreen() {
        boolean status = true;
        Order order = new Order();
        while (status) {
            System.out.println("""
                    
                    --------- ORDER SCREEN ---------
                    1) Add Sandwich
                    2) Add Drink
                    3) Add Chips
                    4) Checkout
                    0) Cancel Order
                    """);

            int choice = Console.promptForInt("Select an option: ");
            switch (choice) {
                case 1 -> addSandwich(order);
                case 2 -> addDrinks(order);
                case 3 -> addChips(order);
                case 4 -> {
                    if (checkout(order)) {
                        status = false;
                    }
                }
                case 0 -> {
                    System.out.println("Order is cancelled.");
                    status = false;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    private boolean checkout(Order order) {
        if (order.isEmpty()){
            System.out.println("Empty order");
            return false;
        }
        System.out.println("\n" + order.orderDetails());
        System.out.println("""
                1) Confirm. This will save receipt and return home
                2) Cancel. This will delete order and return home
                3) Go back to order screen
                """);
        int  choice = Console.promptForInt("Select an option: ");
        if (choice == 1) {
            try {
                order.receipts();
                System.out.println("Thank you for your visit!");
            } catch (IOException e) {
                System.out.println("Error! could not save receipt:" + e.getMessage());
            }
            return true;
        }else if (choice == 2){
            System.out.println("Order cancelled.");
            return true;
        }else {
            System.out.println("Returning to order screen");
            return false;
        }
    }

}

package com.pluralsight.ui;

import com.pluralsight.models.*;

import java.io.IOException;
import java.util.List;

public class App {
    //need to doublecheck the list
    public static final List<String> BREAD_OPTIONS  = List.of("white", "wheat", "rye", "wrap");
    public static final List<String> MEAT_OPTIONS   = List.of("steak", "ham", "salami", "roast beef", "chicken", "bacon");
    public static final List<String> CHEESE_OPTIONS = List.of("american", "provolone", "cheddar", "swiss");
    public static final List<String> REG_TOPPING_OPTIONS = List.of(
            "lettuce", "peppers", "onions", "tomatoes", "jalapeños",
            "cucumbers", "pickles", "guacamole", "mushrooms");
    public static final List<String> SAUCE_OPTIONS  = List.of(
            "mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette");
    public static final List<String> SIDE_OPTIONS   = List.of("au jus", "sauce");
    public static final List<String> DRINK_SIZES    = List.of("small", "medium", "large");
    public static final List<String> CHIP_OPTIONS   = List.of(
            "doritos", "lays", "sun chips", "cheetos", "pretzels", "kettle chips");

    // Home Screen
    public void display() {
        int option;
        do {
            System.out.println("""
                    
                    ========= HOME SCREEN =========
                    1) New Order
                    0) Exit
                    """);
            option = Console.promptForInt("Select an option: ");
            switch (option) {
                case 1 -> orderScreen();
                case 0 -> System.out.println("Exiting the application. Goodbye!");
                default -> System.out.println("Invalid selection. Please try again.");
            }
        } while (option != 0);
    }

    // Order Screen
    private void orderScreen() {
        boolean ordering = true;

        while (ordering) {
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
                case 1 -> System.out.println("Add Sandwich");
                case 2 -> System.out.println("Add drink");
                case 3 -> System.out.println("Add chips ");
                case 4 -> System.out.println("checkout ");
                case 0 -> {
                    System.out.println("Order cancelled.");
                    ordering = false;
                }
                default -> System.out.println("Invalid selection. Please try again.");
            }
        }
    }

}

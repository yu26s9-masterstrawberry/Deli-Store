package com.pluralsight.services;

import com.pluralsight.models.*;
import com.pluralsight.ui.Console;

import java.util.List;

import static com.pluralsight.ui.App.*;

public class Helper {
    public  static void addSandwich(Order order) {
        Sandwich sandwich;
        System.out.println("**Adding sandwich**");
        System.out.println("""
                Would you like a signature sandwich?
                1) BLT
                2) Philly Cheese Steak
                3) House Sub
                4) Bacon Sub
                5) Build my own
                """);
        int choice = Console.promptForInt("Select Option: ");
        switch (choice) {
            case 1 -> {
                sandwich = SignatureSandwich.createBLT();
                System.out.println("BLT is ready, you can still customize it below.");
                sandwich = customizeExistingSandwich(sandwich);
            }
            case 2 -> {
                sandwich = SignatureSandwich.createPhillyCheeseSteak();
                System.out.println("Philly Cheese Steak is ready, you can still customize it below.");
                sandwich = customizeExistingSandwich(sandwich);
            }
            case 3 -> {
                sandwich = SignatureSandwich.createHouseSub();
                System.out.println("House Sub is ready, you can still customize it below.");
                sandwich = customizeExistingSandwich(sandwich);
            }
            case 4 -> {
                sandwich = SignatureSandwich.createBaconSub();
                System.out.println("Bacon Sub is ready, you can still customize it below.");
                sandwich = customizeExistingSandwich(sandwich);
            }
            default -> {
                sandwich = buildCustom();
            }
        }


        if (sandwich != null) {
            order.addSandwich(sandwich);
            System.out.println("\nSandwich added.");
        }
    }


    private static Sandwich buildCustom() {
        System.out.println("\nSandwich size:" + "\n 1) 4\"  ($5.50)" + "\n 2) 8\"  ($7.00)"+ "\n 3) 12\" ($8.50)");
        int sizeChoice = Console.promptForIntRange("Select size: ", "Please enter 1, 2, or 3.", 1, 3);
        int size = switch (sizeChoice) {
            case 1 -> 4;
            case 2 -> 8;
            default -> 12;
        };
        System.out.println("\nBread type: " + BREAD_OPTIONS);
        String bread = promptList("Select bread: ", BREAD_OPTIONS);
        String toastedInput = Console.promptForString("Toasted? (yes/no): ");
        boolean toasted = toastedInput.toLowerCase().startsWith("y");

        Sandwich sandwich = new Sandwich(size, bread, toasted);
        addToppingsToSandwich(sandwich);
        return sandwich;

    }

    //might have to move this to console, but works for now
    public static String promptList(String prompt, List<String> options) {
        while (true) {
            String input = Console.promptForString(prompt);
            if (options.contains(input)) return input;
            System.out.println("  Please choose from: " + options);
        }
    }

    private static void addToppingsToSandwich(Sandwich sandwich) {
        //charge
        addPremium(sandwich, "Cheese", CHEESE_OPTIONS, false);
        addPremium(sandwich, "Meat", MEAT_OPTIONS, true);

        //one by one, changed from listing it in one line
        //noncharged
        System.out.println("\nRegular toppings " + TOPPING_OPTIONS + " (they are included, no extra chareg)");
        System.out.println("Enter each topping you want, please enter one per line. When done press enter with no input.");
        while (true) {
            String input = Console.promptForString("Enter topping: ");
            if (input.isBlank()) {
                break;
            }
            if(TOPPING_OPTIONS.contains(input)) {
                System.out.println(input+" Added");
                RegularTopping regularTopping = new RegularTopping(input);
                sandwich.addTopping(regularTopping);
            }else  {
                System.out.println("Enter a topping from the list");
            }
        }

        System.out.println("\nSauces " + SAUCE_OPTIONS + " (they are included, no extra chareg)");
        while (true) {
            String input = Console.promptForString("Enter sauce: ");
            if (input.isBlank()) {
                break;
            }
            if(SAUCE_OPTIONS.contains(input)) {
                System.out.println(input+" Added");
                RegularTopping regularTopping = new RegularTopping(input);
                sandwich.addTopping(regularTopping);
            }else  {
                System.out.println("Enter a sauce from the list");
            }
        }

        System.out.println("\nSides " + SIDE_OPTIONS + " (they are included, no extra chareg)");
        while (true) {
            String input = Console.promptForString("Enter side: ");
            if (input.isBlank()) {
                break;
            }
            if(SIDE_OPTIONS.contains(input)) {
                System.out.println(input+" Added");
                RegularTopping regularTopping = new RegularTopping(input);
                sandwich.addTopping(regularTopping);
            }else  {
                System.out.println("Enter a side from the list");
            }
        }

    }

    private static void addPremium(Sandwich sandwich, String type, List<String> options, boolean isMeat) {
        System.out.println("\n" + type + " options\n" + options);
        while (true) {
            String input = Console.promptForString("Enter "+type+ " toppings(enter blank to skip): ");
            if (input.isBlank()) {
                break;
            }
            if (options.contains(input)) {
                String extraInput = Console.promptForString("Extra " + type.toLowerCase() + "? (yes/no): ");
                boolean isExtra = extraInput.toLowerCase().startsWith("y");
                Topping topping;
                if (isMeat) {
                    topping = new Meat(input, isExtra);
                }else {
                    topping = new Cheese(input, isExtra);
                }
                sandwich.addTopping(topping);
                System.out.println("  Added: " + topping);
            } else {
                System.out.println("Your input was not recognized, enter from options:" + options);
            }

        }
    }

    private static Sandwich customizeExistingSandwich(Sandwich sandwich) {
        System.out.println("\nToppings on sandwich: ");
        sandwich.getToppings().forEach(topping -> {
            System.out.println("\t-" + topping);
        });
        System.out.println("\nWould you like to add any other toppings?");
        String answer = Console.promptForString("(yes/no): ");
        if (answer.toLowerCase().startsWith("y")) {
            addToppingsToSandwich(sandwich);
        }
        return sandwich;
    }

    public static void addChips(Order order){
        System.out.println("\n **Want to add chips ($1.50)**");
        System.out.println("Types of chips: " + CHIP_OPTIONS);
        String chip = Console.promptForString("Enter chip: ");
        order.addChips(new Chips(chip));
        System.out.println("Chips added\n");
    }
    public static void addDrinks(Order order){
        System.out.println("\n **Want to add a drink**");
        System.out.println("Options: small ($2.00) | medium ($2.50) | large ($3.00)");
        String size = promptList("Select size: ", DRINK_SIZES);
        System.out.println("Types of chips: " + DRINK_FLAVORS);
        String type = Console.promptForString("Enter type of drink: ");
        order.addDrink(new Drink(size, type));
        System.out.println("Drink added\n");
    }
}

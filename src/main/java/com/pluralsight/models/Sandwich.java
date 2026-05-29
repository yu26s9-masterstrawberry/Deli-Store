package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private int size;
    private String breadType;
    private boolean toasted;
    private List<Topping> toppings;

    public Sandwich(int size, String breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double getPrice() {
        double basePrice = switch (size) {
            case 4  -> 5.50;
            case 8  -> 7.00;
            case 12 -> 8.50;
            default -> 0.0;
        };
        double toppingTotal = toppings.stream().mapToDouble(t -> t.getPrice(size)).sum();
        return basePrice + toppingTotal;
    }

    public int getSize() {
        return size;
    }
    public String getBreadType() {
        return breadType;
    }
    public boolean isToasted() {
        return toasted;
    }
    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d\" %s sandwich%s", size, breadType, toasted ? " (toasted)" : ""));
        for (Topping t : toppings) {
            sb.append("\n   - ").append(t);
        }
        sb.append(String.format("\n   Subtotal: $%.2f", getPrice()));
        return sb.toString();
    }
}

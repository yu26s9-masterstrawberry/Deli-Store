package com.pluralsight.models;

public class Drink {
    private String size;   // small, medium, large
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public double getPrice() {
        return switch (size.toLowerCase()) {
            case "small"  -> 2.00;
            case "medium" -> 2.50;
            case "large"  -> 3.00;
            default -> 0.0;
        };
    }

    public String getSize() {
        return size;
    }
    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return String.format("%s %s drink  $%.2f", size, flavor, getPrice());
    }
}

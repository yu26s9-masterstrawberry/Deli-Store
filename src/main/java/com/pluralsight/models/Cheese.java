package com.pluralsight.models;

public class Cheese extends Topping {
    private boolean isExtra;

    public Cheese(String name, boolean isExtra) {
        super(name);
        this.isExtra = isExtra;
    }

    public boolean isExtra() {
        return isExtra;
    }

    @Override
    public double getPrice(int sandwichSize) {
        if (isExtra) {
            return switch (sandwichSize) {
                case 4 -> 0.30;
                case 8 -> 0.60;
                case 12 -> 0.90;
                default -> 0.0;
            };
        } else {
            return switch (sandwichSize) {
                case 4 -> 0.75;
                case 8 -> 1.50;
                case 12 -> 2.25;
                default -> 0.0;
            };
        }
    }

    @Override
    public String toString() {
        return name + (isExtra ? " (extra)" : "");
    }
}

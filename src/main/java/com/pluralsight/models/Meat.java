package com.pluralsight.models;

public class Meat extends Topping {
    private boolean isExtra;

    public Meat(String name, boolean isExtra) {
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
                case 4  -> 0.50;
                case 8  -> 1.00;
                case 12 -> 1.50;
                default -> 0.0;
            };
        } else {
            return switch (sandwichSize) {
                case 4  -> 1.00;
                case 8  -> 2.00;
                case 12 -> 3.00;
                default -> 0.0;
            };
        }
    }

    @Override
    public String toString() {
        return name + (isExtra ? " (extra)" : "");
    }
}

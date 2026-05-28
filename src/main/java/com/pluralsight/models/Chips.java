package com.pluralsight.models;

public class Chips {
    private String type;

    public Chips(String type) {
        this.type = type;
    }

    public double getPrice() {
        return 1.50;
    }

    public String getType() { return type; }

    @Override
    public String toString() {
        return String.format("%s chips  $%.2f", type, getPrice());
    }
}

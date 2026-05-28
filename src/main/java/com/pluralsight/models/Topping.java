package com.pluralsight.models;

public abstract class Topping {
    public String name;

    public Topping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice(int sandwichSize);

    @Override
    public String toString() {
        return name;
    }
}

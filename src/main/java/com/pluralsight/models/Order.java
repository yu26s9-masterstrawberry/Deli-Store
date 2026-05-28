package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwichesList;
    private List<Drink> drinksList;
    private List<Chips> chipsList;

    public Order(){
        this.sandwichesList = new ArrayList<>();
        this.drinksList = new ArrayList<>();
        this.chipsList = new ArrayList<>();
    }

    public void addSandwich(Sandwich sandwich){
        this.sandwichesList.add(sandwich);
    }

    public void addDrink(Drink drink){
        this.drinksList.add(drink);
    }

    public void addChips(Chips chips){
        this.chipsList.add(chips);
    }

    public List<Sandwich> getSandwichesList() {
        return sandwichesList;
    }

    public List<Drink> getDrinksList() {
        return drinksList;
    }

    public List<Chips> getChipsList() {
        return chipsList;
    }

    public boolean isEmpty() {
        return sandwichesList.isEmpty() && drinksList.isEmpty() && chipsList.isEmpty();
    }

    public double getTotal(){
        double total = 0;
        total+=this.sandwichesList.stream().mapToDouble(Sandwich::getPrice).sum();
        total+=this.drinksList.stream().mapToDouble(Drink::getPrice).sum();
        total+=this.chipsList.stream().mapToDouble(Chips::getPrice).sum();
        return total;
    }



}

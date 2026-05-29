package com.pluralsight.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public String orderDetails(){
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("********* ORDER DETAILS *********\n\n");
        if (!sandwichesList.isEmpty()) {
            orderDetails.append("***** sandwiches list *****\n");
            for (int i = sandwichesList.size() - 1; i >= 0; i--) {
                orderDetails.append(sandwichesList.get(i)).append("\n");
            }
        }
        if (!drinksList.isEmpty()) {
            orderDetails.append("***** drink list *****\n");
            for (int i = drinksList.size() - 1; i >= 0; i--) {
                orderDetails.append(drinksList.get(i)).append("\n");
            }
        }
        if (!chipsList.isEmpty()) {
            orderDetails.append("***** sandwiches list *****\n");
            for (int i = chipsList.size() - 1; i >= 0; i--) {
                orderDetails.append(chipsList.get(i)).append("\n");
            }
        }
        orderDetails.append("\n********** TOTAL ORDER DETAILS **********\n");
        orderDetails.append(String.format("Total order: "+ "$%.2f\n", getTotal()));
        orderDetails.append("*********************************************\n");
        return orderDetails.toString();
    }

    public void receipts() throws IOException {
        Path dir = Path.of("receipts");
        Files.createDirectories(dir);
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss"));
        String file = dir+"/"+timeStamp+".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Sandwich Shop\n");
            writer.write("Time Stamp: "+timeStamp+"\n" );
            writer.write(orderDetails());
            System.out.println("Receipt saved.");
        }

    }




}

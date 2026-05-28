package com.pluralsight.models;

public class SignatureSandwich extends Sandwich {
    private String signatureName;

    public SignatureSandwich(String signatureName, int size, String breadType, boolean toasted) {
        super(size,breadType, toasted);
        this.signatureName = signatureName;
    }

    public String getSignatureName() {
        return signatureName;
    }

    public static SignatureSandwich createBLT() {
        SignatureSandwich blt = new SignatureSandwich("BLT",8,"white",true);
        blt.addTopping(new Meat("bacon",false));
        blt.addTopping(new Cheese("cheddar",false));
        blt.addTopping(new RegularTopping("lettuce"));
        blt.addTopping(new RegularTopping("tomatoes"));
        blt.addTopping(new RegularTopping("ranch"));
        return blt;
    }

    public static SignatureSandwich createPhillyCheeseSteak() {
        SignatureSandwich phillyCheeseSteak = new SignatureSandwich("Philly Cheese Steak",8,"white",true);
        phillyCheeseSteak.addTopping(new Meat("steak",false));
        phillyCheeseSteak.addTopping(new Cheese("american",false));
        phillyCheeseSteak.addTopping(new RegularTopping("peppers"));
        phillyCheeseSteak.addTopping(new RegularTopping("mayo"));
        return phillyCheeseSteak;
    }

    @Override
    public String toString() {
        return "SignatureSandwich{" +
                "signatureName='" + signatureName + '\'' +
                '}' + super.toString();
    }
}

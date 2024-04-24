package foms.food;

import java.util.Scanner;


public class MainDish extends FoodItem {
    private Scanner sc = new Scanner(System.in);
    private int saltLevel;
    private String baseDescription;

    public MainDish(String name, double price, String category, String description) {
        super(name, price, availability, category, description);
        this.saltLevel = 2; // Default as normal salt level
        this.baseDescription = description;
    }

    public MainDish(String name, double price, boolean availability, String category, String description) {
        super(name, price, availability, category, description);
        this.saltLevel = 2; // Default as normal salt level
        this.baseDescription = description;
    }

    public static MainDish selectMainDish() {
        System.out.println("Select a main dish:");
        System.out.println("1. Steak (Juicy grilled steak with herbs)");
        System.out.println("2. Chicken Rice (Delicious chicken with fragrant rice)");
        System.out.println("3. Veggie Burger (Juicy grilled veggie burger)");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                return new MainDish("Steak", 10.00, true, "Main Dish", "Juicy grilled steak with herbs");
            case 2:
                return new MainDish("Chicken Rice", 5.00, true, "Main Dish", "Delicious chicken with fragrant rice");
            case 3:
                return new MainDish("Veggie Burger", 7.00, true, "Main Dish", "Juicy grilled veggie burger");
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    @Override
    public void customise() {
        System.out.println("Current Salt Level: " + convertSaltLevelToString());
        System.out.println("Choose a salt level:");
        System.out.println("(1) Less Salt\n(2) Normal Salt\n(3) More Salt");
        int newSaltLevel = sc.nextInt();
        if (newSaltLevel >= 1 && newSaltLevel <= 3) {
            this.saltLevel = newSaltLevel;
            updateDescription();
            System.out.println("Salt level updated to: " + convertSaltLevelToString());
        } else {
            System.out.println("Invalid selection. No changes made.");
        }
    }

    private void updateDescription() {
        String saltDescription = switch (saltLevel) {
            case 1 -> "Less Salt";
            case 2 -> "Normal Salt";
            case 3 -> "More Salt";
            default -> "Unknown Salt Level";
        };
        setDescription(this.baseDescription + " | Salt Level: " + saltDescription);
    }

    @Override
    public void displayFoodInformation() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return super.toString() + " | Salt Level: " + convertSaltLevelToString();
    }

    private String convertSaltLevelToString() {
        return switch (saltLevel) {
            case 1 -> "Less Salt";
            case 2 -> "Normal Salt";
            case 3 -> "More Salt";
            default -> "Unknown";
        };
    }
}

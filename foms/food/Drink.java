package foms.food;

import java.util.Scanner;
public class Drink extends FoodItem {
    private static Scanner sc = new Scanner(System.in);
    private int iceLevel;
    private String baseDescription;

    public Drink(String name, double price, boolean availability, String category, String description) {
        super(name, price, availability, category, description);
        this.iceLevel = 3; // Default as maximum ice level
        this.baseDescription = description;
    }

    public static Drink selectDrink() {
        System.out.println("Select a drink:");
        System.out.println("1. Lemonade (Freshly squeezed lemonade)");
        System.out.println("2. Cola (A refreshing cola drink)");
        System.out.println("3. Beer (Chilled premium beer)");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                return new Drink("Lemonade", 2.00, true, "Cold Drinks", "Freshly squeezed lemonade");
            case 2:
                return new Drink("Cola", 1.50, true, "Cold Drinks", "A refreshing cola drink");
            case 3:
                return new Drink("Beer", 4.00, true, "Alcoholic Beverages", "Chilled premium beer");
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    @Override
    public void customise() {
        System.out.println("Current Ice Level: " + convertIceLevelToString());
        System.out.println("Choose an ice level:");
        System.out.println("(1) No Ice\n(2) Little Ice\n(3) Maximum Ice");
        int newIceLevel = sc.nextInt();
        if (newIceLevel >= 1 && newIceLevel <= 3) {
            this.iceLevel = newIceLevel;
            updateDescription();
            System.out.println("Ice level updated to: " + convertIceLevelToString());
        } else {
            System.out.println("Invalid selection. No changes made.");
        }
    }

    private void updateDescription() {
        String iceDescription = switch (iceLevel) {
            case 1 -> "No Ice";
            case 2 -> "Little Ice";
            case 3 -> "Maximum Ice";
            default -> "Unknown Ice Level";
        };
        setDescription(this.baseDescription + " | Ice Level: " + iceDescription);
    }

    @Override
    public void displayFoodInformation() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return getName() + ": " + getDescription() + " | Price: $" + getPrice() + (getAvailability() ? "(Available)" : "(Unavailable)") + " | Ice Level: " + convertIceLevelToString();
    }

    private String convertIceLevelToString() {
        return switch (iceLevel) {
            case 1 -> "No Ice";
            case 2 -> "Little Ice";
            case 3 -> "Maximum Ice";
            default -> "Unknown";
        };
    }

}

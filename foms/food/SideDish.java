package foms.food;
import java.util.Scanner;

public class SideDish extends FoodItem {
    private Scanner sc = new Scanner(System.in);
    private int saltLevel;
    private String baseDescription;

    public SideDish(String name, double price, boolean availability, String category, String description) {
        super(name, price, availability, category, description);
        this.saltLevel = 2; // Default as normal salt level
        this.baseDescription = description;
    }

    public static SideDish selectSideDish() {
        System.out.println("Select a side dish:");
        System.out.println("1. French Fries (Crispy golden French fries)");
        System.out.println("2. Coleslaw (Crunchy fresh coleslaw)");
        System.out.println("3. Salad (Fresh green salad)");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                return new SideDish("French Fries", 3.00, true, "Side Dish", "Crispy golden French fries");
            case 2:
                return new SideDish("Coleslaw", 2.00, true, "Side Dish", "Crunchy fresh coleslaw");
            case 3:
                return new SideDish("Salad", 3.50, true, "Side Dish", "Fresh green salad");
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

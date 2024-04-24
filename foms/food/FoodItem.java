package foms.food;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FoodItem{
    private String name;
    private double price;
    private boolean availability = true;
    private String description;
    private String customOrder;

    public FoodItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Removed abstract keyword
    public void customise() {
        // Define customization logic for each type of food item here
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean getAvailability() {
        return availability;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updatePrice() {
        System.out.print("Enter the new price: ");
        Scanner scanner = new Scanner(System.in);
        double newPrice;
        while (true) {
            try {
                newPrice = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("enter a float!");
                scanner.next();
            }
        }
        this.price = newPrice;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateDescription() {
        System.out.print("Enter the new Description: ");
        Scanner scanner = new Scanner(System.in);
        String newDescription;
        while (true) {
            try {
                newDescription = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("enter a float!");
                scanner.next();
            }
        }
        this.description = newDescription;

        System.out.printf("Updated the description to: %s\n",description);
    }

    public String toString() {
        return name + ": " + description + " | Price: $" + price + (availability ? " (Available)" : " (Unavailable)");
    }
}

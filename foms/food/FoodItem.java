package foms.food;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FoodItem implements Serializable {
    private String name;
    private double price;
    private boolean availability = true;
    private String description;
    private String customRequest;

    /**
     * constructor for fooditem basis. abstract class, so this is only used for polymorphism in menu/cart
     * @param name of the food
     * @param price price of the food
     * @param description brief description of the food
     */
    public FoodItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * a customise function used by customers to denote any special request.
     */
    public void customiseFoodItem() {
        System.out.printf("Customising food item %s...\n", getName());
        System.out.println("Enter your custom request (press 0 to quit): ");

        Scanner scanner = new Scanner(System.in);
        String customRequest;

        try {

            customRequest = scanner.nextLine();
            scanner.nextLine(); //reading in '\n'
            if (customRequest.equals("0")) {
                System.out.println("Didnt not input custom request. \nReturning to previous page.");
                return;
            }

            setCustomRequest(customRequest);
            System.out.printf("Custom Request has been set to: %s\n", customRequest);
            return;
        } catch (Exception e) {
            System.out.println("something went wrong");
        }
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

    public String setCustomRequest(String customRequest) {
        this.customRequest = customRequest;
    }

    public void setAvailability() {
        Scanner scanner = new Scanner(System.in);

        String choice;
        while (true) {
            System.out.print("Choose 1 to set as Available\n 2 to set as Unavailable.\nChoose 0 to exit.\n" +
                    "Enter your choice: ");
            choice = scanner.next();
            switch (choice) {
                case "0"-> {System.out.println("going back to previous page..");
                            return;}
                case "1" -> {System.out.println("Availability has been set to true.");
                             availability = true;}
                case "2" -> {System.out.println("Availability has been set to false.");
                    availability = false;}
                }
            }
        }
    
}

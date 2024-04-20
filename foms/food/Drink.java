package foms.food;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Drink extends FoodItem {

    /**
     * ice level of the drink. ranges from 0,1,2, 0 is no ice, 1 is less ice, 2 is normal ice.
     * 2 is the default, but changeable when requested.
     */
    private int iceLevel = 2;
    /**
     * Constructor
     * Creates a new Food Item with the given name, price and description.
     * Automatically adds the added menu to menu.
     *
     * @param name        This Food Item's name
     * @param price       This food item's price
     * @param description This food item's description
     */
    public Drink(String name, float price, String description) {
        super(name, price, description);
    }

    public Drink(FoodItem menuItem) {
        super(menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }

    public void setIceLevel () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the desired ice level: \n0: No Ice \n1: Less Ice 2: Normal Ice");
        int iceLevel = 2;

        try {
            iceLevel = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong Input! Your Ice Level has been set to default");
        }

        this.iceLevel = iceLevel;
    }

}

package foms.food;



import java.util.InputMismatchException;
import java.util.Scanner;

/**
 Represents a template for Food Items that are added to the menu.
 */

public abstract class FoodItem {

    /**
     *a new menu item with a unique name */
    private String name;
    /**
     *a new menu item with a price
     */
    private float price;
    /**
     *a new menu item with a unique description
     */
    private String description;


    /**
     * Constructor
     * Creates a new Food Item with the given name, price and description.
     * Automatically adds the added menu to menu.
     * @param name This Food Item's name
     * @param price This food item's price
     * @param description This food item's description
     */

    public FoodItem(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * overriding toString method to print the fooditem's information.
     * @return food name, description and price in a stringed format.
     */
    @Override
    public String toString() {
        return getName() + "\n" + getDescription() + "\nprice: " + String.format("%.2f", getPrice());
    }


    /**
     *  Gets the price of the food item.
     *  @return this food item's price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Changes the price of the food item.
     *Private and only accessed by Managers by initiating a class.
     * @param newPrice is the food item's new price.
     */
    protected void setPrice(float newPrice) {
        price = newPrice;
    }

    /**
     *  Gets the price of the food item.
     * @return this food item's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the price of the food item.
     * @param newDescription is the food item's new price.
     */
    protected void setDescription(String newDescription) {
        description = newDescription;
    }

    public String getName() {
        return name;
    }
}

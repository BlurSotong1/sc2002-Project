package foms.food;

import java.io.Serializable;

public class Sides extends FoodItem implements Serializable {

    /**
     * Constructor
     * Creates a new FoodItem with the given name, price and description.
     * Automatically adds the added menu to menu.
     *
     * @param name        This Food Item's name
     * @param price       This food item's price
     * @param description This food item's description
     */
    public Sides(String name, double price, String description) {
        super(name, price, description);
    }

    public Sides(FoodItem menuItem) {
        super(menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }
}

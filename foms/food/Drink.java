package foms.food;

import java.awt.*;
import java.io.Serializable;

public class Drink extends FoodItem implements Serializable {
    /**
     * ice levels of a drink that is customisable.
     */
    private iceLevels iceLevel = iceLevels.DEFAULT_ICE;

    /**
     * constructors to create food item by manager
     * @param name name of the drink
     * @param price of the drink
     * @param description what is the drink
     */
    public Drink(String name, double price, String description) {
        super(name, price, description);
    }

    /**
     * used to copy a new object over for customers into the cart
     * @param drink is the original menu item.
     */
    public Drink(FoodItem drink) {
        super(drink.getName(),drink.getPrice(),drink.getDescription());
    }






}

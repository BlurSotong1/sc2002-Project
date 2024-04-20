package foms.food;

public class Sides extends FoodItem {

    /**
     * Constructor
     * Creates a new FoodItem with the given name, price and description.
     * Automatically adds the added menu to menu.
     *
     * @param name        This Food Item's name
     * @param price       This food item's price
     * @param description This food item's description
     */
    public Sides(String name, float price, String description) {
        super(name, price, description);
    }

    public Sides(FoodItem menuItem) {
        super(menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }
}

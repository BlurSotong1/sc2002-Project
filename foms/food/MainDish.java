package foms.food;

public class MainDish extends FoodItem {

    /**
     * Constructor
     * Creates a new Food Item with the given name, price and description.
     * Automatically adds the added menu to menu.
     *
     * @param name        This Food Item's name
     * @param price       This food item's price
     * @param description This food item's description
     */
    public MainDish(String name, float price, String description) {
        super(name, price, description);
    }


    public MainDish(FoodItem menuItem) {
        super(menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }
}

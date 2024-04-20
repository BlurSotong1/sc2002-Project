package foms.food;

/**
 * in a set meal, there are 3 components:  Main dish, a drink, and 1 sides.
 * drinks and sides are chosen by customer.
 * A set meal is quite a default by most food chains.
 */
public class SetMeal extends MainDish {

    private MainDish mainDish;
    /**
     * a drink that comes with the meal. Customisable by customer.
     */
    private Drink drink;

    /**
     * sides that come with the meal. Customisable by customer.
     */
    private Sides sides;

    /**
     * Constructor
     * Creates a new Food Item with the given name, price and description.
     * Automatically adds the added menu to menu.
     * When constructing set meal, only the main dish
     *
     * @param name        This Food Item's name
     * @param price       This food item's price
     * @param description This food item's description
     */
    public SetMeal(String name, float price, String description, MainDish mainDish) {
        super(name, price, description);
        this.mainDish = mainDish;

    }

    public SetMeal(SetMeal menuSetMeal) {
        super(menuSetMeal.getName(),menuSetMeal.getPrice(),menuSetMeal.getDescription());
        mainDish = menuSetMeal.mainDish;
    }

    public void chooseDrinkForSetMeal(Drink drink) {
        this.drink = drink;
    }

    public void chooseSidesForSetMeal(Sides sides) {
        this.sides = sides;
    }


}

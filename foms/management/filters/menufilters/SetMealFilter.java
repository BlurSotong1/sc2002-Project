package foms.management.filters.menufilters;

import foms.food.FoodItem;
import foms.food.SetMeal;
import foms.management.lists.Menu;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The SetMealFilter class implements the MenuFilters interface to display and filter set meals from the menu.
 */
public class SetMealFilter implements MenuFilters, Serializable {

    /**
     * Displays the filtered menu containing only set meals.
     * @param menu the list of food items to filter
     */
    @Override
    public void displayFilteredMenu(ArrayList<FoodItem> menu) {
        System.out.println("Set Meals");
        int counter = 1;
        String s;
        for (FoodItem foodItem: menu) {
            if (foodItem instanceof SetMeal) {
                if (foodItem.getAvailability()) {
                    s = "Available";
                } else {
                    s = "Unavailable";
                }
                System.out.printf("%d. %s | %s | Price: %.2f (%s)\n",counter,foodItem.getName()
                        ,foodItem.getDescription(),foodItem.getPrice(),s);
                counter++;
            }
        }
    }


    /**
     * Finds the food item in the filtered menu based on the index.
     *
     * @param index   The index of the food item in the filtered menu.
     * @param menuObj The Menu object representing the menu selected by the customer.
     * @return The FoodItem object at the specified index in the filtered menu, or null if the index is invalid.
     */
    @Override
    public FoodItem findIndexedFoodItemInFilteredMenu(int index, Menu menuObj) {
        ArrayList<FoodItem> filteredMenu = new ArrayList<>();
        for (FoodItem foodItem : menuObj.getMenuArraylist()) {
            if (foodItem instanceof SetMeal) {
                filteredMenu.add(foodItem);
            }
        }

        if (index < 0 || index >= filteredMenu.size()) {
            System.out.println("Enter a valid index!");
            return null;
        } else {
            return filteredMenu.get(index);
        }
    }
}

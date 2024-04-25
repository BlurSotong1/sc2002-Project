package foms.management.filters.menufilters;

import foms.food.FoodItem;
import foms.management.lists.Menu;

import java.io.Serializable;
import java.util.ArrayList;

public interface MenuFilters extends Serializable {
    /**
     * Abstract method
     * Display the entire menu information
     * @param menu list of fooditem
     */
    public void displayFilteredMenu(ArrayList<FoodItem> menu);

    /**
     * abstract method to return the fooditem chosen based on index of filtered display
     * @param index is the index of the food in the arraylist in the filtered list.
     * @return the fooditem chosen based on index
     */
    public FoodItem findIndexedFoodItemInFilteredMenu(int index, Menu menuObj);
}

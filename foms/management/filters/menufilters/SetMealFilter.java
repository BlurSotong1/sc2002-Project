package foms.management.filters.menufilters;

import foms.food.FoodItem;
import foms.food.SetMeal;
import foms.management.lists.Menu;

import java.io.Serializable;
import java.util.ArrayList;

public class SetMealFilter implements MenuFilters, Serializable {

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
     * do not need to check whether index range is valid. will check in this function
     * @param index is the index of the food in the arraylist in the filtered list.
     * @param menuObj is the menu object of the branch,
     * @return fooditem found or null if invalid index.
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

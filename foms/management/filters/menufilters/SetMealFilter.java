package foms.management.filters.menufilters;

import foms.food.FoodItem;
import foms.food.SetMeal;
import foms.management.lists.Menu;

import java.util.ArrayList;

public class SetMealFilter implements MenuFilters {

    @Override
    public void displayFilteredMenu(ArrayList<FoodItem> menu) {
        System.out.println("Set Meals");
        int counter = 1;
        for (FoodItem foodItem: menu) {
            if (foodItem instanceof SetMeal) {
                System.out.printf("%d. %s\n",counter,foodItem.getName());
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

        if (index < 0 || index > filteredMenu.size()) {
            System.out.println("Enter a valid index!");
            return null;
        } else {
            return filteredMenu.get(index);
        }
    }
}

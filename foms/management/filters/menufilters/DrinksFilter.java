package foms.management.filters.menufilters;

import foms.food.Drinks;
import foms.food.FoodItem;
import foms.management.lists.Menu;

import java.util.ArrayList;

public class DrinksFilter implements MenuFilters{
    @Override
    public void displayFilteredMenu(ArrayList<FoodItem> menu) {
        System.out.println("Drinks");
        int counter = 1;
        for (FoodItem foodItem: menu) {
            if (foodItem instanceof Drinks) {
                System.out.printf("%d. %s\n",counter,foodItem.getName());
                counter++;
            }
        }
    }

    @Override
    public FoodItem findIndexedFoodItemInFilteredMenu(int index, Menu menuObj) {
        ArrayList<FoodItem> filteredMenu = new ArrayList<>();
        for (FoodItem foodItem : menuObj.getMenuArraylist()) {
            if (foodItem instanceof Drinks) {
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

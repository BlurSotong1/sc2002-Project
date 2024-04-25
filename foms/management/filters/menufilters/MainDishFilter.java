package foms.management.filters.menufilters;

import foms.food.FoodItem;
import foms.food.MainDish;
import foms.management.lists.Menu;

import java.util.ArrayList;

public class MainDishFilter implements MenuFilters{
    @Override
    public void displayFilteredMenu(ArrayList<FoodItem> menu) {
        System.out.println("Main Dish");
        int counter = 1;
        for (FoodItem foodItem: menu) {
            if (foodItem instanceof MainDish) {
                System.out.printf("%d. %s\n",counter,foodItem.getName());
            }
        }
    }

    @Override
    public FoodItem findIndexedFoodItemInFilteredMenu(int index, Menu menuObj) {
        ArrayList<FoodItem> filteredMenu = new ArrayList<>();
        for (FoodItem foodItem : menuObj.getMenuArraylist()) {
            if (foodItem instanceof MainDish) {
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

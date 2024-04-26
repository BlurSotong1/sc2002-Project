package foms.management.filters.menufilters;

import foms.food.FoodItem;
import foms.food.MainDish;
import foms.management.lists.Menu;

import java.io.Serializable;
import java.util.ArrayList;

public class MainDishFilter implements MenuFilters, Serializable {
    @Override
    public void displayFilteredMenu(ArrayList<FoodItem> menu) {
        System.out.println("Main Dish");
        int counter = 1;
        String s;
        for (FoodItem foodItem: menu) {
            if (foodItem instanceof MainDish) {
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

    @Override
    public FoodItem findIndexedFoodItemInFilteredMenu(int index, Menu menuObj) {
        ArrayList<FoodItem> filteredMenu = new ArrayList<>();
        for (FoodItem foodItem : menuObj.getMenuArraylist()) {
            if (foodItem instanceof MainDish) {
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

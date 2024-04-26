package foms.management.filters.menufilters;

import foms.food.FoodItem;
import foms.management.lists.Menu;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseMenuFilter implements MenuFilters, Serializable {

    @Override
    public void displayFilteredMenu(ArrayList<FoodItem> menu) {
        int counter = 1;
        String s;
        System.out.println("All Items");
        for (FoodItem foodItem: menu) {
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

    @Override
    public FoodItem findIndexedFoodItemInFilteredMenu(int index, Menu menuObj) {

        if (index < 0 || index >= menuObj.getMenuSize()) {
            System.out.println("Enter a valid index!");
            return null;
        } else {
            return menuObj.getMenuArraylist().get(index);
        }

    }
}

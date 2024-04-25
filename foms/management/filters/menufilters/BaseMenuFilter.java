package foms.management.filters.menufilters;

import foms.food.FoodItem;
import foms.management.lists.Menu;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseMenuFilter implements MenuFilters, Serializable {

    @Override
    public void displayFilteredMenu(ArrayList<FoodItem> menu) {
        int counter = 1;
        System.out.println("All Items");
        for (FoodItem foodItem: menu) {

                System.out.printf("%d. %s\n",counter,foodItem.getName());

        }
    }

    @Override
    public FoodItem findIndexedFoodItemInFilteredMenu(int index, Menu menuObj) {

        if (index < 0 || index > menuObj.getMenuSize()) {
            System.out.println("Enter a valid index!");
            return null;
        } else {
            return menuObj.getMenuArraylist().get(index);
        }

    }
}

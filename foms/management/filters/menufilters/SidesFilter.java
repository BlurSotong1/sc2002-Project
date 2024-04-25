package foms.management.filters.menufilters;

import foms.food.FoodItem;
import foms.food.Sides;
import foms.management.lists.Menu;

import java.util.ArrayList;

public class SidesFilter extends BaseMenuFilter implements MenuFilters{

    @Override
    public void displayFilteredMenu(ArrayList<FoodItem> menu) {
        int counter = 1;
        System.out.println("Sides");
        for (FoodItem foodItem: menu) {
            if (foodItem instanceof Sides) {
                System.out.printf("%d. %s\n",counter,foodItem.getName());
            }
        }
    }

    @Override
    public FoodItem findIndexedFoodItemInFilteredMenu(int index, Menu menuObj) {

        ArrayList<FoodItem> filteredMenu = new ArrayList<>();
        for (FoodItem foodItem : menuObj.getMenuArraylist()) {
            if (foodItem instanceof Sides) {
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

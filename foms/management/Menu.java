package foms.management;

import foms.food.FoodItem;
import foms.workers.ManagerWorker;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    /**
     * this branch's menu
     */
    private ArrayList<FoodItem> menu;

    /**
     * Constructor for Menu class.
     *
     */
    public Menu(){

    }



    /**
     * @param foodName is the foodname that you want to check if duplicate exists.
     * @return  FoodItem if there is a duplicate, null if no duplicate.
     */
    public FoodItem whetherExistInMenu(String foodName) {
        for (FoodItem foodItem: manager.getBranch().getMenu()) {
            if (foodItem.getName().equals(foodName)) { //dupe exists!
                return foodItem;
            }
        }
        return null;
    }

    public void addCreatedFoodItemToMenu(FoodItem foodItem) {

        System.out.printf("Please confirm that you want to add %s to menu.\n",foodItem.getName());
        System.out.println("Enter 1 to add, 2 to exit.");


        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {

                if (scanner.next().equals("1")) {
                    manager.getBranch().getMenu().add(foodItem);
                    System.out.printf("Added %s to menu\n", foodItem.getName());
                    return;
                } else if (scanner.next().equals("2")) {
                    System.out.println("Did not add item to menu.");
                    return;
                } else {
                    System.out.println("Enter 1 or 2!");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong.");
            }
        }
    }

    public void removeIndexedFoodItemFromMenu(int index) {
        FoodItem foodItem;
        try {
            foodItem = manager.getBranch().getMenu().get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Indexed out of range.");
            return;
        }

        System.out.printf("Please confirm that you want to remove %s to menu.\n",foodItem.getName());
        System.out.println("Enter 1 to remove, 2 to exit.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {

                if (scanner.next().equals("1")) {
                    manager.getBranch().getMenu().remove(foodItem);
                    System.out.printf("Removed %s from menu\n", foodItem.getName());
                    return;
                } else if (scanner.next().equals("2")) {
                    System.out.println("Did not remove item to menu.");
                    return;
                } else {
                    System.out.println("Enter 1 or 2!");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong.");
            }
        }
    }
}




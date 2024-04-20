package foms.workers;

import foms.food.Drink;
import foms.food.FoodItem;
import foms.food.MainDish;
import foms.food.Sides;
import foms.management.Branch;
import foms.management.OperationsOnMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * manager of the branch
 */

public class ManagerWorker extends StaffWorker {

    /**
     * is the class that enables manager to do their job through functions that change the menu.
     */
    OperationsOnMenu jobsOnMenu;
    /**
     * Constructor for manager class.
     * @param name    full name of the worker.
     * @param age     age of the worker.
     * @param gender  gender of the worker.
     * @param loginID loginID of the worker. Duplicate will be checked in previous function.
     * @param branch is the branch the manager works at.
     */
    public ManagerWorker(String name, int age, char gender, String loginID, Branch branch) {
        super(name, age, gender, loginID, branch);
        jobsOnMenu = new OperationsOnMenu(this);
        setRole('M');
    }
    //**********************************************************************************************\\


     /**
     * Food Item Adder Method Specific for managers only, accessed through operations object
     * Creates food item then passes the food item as argument to add to menu.
     */

    public void addFoodItemsToMenu() {
        Scanner scanner = new Scanner(System.in);

        String foodName;
        try {
            System.out.print("Enter the new food name (Enter 0 to exit): ");
            foodName = scanner.next();
            if (foodName.equals("0")) {
                System.out.println("Returning to previous page..");
                return;
            }
        } catch (Exception e) {
            System.out.println("Something Went Wrong");
            return;
        }

        if (jobsOnMenu.haveDuplicateFoodItems(foodName)) { //returns true if have dupe
            System.out.println("This food name already exists in system!\nReturning to Main Menu..");
            return;
        }

        //no dupe so can add food.

        int foodType;
        float price;


        //exception handling for input mismatch

        while (true) {
            try {
                System.out.print("Enter the food type (Enter 0 to exit): \n1: Main Dish \n2: Drinks \n3: Sides");
                foodType = scanner.nextInt();

                if (foodType == 0) { //quit adding
                    System.out.println("Exiting to main menu...");
                    return;
                }

                System.out.print("Enter the price: ");
                price = scanner.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch! enter a valid integer.");
            } catch (Exception e) {
                System.out.println("Something went wrong..");
            }


        }

        System.out.print("Enter the description (press 0 to exit): ");
        String description = scanner.nextLine();

        if (description.equals("0")) {
            System.out.println("Exiting to previous page..");
            return;
        }
        FoodItem food = null;
        while (food == null) {
            try {
                switch (foodType) {
                    case 0 -> {
                        System.out.println("Exiting to previous page...");
                        return;
                    }
                    case 1 -> food = new MainDish(foodName, price, description);
                    case 2 -> food = new Drink(foodName, price, description);
                    case 3 -> food = new Sides(foodName, price, description);
                    default -> System.out.println("Enter valid choice!");
                };
                if (food == null) {
                    System.out.print("Invalid! try again: \n1: Main Dish \n2: Drinks \n3: Sides\n");
                    foodType = scanner.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number!");
            }
        } //end of creating fooditem


        jobsOnMenu.addCreatedFoodItemToMenu(food);
    }


    @Override
    public String toString() {
        String manager = "Manager";
        return String.format(super.toString()+"%-20s", "Manager");
    }
}

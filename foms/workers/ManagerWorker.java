package foms.workers;

import foms.food.*;
import foms.management.branch.Branch;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * manager of the branch
 */

public class ManagerWorker extends StaffWorker implements Serializable {

    /**
     * Constructor for manager class.
     *
     * @param name    full name of the worker.
     * @param age     age of the worker.
     * @param gender  gender of the worker.
     * @param loginID loginID of the worker. Duplicate will be checked in previous function.
     * @param branch  is the branch the manager works at.
     */
    public ManagerWorker(String name, int age, char gender, String loginID, Branch branch) {
        super(name, age, gender, loginID, branch);
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

        if (getBranch().getMenu().isFoodItemInMenu(foodName) != null) { //if not null, means there is a duplicate
            System.out.println("This food name already exists in system.\nReturning to Main Menu..");
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
                scanner.next();
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
                    case 2 -> food = new Drinks(foodName, price, description);
                    case 3 -> food = new Sides(foodName, price, description);
                    default -> System.out.println("Enter valid choice!");
                };
                if (food == null) {
                    System.out.print("Invalid! try again: \n1: Main Dish \n2: Drinks \n3: Sides\n");
                    foodType = scanner.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number!");
                scanner.next();
            }
        } //end of creating fooditem


        getBranch().getMenu().addCreatedFoodItemToMenu(food);
    }


    /**
     * finds for object then removes if it exists in system.
     */
    public void removeFoodItemFromMenu() {
        Scanner scanner = new Scanner(System.in);

        getBranch().getMenu().displayMenu();

        int IndexOfFoodItemToRemove;
        int maxIndexOfMenu = getBranch().getMenu().getMenuSize();

        System.out.print("Enter the food index to remove (press 0 to exit): ");
        while (true) {
            try {

                IndexOfFoodItemToRemove = scanner.nextInt() -1; //-1 because display start from 1.
                if (IndexOfFoodItemToRemove == -1) {
                    System.out.println("Returning to previous page..");
                    return;

                } else if (IndexOfFoodItemToRemove >= 0 && IndexOfFoodItemToRemove <= maxIndexOfMenu) {
                    getBranch().getMenu().removeIndexedFoodItemFromMenu(IndexOfFoodItemToRemove);
                    break;

                } else {
                    System.out.println("Food not found. Enter a valid food index.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid food index.");
                scanner.next();
            }
        }


    }

    /**
     * updates the information of food items in the menu
     */
    public void updateFoodItemInformation() {
        Scanner scanner = new Scanner(System.in);
        int IndexOfFoodItemToUpdate;
        int maxIndexOfMenu = getBranch().getMenu().getMenuSize();

        System.out.print("Enter the food number to update (press 0 to exit): ");
        while (true) {
            try {

                IndexOfFoodItemToUpdate = scanner.nextInt() -1; //-1 because display start from 1.
                if (IndexOfFoodItemToUpdate == -1) {
                    System.out.println("Exiting to previous page..");
                    return;

                } else if (IndexOfFoodItemToUpdate >= 0 && IndexOfFoodItemToUpdate < maxIndexOfMenu) {
                    break;

                } else {
                    System.out.println("Enter within the valid range!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a number!");
            }
        }

        getBranch().getMenu().updateIndexedFoodItemFromMenu(IndexOfFoodItemToUpdate);
    }

    /**
     * Displays workers in the branch.
     */
    public void displayBranchWorkers(){
        getBranch().getWorkerList().displayWorkerListInBranch();
    }

    /**
     * @return Stringified details of manager
     */
    @Override
    public String toString() {
        return String.format("%-15s%-5d%-3c%-15s%-15s%-3c",
                getName(), getAge(), getGender(), getLoginID(), getBranch().getName(), getRole());
    }
}

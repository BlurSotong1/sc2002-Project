package foms.order;

import foms.food.FoodItem;
import foms.food.MainDish;
import foms.food.SetMeal;
import foms.management.Branch;
import foms.management.OperationsOnBranchList;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer {
    private Order cart;
    private static Branch branch;

    public void selectBranch(){
        int branchChoice;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Select your branch:");
            BranchList.displayBranchNames();
            try{
                branchChoice = scanner.nextInt();
                branch = BranchList.findBranch(branchChoice-1);

            }catch(InputMismatchException e){
                System.out.println("Please enter a valid integer for branch selection.");
            }
        }
    }

    public FoodItem addFoodItemToCart(){
        Scanner scanner = new Scanner(System.in);
        int foodCategory;
        FoodItem foodOrdered = null;
        while(true) {
            System.out.println("Select food category:");
            try{
                foodCategory = scanner.nextInt();
                switch(foodCategory){
                    case 1:
                        foodOrdered = new SetMeal();
                        break;
                    case 2:
                        foodOrdered = new MainDish();
                        break;
                    case 3:
                        foodOrdered = new Sides();
                        break;
                    case 4:
                        foodOrdered = new Drink();
                        break;
                }
                return foodOrdered;

            }catch(InputMismatchException e){
                System.out.println("Enter a valid integer.");
            }


        }
    }
}

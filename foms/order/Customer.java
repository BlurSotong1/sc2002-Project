package foms.order;

import foms.food.FoodItem;
import foms.food.MainDish;
import foms.food.SetMeal;
import foms.management.Branch;
import foms.management.BranchList;
import foms.management.Menu;
import foms.management.OperationsOnBranchList;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import static foms.order.OrderStatus.COMPLETED;

public class Customer implements Serializable {
    private Order cart;
    private static Branch branch;

    public void selectBranch(){
        int branchChoice;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try{
                System.out.println("Select your branch:");
                BranchList.displayBranchNames();
                branchChoice = scanner.nextInt();
                branch = BranchList.findBranch(branchChoice-1);
                break;
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid integer for branch selection.");
                scanner.next();
            }
        }
    }

    /**
     * display menu for users and ask users to select food item
     * @return Food Item which will be passed into Order class
     */
    public FoodItem addFoodItemToCart(){
        Scanner scanner = new Scanner(System.in);
        int foodCategory;
        FoodItem foodOrdered = null;
        while(true) {
            System.out.println("Select food category:");
            try{


            }catch(InputMismatchException e){
                System.out.println("Enter a valid integer.");
            }


        }
    }

    /**
     * display all items in cart and ask users to select cart item
     * @return Food Item which will be passed into Order class (removeIndexedFoodItemFromCart method)
     */
    public FoodItem removeFoodItem(){
        Scanner scanner = new Scanner(System.in);
        int foodChoice;
        while(true) {
            try{
                System.out.println("Select the food item that you want to delete from your cart:");
                cart.displayCart();
                foodChoice = scanner.nextInt();
                if(foodChoice>=1 || foodChoice <= cart.getCart().size()){
                    return cart.getCart().get(foodChoice-1);
                }else{
                    System.out.println("Invalid food item index.");
                }
            }catch(InputMismatchException e){
                System.out.println("Enter a valid input.");
                scanner.next();
            }
        }
    }

    /**
     * display all items in cart and asl users to select cart item
     * @return Food Item which will be passed into Order class (editFoodItem method)
     */
    public FoodItem editFoodItem(){
        Scanner scanner = new Scanner(System.in);
        int foodChoice;
            while(true){
                try{
                    System.out.println("Select the food item that you want to edit from your cart:");
                    cart.displayCart();
                    foodChoice = scanner.nextInt();
                    if(foodChoice>1 || foodChoice <= cart.getCart().size()){
                        FoodItem foodToBeEdited = cart.getCart().get(foodChoice-1);
                        return foodToBeEdited;
                    }
                }catch(InputMismatchException e){
                    System.out.println("Enter a valid input.");
                }catch (Exception e){
                    System.out.println(e.getMessage()+"Error occurred.");
                }
            }
    }

    public void collectOrder(int orderID){
        cart.setOrderStatus(COMPLETED);
    }


    /**
     * will update again after doing the Menu filter
     */
    public void displayMenu(){
        Menu.displayMenu();
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
    }
}

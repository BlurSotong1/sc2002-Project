package foms.order;

import foms.food.*;
import foms.management.branch.Branch;
import foms.management.lists.BranchList;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static foms.order.OrderStatus.COMPLETED;

/**
 * Represent a customer in foms.
 */
public class Customer implements Serializable {
    /**
     * Customer's order.
     */
    private Order order;
    /**
     * Customer's branch.
     */
    private Branch branch;

    /**
     * Customer choosing a branch.
     * Customer can only choose those that are open.
     */
    public void selectBranch(){
        int branchChoice;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try{
                System.out.println("Select your branch:");
                BranchList.displayBranchNames();
                branchChoice = scanner.nextInt();

                setBranch(BranchList.findBranch(branchChoice-1));
                if(!branch.getStatus()){
                    System.out.println("The branch is closed. Please select another branch.");
                } else {
                    break;
                }

            }catch(InputMismatchException e){
                System.out.println("Please enter a valid integer for branch selection.");
                scanner.next();
            }catch(Exception e){
                System.out.println(e.getMessage()+"Error occurred.");
            }
        }
    }

    /**
     * Display menu for users and ask users to select food item
     * New food item for the cart will be created here
     * Food Item will be passed into Order class by calling its method
     */
    public void addFoodItemToCart(){
        Scanner scanner = new Scanner(System.in);
        int filterType;
        int input;
        filterType = displayMenu();

        if (filterType == 0) { //exited display menu, so should exit this as well
            return;
        }

        while (true) {
            try {
                System.out.print("To view the menu again, press 0.\n" +
                        "To exit, press -1.\n" +
                        "To add food item, type in the number: ");
                input = scanner.nextInt();

                switch (input) {
                    case -1 -> {
                        System.out.println("Returning to main menu..");
                        return;
                    }

                    case 0 -> {
                        filterType = displayMenu();
                        if (filterType == 0) {
                            return;
                        }
                    }

                    default -> {
                        order.editFoodItem(input,filterType); // valid index checking is done in fooditems
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a number!");
            } catch (Exception e)  {
                System.out.println("something when wrong");
            }

        }





    }

    /**
     * Display all items in cart and ask users to select cart item
     * Index of Food Item will be passed into Order class (removeIndexedFoodItemFromCart method)
     */
    public void removeFoodItem(){
        Scanner scanner = new Scanner(System.in);
        int foodChoice;
        while (true) {
            try{
                System.out.print("Select the food item that you want to delete from your cart:\n " +
                        "(Enter 0 to exit)\n" +
                        "Enter your choice: ");

                order.displayCart();
                foodChoice = scanner.nextInt();


                if(foodChoice ==0){ // go back menu
                    System.out.println("Going back to main menu.");
                    return;
                }

                //valid indexing
                else if(foodChoice>=1 || foodChoice <= order.getCartSize()) {
                    order.removeIndexedFoodItem(foodChoice-1);
                    return;
                }

            }catch(InputMismatchException e){
                System.out.println("Enter a valid input.");
                scanner.next();
            }catch(Exception e){
                System.out.println(e.getMessage()+"Error occurred.");
            }
        }
    }

    /**
     * display all items in cart and asl users to select cart item
     * Food Item which will be passed into Order class (editFoodItem method)
     */
    public void editFoodItem(){
        Scanner scanner = new Scanner(System.in);
        //display order then ask person choose
        order.displayCart();

        int choice;
        int maxCartIndex = order.getCartSize() -1;
        while (true) {

            System.out.print("Enter the food item you wish to edit:" +
                    "\nPress 0 to exit.\n" +
                    "Enter your choice: ");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter a number!");
                scanner.next();
                continue;
            }

            if (choice == 0) { //terminate
                System.out.println("exiting..");
                return;

            } else if (choice >= 0 && choice <= maxCartIndex) { //valid range
                order.editFoodItem(choice);

            } else {
                System.out.println("Enter a valid range!");
            }

        }


    }

    /**
     * check out order method
     * customer will be brought to check our order and they will selecting dine in option here
     */
    public void checkOutOrder(){
        Scanner scanner = new Scanner(System.in);
        boolean dineInOption;
        int checkOutChoice;
        while(true) {
            System.out.println("Do you want to check out your order now?\n" +
                    "0. Continue browsing\n" +
                    "1. Check Out now");
            try {
                checkOutChoice = scanner.nextInt();
                if(checkOutChoice==0){
                    System.out.println("Going back to main menu...");
                    break;
                }
                else if(checkOutChoice==1){
                    System.out.println("Select your dine in option:\n" +
                            "0. Dine in\n" +
                            "1. Take Away");
                    dineInOption=scanner.nextBoolean();
                    order.setDineInOption(dineInOption);

                    CheckOutOrder customerCheckOut = new CheckOutOrder(this);
                    customerCheckOut.updateOrderStatus(this);

                }

            }catch(InputMismatchException e){
                System.out.println("Enter a valid integer.");
            }catch(Exception e){
                System.out.println(e.getMessage()+"Error occurred.");
             }
        }

    }

    /**
     * method for customer to collect their order.
     * @param orderID use orderID to identify their orders
     * once they collected, we will change the order status from READYTOPICKUP to COMPLETED
     * orderList will remove this order once this step is done
     */
    public void collectOrder(int orderID){
        order.setOrderStatus(COMPLETED);
    }


    public Branch getBranch() { return branch;}
    public void setBranch(Branch branch){
        this.branch=branch;
    }
    public ArrayList<FoodItem> getCart() {
        return order.getCart();
    }

    public int displayMenu () {
        return getBranch().getMenu().displayMenu();
    }

}

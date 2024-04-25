package foms.order;

import foms.food.*;
import foms.management.branch.Branch;
import foms.management.lists.BranchList;
import foms.order.payment.CheckOutProcess;
import foms.order.payment.Payment;

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
     * payment method that the customer will use to pay for their food.
     */
    private Payment paymentMethod;

    /**
     * Customer's option to check out.
     */
    private CheckOutProcess checkOutProcess;

    private CollectOrder collectOrder;

    /**
     * constructor for branch.
     * will call select branch function when creating customer.
     */
    public Customer() {
        Order order = new Order();
        selectBranch();
        this.collectOrder = new CollectOrder(this);
    }

    /**
     *  Customer choosing a branch.
     *      * Customer can only choose those that are open.
     * @return branch if chose correct one, null if close app.
     */

    public void selectBranch(){
        int branchChoice;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try{
                System.out.print("Select your branch (Enter 0 to exit): ");
                BranchList.displayBranchNames();
                branchChoice = scanner.nextInt();

                if (branchChoice == 0) {
                    System.out.println("Exiting... ");
                    return;
                }

                Branch branch = BranchList.findBranch(branchChoice-1);

                if(!branch.getStatus()){
                    System.out.println("The branch is closed. Please select another branch.");
                } else {
                    this.branch = branch;
                    return;
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
                        System.out.println("here");

                        order.addToCart(input,filterType,getBranch().getMenu()); // valid index checking is done in fooditems
                        System.out.println("here2");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a number!");
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
        if (order.getCart().isEmpty()) {
            System.out.println("cart is empty!");
            return;
        }

        while (true) {
            try{

                if (order.getCart().isEmpty()) {
                    System.out.println("cart is empty!");
                    return;
                }
                System.out.print("Select the food item that you want to delete from your cart:\n " +
                        "(Enter 0 to exit)\n" +
                        "Enter your choice: ");

                order.displayCart();
                foodChoice = scanner.nextInt();


                if(foodChoice == 0){ // go back menu
                    System.out.println("Going back to main menu.");
                    return;
                }

                //valid indexing
                else if(foodChoice>=1 || foodChoice <= order.getCartSize()) {
                    order.removeIndexedFoodItem(foodChoice-1);
                    return;
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid input.");
                scanner.next();
            } catch (Exception e) {
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
        if (order.getCart().isEmpty()) {
            System.out.println("Empty cart!");
            return;
        }
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
            } catch (Exception e) {
                System.out.println("error in customer edit menu");
                return;
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



    public Branch getBranch() { return branch;}

    public int displayMenu () {
        return getBranch().getMenu().displayMenu();
    }

    public Order getOrder() {
        return order;
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public CheckOutProcess getCheckOutProcess() {
        return checkOutProcess;
    }

    public CollectOrder getCollectOrder() {
        return collectOrder;
    }
}

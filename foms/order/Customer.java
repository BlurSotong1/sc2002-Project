package foms.order;

import com.sun.tools.javac.Main;
import foms.food.*;
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
                if(!branch.getStatus()){
                    System.out.println("The branch is closed. Please select another branch.");
                } else{
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
     * display menu for users and ask users to select food item
     * @return Food Item which will be passed into Order class
     */
    public void addFoodItemToCart(){
        Scanner scanner = new Scanner(System.in);
        int foodChoice;

        while(true) {
            try{
                System.out.println("Select food item to add into your cart:\n" +
                        "Press 0 to go back to main menu");
                Menu.displayMenu();
                foodChoice=scanner.nextInt();

                if(foodChoice ==0){
                    System.out.println("Going back to main menu.");
                    break;
                }
                else if(foodChoice>=1 || foodChoice <= Menu.getMenu().size()) {
                    FoodItem cartItem = Menu.getMenu().get(foodChoice-1);

                    if(cartItem instanceof SetMeal){
                        SetMeal setMeal = (SetMeal)cartItem;
                        cart.addToCart(setMeal);

                        System.out.println("What do you want for your sides:");
                        Menu.displayMenu("Sides");
                        int sidesChoice = scanner.nextInt();
                        Sides selectedSides = setMeal.getSides(sidesChoice-1);
                        setMeal.setSides(selectedSides);

                        System.out.println("What do you want for your drinks:");
                        Menu.displayMenu("Drink");
                        int drinkChoice = scanner.nextInt();
                        Drink selectedDrink = setMeal.getDrink(drinkChoice-1);
                        setMeal.setSides(selectedDrink);

                    }else if(cartItem instanceof MainDish){
                        MainDish mainDish = (MainDish)cartItem;
                        cart.addToCart(mainDish);

                    }else if(cartItem instanceof Drink){
                        Drink drink = (Drink) cartItem;
                        cart.addToCart(drink);

                    } else if(cartItem instanceof Sides){
                        Sides sides = (Sides) cartItem;
                        cart.addToCart(sides);

                    }

                }

            }catch(InputMismatchException e){
                System.out.println("Enter a valid integer.");
            }catch(Exception e){
                System.out.println(e.getMessage()+"Error occurred.");
            }
        }
    }

    /**
     * display all items in cart and ask users to select cart item
     * @return Food Item which will be passed into Order class (removeIndexedFoodItemFromCart method)
     */
    public void removeFoodItem(){
        Scanner scanner = new Scanner(System.in);
        int foodChoice;
        while(true) {
            try{
                System.out.println("Select the food item that you want to delete from your cart:\n" +
                        "Press 0 to go back to main menu");
                cart.displayCart();
                foodChoice = scanner.nextInt();
                if(foodChoice ==0){
                    System.out.println("Going back to main menu.");
                }
                else if(foodChoice>=1 || foodChoice <= cart.getCart().size()) {
                    cart.removeIndexedFoodItem(foodChoice);
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
     * @return Food Item which will be passed into Order class (editFoodItem method)
     */
    public void editFoodItem(){
        Scanner scanner = new Scanner(System.in);
        int foodChoice;
        int customiseChoice;
            while(true){
                try{
                    System.out.println("Select the food item that you want to edit from your cart:");
                    cart.displayCart();
                    foodChoice = scanner.nextInt();
                    if(foodChoice == 0){
                        System.out.println("Going back to main menu.");
                        break;

                    }else if(foodChoice>=1 || foodChoice <= cart.getCart().size()){
                        FoodItem itemToBeEdited = cart.getCart().get(foodChoice-1);
                        if(itemToBeEdited instanceof SetMeal){
                            System.out.println("Customising...\n" +
                                    "(1) Main\n" +
                                    "(2) Sides\n" +
                                    "(3) Drink\n" +
                                    "Press 0 to return.");
                            customiseChoice=scanner.nextInt();

                            if(customiseChoice==0){
                                System.out.println("Returning back...");
                                break;
                            }else if (customiseChoice==1){
                                MainDish selectedMainDish = (SetMeal)itemToBeEdited.getMainDish();
                            }else if (customiseChoice==2){
                                MainDish selectedSides = (SetMeal)itemToBeEdited.getSides();
                            }else if (customiseChoice==3){
                                MainDish selectedDrinks = (SetMeal)itemToBeEdited.getDrink();
                            }
                        }
                        cart.editFoodItem(itemToBeEdited);
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



}

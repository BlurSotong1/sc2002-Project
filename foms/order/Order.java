package foms.order;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import foms.food.*;

/**
 * Represents an order placed by customer
 */
public class Order implements Serializable {
    /**
     * The orderID of an order
     */
    private int orderID;
    /**
     * ArrayList of cart items
     */
    private ArrayList<FoodItem> cart;
    /**
     * Sum of the amount of all the cart items
     */
    private static double amount;
    /**
     * Order status of the order
     */
    private OrderStatus orderStatus;
    /**
     * dine in option of the order
     */
    private boolean dineInOption;

    /**
     * customer creates an order with the given orderID
     * FoodItem chosen will be added to cart
     * initial amount set to 0
     * initialOrderStatus set to PENDING
     * @param orderID The order's number which customer can refer and collect their food when it is being called
     */
    public Order(int orderID, ArrayList<FoodItem> cart) {
        this.orderID=orderID;
        this.cart=new ArrayList<>(cart);
        this.orderStatus = OrderStatus.PENDING; //default
    }

    /**
     * method to add food into cart
     * @param foodItem customer will be asked what menuItem they want to add in the Customer class
     * food item is already craeted in Customer class
     * add to cart , amount increases
     */
    public void addToCart(FoodItem foodItem){
        cart.add(foodItem);
        amount += foodItem.getPrice();
        System.out.println(foodItem.getName() + " is added into the cart.");
    }



    /**
     * method to delete food from cart
     * @param indexOfFoodToDelete customer will be asked what food to be deleted in Customer class
     *                            and the index of the food will be passed
     * checking whether it is in cart has already done in the Customer Class
     */
    public void removeIndexedFoodItem(int indexOfFoodToDelete){
        FoodItem cartItem = cart.get(indexOfFoodToDelete - 1);
        cart.remove(cartItem);
        amount -= cartItem.getPrice();
        System.out.println(cartItem.getName()+" is removed from the cart.");
    }

    /**
     * method to editCart
     * @param itemToBeEdited retrieves the selected FoodItem from the cart and
     *                       directly calls its customiseFoodItem() method.
     */
    public void editFoodItem(FoodItem itemToBeEdited){
        itemToBeEdited.customiseFoodItem();
    }

    /**
     * method to display and list all items in cart
     */
    public void displayCart(){
        if(cart.isEmpty()){
            System.out.println("Cart is empty.");
            return;
        }
        if(orderStatus==OrderStatus.PENDING) {
            System.out.println("Order ID: "+ getOrderID());
            System.out.println();
            for(int i=0; i<cart.size(); i++){
                System.out.println("("+(i+1)+") "+ cart.get(i) + " - " + cart.get(i).getPrice());
            }
        }
        System.out.println();

    }

    /**
     * Get order ID of the order
     * @return orderID of the order
     */
    public int getOrderID(){
        return orderID;
    }

    /**
     * Get Cart Items placed in the cart
     * @return cart items
     */
    public ArrayList<FoodItem> getCart(){
        return cart;
    }

    /**
     * Get the total price of items in cart
     * @return price of all items in cart
     */
    public double getAmount(){
        return amount;
    }

    /**
     * Get the order status for this order
     * @return order status
     */
    public OrderStatus getOrderStatus(){
        return orderStatus;
    }

    /**
     * Change the order status for this order
     * After paying - from PENDING to PREPARING
     * In the processing order - from PREPARING to READYTOPICKUP
     * After customer collected the food - from READYTOPICKUP to COMPLETED
     * If customer canceled order during payment - from PENDING to CANCELED
     * @param newOrderStatus this includes Order's new Order Status
     */
    public void setOrderStatus(OrderStatus newOrderStatus){
        this.orderStatus=newOrderStatus;
    }


    /**
     * make changes to dine in option for this order
     * @param dineInOption customer's choice of dine in option
     */
    public void setDineInOption(boolean dineInOption) {this.dineInOption=dineInOption;}
}

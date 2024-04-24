package foms.order;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import foms.food.*;

public class Order implements Serializable {
    private int orderID;
    private ArrayList<FoodItem> cart;
    private static double amount;
    private OrderStatus orderStatus;

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
     * @param cartItem customer will be asked what menuItem they want to add in the Customer class
     * check whether the menuItem is valid (downcasting)
     * add to cart , amount increase,
     */
    public void addToCart(FoodItem foodItem){
        cart.add(foodItem);
        amount += foodItem.getPrice();
        System.out.println(foodItem.getName() + " is added into the cart.");
    }



    /**
     * method to delete food from cart
     * @param foodToDelete customer will be asked what foodToBeDelete in Customer class
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
     * make use of displayCartItems function
     * retrieves the selected FoodItem from the cart and directly calls its customise() method.
     * This approach takes advantage of polymorphism,
     * allowing the correct customise() implementation to be invoked dynamically at runtime based on the actual type of the FoodItem.
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
            for(int i=0; i<cart.size(); i++){
                System.out.println("("+(i+1)+") "+ cart.get(i));
            }
        }
        System.out.println();

    }
    /**
     * method to viewSingleOrderDetails()
     * for receipt printing
     * customer can view their own order
     * multiple single order details to form multiple order details
     * for staffs and managers to view and process order accordingly
     * output - includes orderID, food item names, food prices
     */
    public void viewSingleOrderDetails(int inputOrderID) {
        for(Order order: OperationsOnOrderList.getOrderList()){
            if(order.getOrderID()==inputOrderID){
                StringBuilder details = new StringBuilder();		//create a StringBuilder is more efficient than concatenating strings directly
                details.append("Order ID: ").append(orderID).append("\n");	//append the orderID to identify the order
                details.append("Items:\n");
                for(FoodItem item : cart) {
                    details.append("- ").append(item.getName()).append(": $").append(item.getPrice()).append("\n");
                    //append the name and price of each food item to the details string
                }
                System.out.println(details.toString());
            }
        }
        System.out.println("OrderID not found. Please key in the correct OrderID.");
    }


    public int getOrderID(){
        return orderID;
    }
    public void setOrderID(int orderID){
        this.orderID=orderID;
    }
    public ArrayList<FoodItem> getCart(){
        return cart;
    }
    public void setCart(ArrayList<FoodItem>cart){
        this.cart = cart;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(double newAmount){
        this.amount = newAmount;
    }
    public OrderStatus getOrderStatus(){
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus newOrderStatus){
        this.orderStatus=newOrderStatus;
    }


}

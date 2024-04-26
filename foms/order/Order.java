package foms.order;
import java.io.Serializable;
import java.util.*;

import foms.food.*;
import foms.management.filters.menufilters.DrinksFilter;
import foms.management.filters.menufilters.MenuFilters;
import foms.management.lists.Menu;

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
    private double totalAmount = 0.0;
    /**
     * Order status of the order
     */
    private OrderStatus orderStatus = OrderStatus.PENDING;
    /**
     * dine in option of the order
     */
    private boolean dineInOption = true;

    /**
     * customer creates an order with the given orderID
     */
    public Order() {
        Random random = new Random();
        orderID = random.nextInt(8999) + 1000; //random orderID between 1000~9999
        cart = new ArrayList<FoodItem>();
    }

    /**
     * for copying objects over easily in orderlist.
     * @param order is the order i want to copy to put into arraylist
     */
    public Order(Order order) {
        orderID = order.orderID;
        cart = new ArrayList<>(order.getCart());
        dineInOption = order.dineInOption;
        orderStatus = order.orderStatus;
    }



    /**
     * control flow of adding food item to menu.
     * creates a new object and adds it to the cart, copying object from menu.
     * @param indexedFoodItem is the index of the food item that i need to retrieve from filtered menu.
     * @param filterType is the filter
     * @param menu is the menu object that has the menu list.
     */
    public void addToCart(int indexedFoodItem, int filterType, Menu menu){

        //the filter used
        MenuFilters filter = Menu.findWhichFilter(filterType);

        FoodItem fromMenu;
        //the index of the food
        fromMenu = filter.findIndexedFoodItemInFilteredMenu(indexedFoodItem,menu);
        if (fromMenu == null) { //return cus cannot create food.
            return;
        }

        FoodItem addNewItemToCart = switch (fromMenu.getClass().getSimpleName()) {

            case "SetMeal" -> new SetMeal(((SetMeal) fromMenu).getMainDish(), fromMenu.getPrice(), fromMenu.getDescription());

            case "MainDish" -> new MainDish(fromMenu.getName(), fromMenu.getPrice(), fromMenu.getDescription());

            case "Sides" -> new Sides(fromMenu.getName(), fromMenu.getPrice(), fromMenu.getDescription());

            case "Drinks" -> new Drinks(fromMenu.getName(), fromMenu.getPrice(), fromMenu.getDescription());

            default -> {
                System.out.println("Error in order add filtered food!");
                yield null; // or throw an exception or return a default value
            }
        };
        if (addNewItemToCart == null) return;

        if (addNewItemToCart instanceof SetMeal) {

            //TODO
        }

        cart.add(addNewItemToCart);
        totalAmount += addNewItemToCart.getPrice();
        System.out.printf("added %s to cart.\n", addNewItemToCart.getName());


    }

    /**
     * method to delete food from cart
     * @param indexOfFoodItemToDelete customer will be asked what food to be deleted in Customer class
     *                            and the index of the food will be passed
     * checking whether it is in cart has already done in the Customer Class
     */
    public void removeIndexedFoodItem(int indexOfFoodItemToDelete){
        FoodItem cartItem = cart.get(indexOfFoodItemToDelete);
        cart.remove(cartItem);
        totalAmount -= cartItem.getPrice();
        System.out.println(cartItem.getName()+" is removed from the cart.");
    }

    /**
     * method to editCart
     * @param index retrieves the selected FoodItem by indexing from the cart and
     * directly calls its customiseFoodItem() method.
     */
    public void editFoodItem(int index){

        getCart().get(index).customiseFoodItem();
    }

    /**
     * method to display and list all items in cart
     */
    public void displayCart(){

        if(cart.isEmpty()){
            System.out.println("Cart is empty.");
            return;
        }
        else if (orderStatus==OrderStatus.PENDING) {

            System.out.println("Order ID: "+ getOrderID());

            for(int i=0; i<cart.size(); i++){
                System.out.println((i+1)+". "+ cart.get(i) + " - " + cart.get(i).getPrice());
            }
        }

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
        return totalAmount;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    /**
     * Get the order status for this order
     * @return order status
     */
    public String getOrderStatusInString(){
        if (orderStatus.equals(OrderStatus.READYTOPICKUP)) {
            return "Ready to Pick Up";
        }

        if (orderStatus.equals(OrderStatus.PREPARING)) {
            return "Preparing";
        }

        if (orderStatus.equals(OrderStatus.PENDING)) {
            return "Pending";
        }
        return "Completed";
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

    public int getCartSize () {
        return cart.size();
    }
}

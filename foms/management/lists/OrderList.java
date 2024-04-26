package foms.management.lists;

import java.io.Serializable;
import java.util.ArrayList;

import foms.food.FoodItem;
import foms.order.Order;
import foms.order.OrderStatus;

/**
 * OrderList will collate all the Orders in the branch
 */
public class OrderList implements Serializable {

    private ArrayList<Order> orderList;

    public OrderList() {
        this.orderList = new ArrayList<>();
    }


    /**
     * method to add Order to Order List
     *
     * @param order order with OrderStatus PREPARING will be added to this OrderList
     */
    public void addOrderToOrderList(Order order) {
        Order newOrder = new Order(order);
            orderList.add(order);
    }



    /**
     * method to display every Order in the OrderList
     * use for loop and utilise displayCart method in order
     */
    public void displayOrderList() {
        if(orderList.isEmpty()){
            System.out.println("No order in the order list.");
            return;
        }
        System.out.println("Orders:");
        int i=1;
        for (Order order : orderList) {
            System.out.println(i + ". OrderID: "+order.getOrderID()+ ", status: "+order.getOrderStatus());
            i++;
        }
    }

    public Order findOrder(int index) throws IndexOutOfBoundsException{
        if(index>=0 && index<orderList.size()){
            return orderList.get(index);
        }else{
            throw new IndexOutOfBoundsException("Order not found!");
        }
    }

    /**
     * multiple single order details to form multiple order details
     * for staffs and managers to view and process order accordingly
     * once Order is added
     *
     * @param orderID is the order id of the order.
     */
    public void processOrder(int orderID) {

        for (Order order : orderList) {
            if (orderID == order.getOrderID()) {
                if (order.getOrderStatus() == OrderStatus.PREPARING) {
                    order.setOrderStatus(OrderStatus.READYTOPICKUP);
                    System.out.println("The order is ready to pick up now.");
                } else if (order.getOrderStatus() == OrderStatus.READYTOPICKUP) {
                    order.setOrderStatus(OrderStatus.CANCELLED);
                    System.out.println("The order has been cancelled due to over time.");
                    orderList.remove(order);
                }
            }
        }
    }



    // Getter and Setter methods
    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }
}

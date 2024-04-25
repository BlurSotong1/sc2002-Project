package foms.management.lists;

import java.io.Serializable;
import java.util.ArrayList;
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
        if(order.getOrderStatus().equals(OrderStatus.PREPARING)){
            Order order1 = new Order(order);
            orderList.add(order1);
        }else{
            System.out.println("Order is still pending.");
        }
    }

//    public void removeIndexedOrder(int index) {
//        if(index>=0 && index < orderList.size()){
//            orderList.remove(index);
//        }
//    }

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
        for (Order order : orderList) {
            System.out.println(order.getOrderStatus());
            order.displayCart();
        }
    }

//    public Order findOrder(int index) throws IndexOutOfBoundsException{
//        if(index>=0 && index<orderList.size()){
//            return orderList.get(index);
//        }else{
//            throw new IndexOutOfBoundsException("Order not found!");
//        }
//    }




    // Getter and Setter methods
    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }
}

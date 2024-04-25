package foms.management;

import java.util.ArrayList;
import foms.order.Order;
import foms.order.OrderStatus;
import foms.workers.StaffWorker;

/**
 * OrderList will collate all the Orders in the branch
 */
public class OrderList {

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
            orderList.add(order);
        }else{
            System.out.println("Order is still pending.");
        }
    }

    public void removeIndexedOrder(int index) {
        if(index>=0 && index < orderList.size()){
            orderList.remove(index);
        }
    }

    /**
     * method to display every Order in the OrderList
     * use for loop and utilise displayCart method in order
     * add a total amount and order status
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
            System.out.println("Total amount = " + order.getAmount());
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
     * @param orderID
     */
    public void processOrder(int orderID) {

        for (Order order : orderList) {
            if (orderID == order.getOrderID()) {
                if (order.getOrderStatus() == OrderStatus.PREPARING) {
                    order.setOrderStatus(OrderStatus.READYTOPICKUP);
                    System.out.println("Your order is ready to pick up now.");
                } else if (order.getOrderStatus() == OrderStatus.READYTOPICKUP) {
                    order.setOrderStatus(OrderStatus.COMPLETED);
                    System.out.println("Your order has completed now.");
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

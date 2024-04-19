package foms.management;

import java.util.ArrayList;
import foms.order.Order;
import foms.order.OrderStatus;
import foms.workers.StaffWorker;

public class OperationsOnOrderList {

    private StaffWorker staff;
    private static ArrayList<Order> orderList;

    public OperationsOnOrderList() {
        // Constructor
        this.orderList = new ArrayList<Order>(); // Initialize the list of orders
    }

    /**
     *  method to add Order to Order List
     * @param order order with OrderStatus PREPARING will be added to this OrderList
     */
    public static void addOrderToOrderList(Order order){
        orderList.add(order);
    }

    /**
     * method to display every Order in the OrderList
     * use for loop and utlise method viewSingleOrderDetails
     */
    public void displayOrderList() {
        for (Order order : orderList) {
            System.out.println("OrderID = " + order.getOrderID()); // Access orderID using a getter method
            order.viewSingleOrderDetails(order.getOrderID());
        }
    }

    /**
     * multiple single order details to form multiple order details
     * for staffs and managers to view and process order accordingly
     * once Order is added
     * @param orderID
     */
    public void processOrder(int orderID){

        for(Order order:orderList){
            if(orderID==order.getOrderID()){
                if(order.getOrderStatus() == OrderStatus.PREPARING){
                    order.setOrderStatus(OrderStatus.READYTOPICKUP);
                    System.out.println("Your order is ready to pick up now.");
                } else if(order.getOrderStatus() == OrderStatus.READYTOPICKUP) {
                    order.setOrderStatus(OrderStatus.COMPLETED);
                    System.out.println("Your order has completed now.");
                    orderList.remove(order);
                }
            }

        }
    }

    // Getter and Setter methods
    public static ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        OperationsOnOrderList.orderList = orderList;
    }

    public StaffWorker getStaff() {
        return staff;
    }

    public void setStaff(StaffWorker staff) {
        this.staff = staff;
    }
}

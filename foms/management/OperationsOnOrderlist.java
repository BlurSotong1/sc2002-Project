package foms.management;
import foms.order.Order;
import foms.order.OrderStatus;
import foms.workers.StaffWorker;

import java.util.ArrayList;
public class OperationsOnOrderList {
    private StaffWorker staff;
    private ArrayList<Order> orderList;

    public OperationsOnOrderList(ArrayList<Order>orderList) {
        // Constructor
        this.orderList = new ArrayList<>(Order); // Initialize the list of orders
    }

    public void displayOrderList() {
        for (Order order : orderList) {
            System.out.println("OrderID = " + order.getOrderID()); // Access orderID using a getter method
            order.viewSingleOrderDetails();
        }
    }

    /**
     * multiple single order details to form multiple order details
     * for staffs and managers to view and process order accordingly
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
    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public StaffWorker getStaff() {
        return staff;
    }

    public void setStaff(StaffWorker staff) {
        this.staff = staff;
    }
}

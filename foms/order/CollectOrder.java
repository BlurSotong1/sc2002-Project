package foms.order;

import java.io.Serializable;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static foms.order.OrderStatus.COMPLETED;

public class CollectOrder implements Serializable {

    Customer customer;

    public CollectOrder (Customer customer) {
        this.customer = customer;
    }

    //todo
    public void checkOrder() {
    }
    /**
     * method for customer to collect their order.
     * once they collected, we will change the order status from READYTOPICKUP to COMPLETED
     * orderList will remove this order once this step is done
     */
    public void collectOrder(){
//TODO
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            try {
                System.out.print("Enter your orderID to collect (Enter 0 to exit): ");
                input = scanner.nextInt();
                if (input == 0) {
                    System.out.println("returning..");
                    return;
                }
                for (Order order: customer.getBranch().getOrderList().getOrderList()) {
                    if (order.getOrderID() == input) { //correct order ID
                        if (order.getOrderStatus().equals(OrderStatus.READYTOPICKUP)) {
                            System.out.println("Order is ready for collection.");
                            System.out.println("You've picked your order up!");
                            customer.getBranch().getOrderList().getOrderList().remove(order);
                            return;
                        } else {
                            System.out.println("Your order is not ready for pick up.");
                            return;
                        }
                    }
                }

                System.out.println("Order not found!");
            } catch (InputMismatchException e) {
                System.out.println("Enter a number!");
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        }
    }
}

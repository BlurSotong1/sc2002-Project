package foms.workers;

import foms.management.branch.Branch;
import foms.order.Order;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StaffWorker extends Worker implements Serializable {
    /**
     * branch that the staff works at.
     */
    private Branch branch;


    /**
     * Constructor for manager class.
     * @param name    full name of the worker.
     * @param age     age of the worker.
     * @param gender  gender of the worker.
     * @param loginID loginID of the worker. Duplicate will be checked in previous function.
     * @param branch is the branch the manager works at.
     */
    public StaffWorker(String name, int age, char gender, String loginID, Branch branch) {
        super(name, age, gender, loginID);
        this.branch = branch;
        setRole('S');
    }

    @Override
    public String toString() {
        return String.format("%-15s%-5d%-3c%-15s%-15s%-3c",
                getName(), getAge(), getGender(), getLoginID(), getBranch().getName(), getRole());
    }

    /**
     * Process order
     */
    public void processOrder(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            getBranch().getOrderList().displayOrderList();
            System.out.println("Select the order to process (Enter 0 to exit): ");
            try {
                int choice = sc.nextInt();
                if (choice == 0) {
                    System.out.println("Returning to previous page..");
                    return;
                }
                Order order = getBranch().getOrderList().findOrder(choice - 1);
                getBranch().getOrderList().processOrder(order.getOrderID());
            }
            catch(InputMismatchException e)
            {
                System.out.println("Enter the order index (number) (Enter 0 to exit): ");
            }
            catch (Exception e){
                System.out.println(e.getMessage()+" Enter the order index (number) (Enter 0 to exit): ");
            }
        }
    }

    /**
     * display all orders in the list
     * single order details can be viewed subsequently
     */
    public void displayOrders(){
        Scanner sc = new Scanner(System.in);
        getBranch().getOrderList().displayOrderList();
        System.out.println("Select the order to display its details (Enter 0 to exit): ");
        while (true){
            try{
                int choice = sc.nextInt();
                if (choice ==0){
                    System.out.println("Returning to previous page..");
                    return;
                }
                Order order = getBranch().getOrderList().findOrder(choice-1);
                order.displayCart();
                return;
            }
            catch(InputMismatchException e)
            {
                  System.out.println("Enter the order index (number) (Enter 0 to exit): ");
            }
            catch (Exception e){
                System.out.println(e.getMessage()+" Enter the order index (number) (Enter 0 to exit): ");
            }
        }
    }




    /**
     * Gets the branch object of this worker.
     * @return this worker's branch object.
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * Changes the branch of this worker.
     * @param branch is the new branch of this worker.
     */
    public void setBranch(Branch branch){
        this.branch = branch;
    }
}

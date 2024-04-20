package foms.management;

import foms.food.FoodItem;
import foms.order.Order;
import foms.workers.Worker;

import java.util.ArrayList;

public class Branch {
    /**
     * name of this branch.
     */
    private String name;

    /**
     * location of this branch.
     */
    private String location;

    /**
     * maximum number of workers in this branch.
     */
    private int quota;

    /**
     * number of staff (excluding manager) in this branch.
     */
    private int numStaff;

    /**
     * number of managers in this branch.
     */
    private int numManager;

    /**
     * total number of staff in this branch.
     */
    private int totalNumStaff;

    /**
     * status of this branch. Open = true, Closed = false.
     */
    private boolean status;

    /**
     * list of workers in this branch.
     */
    private ArrayList<Worker> workerList;

    /**
     * list of orders in this branch.
     */
    private ArrayList<Order> orderList;

    /**
     * this branch's menu
     */
    private ArrayList<FoodItem> menu;

    /**
     * list of payment methods in this branch.
     */
    private ArrayList<Payment> paymentList;

    /**
     * Constructor for Branch Class
     * Create a new Branch.
     * @param name is the name of the branch.
     * @param location is the location of the branch.
     * @param quota is the maximum number of workers in the branch.
     * There is  0 staff, 0 manager, 0 total number of staff.
     * Create list of workers, list of orders, menu and list of payment methods in the branch.
     */
    public Branch(String name, String location, int quota) {
        this.name = name;
        this.location = location;
        this.quota = quota;
        this.numStaff = 0;
        this.numManager = 0;
        this.totalNumStaff = 0;
        this.status = true;
        this.workerList = new ArrayList<Worker>();
        this.orderList = new ArrayList<Order>();
        this.menu = new ArrayList<FoodItem>();
        this.paymentList = new ArrayList<Payment>();
    }

    /**
    * Gets the name of this branch.
    * @return this Branch's name.
    */
    public String getName() {
        return name;
    }

    /**
     * Gets the location of this branch.
     * @return this Branch's location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the maximum number of staff in this branch.
     * @return the maximum number of staff in this branch.
     */
    public int getQuota() {
        return quota;
    }

    /**
     * Gets the number of staff (excluding manager) in this branch.
     * @return number of staff (excluding manager) in this branch.
     */
    public int getNumStaff() {
        return numStaff;
    }

    /**
     * Gets the number of managers in this branch.
     * @return number of managers in this branch.
     */
    public int getNumManager() {
        return numManager;
    }

    /**
     * Gets the total number of staff in this branch.
     * @return total number of staff in this branch.
     */
    public int getTotalNumStaff() {
        return totalNumStaff;
    }

    /**
     * Gets the status of this branch.
     * @return status of this branch.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Gets the list of workers in this branch.
     * @return list of workers in this branch.
     */
    public ArrayList<Worker> getWorkerList(){
        return workerList;
    }

    /**
     * Gets the list of orders in this branch.
     * @return list of orders in this branch.
     */
    public ArrayList<Order> getOrderList(){
        return orderList;
    }

    /**
     * Gets this branch's menu.
     * @return this branch's menu.
     */
    public ArrayList<FoodItem> getMenu(){
        return menu;
    }

    /**
     * protected because only operationsOnMenu is accessing this in the same package.
     * @param foodItem is the fooditem you are adding to menu.
     */
    protected void addCreatedFoodItemToMenu(FoodItem foodItem) {
        menu.add(foodItem);
    }

    /**
     * Gets the list of payment methods in this branch.
     * @return list of payment methods in this branch.
     */
    public ArrayList<Payment> getPaymentList(){
        return menu;
    }


    /**
     * Changes the name of this branch.
     * @param name is the new name of this branch.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the location of this branch.
     * @param location is the new location of this branch.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Changes the maximum number of staff in this branch.
     * @param quota is the new maximum number of staff in this branch.
     */
    public void setQuota (int quota) {
        this.quota = quota;
    }

    /**
     * Changes the number of staff (excluding manager) in this branch.
     * @param numStaff is the new number of staff (excluding manager) in this branch.
     */
    public void setNumStaff(int numStaff) {
        this.numStaff = numStaff;
        totalNumStaff = this.numStaff + numManager;
    }

    /**
     * Changes the number of managers in this branch.
     * @param numManager is the new number of managers in this branch.
     */
    public void setNumManager(int numManager) {
        this.numManager = numManager;
        totalNumStaff = numStaff + this.numManager;
    }

    /**
     * Changes the total number of staff in this branch.
     * @param totalNumStaff is the new total number of staff in this branch.
     */
    public void setTotalNumStaff(int totalNumStaff) {
        this.totalNumStaff = totalNumStaff;
    }

    /**
     * Changes the status of this branch.
     * @param status is the new status of this branch.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Changes the list of workers this branch.
     * @param workerList is the new list of workers of this branch.
     */
    public void setWorkerList(ArrayList<Worker> workerList){
        this.workerList = workerList;
    }

    /**
     * Changes the list of orders this branch.
     * @param orderList is the new list of orders of this branch.
     */
    public void setOrderList(ArrayList<Order> orderList){
        this.orderList = orderList;
    }


    /**
     * Changes the list of payment methods this branch.
     * @param paymentList is the new list of payment methods this branch.
     */
    public void setPaymentList(ArrayList<Payment> paymentList){
        this.paymentList = paymentList;
    }

}

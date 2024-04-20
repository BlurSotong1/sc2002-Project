package foms.management;

import foms.food.FoodItem;
import foms.workers.AdminWorker;
import foms.workers.ManagerWorker;

public class OperationsOnMenu {
    /**
     * Operations on branch status is performed by manager worker.
     */
    private final ManagerWorker manager;

    /**
     * Constructor for OperationsOnBranchStatus class
     * @param manager is the manage worker performing the change of branch status.
     */
    public OperationsOnMenu(ManagerWorker manager){
        this.manager = manager;
    }

    /**
     * @param foodName is the foodname that you want to check if duplicate exists.
     * @return true if there is a duplicate, false if no duplicate.
     */
    public boolean haveDuplicateFoodItems(String foodName) {
        for (FoodItem foodItem: manager.getBranch().getMenu()) {
            if (foodItem.getName().equals(foodName)) { //dupe exists!
                return true;
            }
        }
        return false;
    }

    public void addCreatedFoodItemToMenu(FoodItem foodItem) {
        manager.getBranch().addCreatedFoodItemToMenu(foodItem);
    }


}

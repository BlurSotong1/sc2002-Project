package foms.management;

import foms.order.Order;

import java.util.ArrayList;

public class BranchList {
    /**
    * list of branches.
     */
    private static ArrayList<Branch> branchList;

    public BranchList() {
        this.branchList = new ArrayList<Branch>();
    }

    /**
     * Displays all the branch names.
     */
    public static void displayBranchNames() {
        int i;
        int length;

        length = branchList.size();
        for (i = 0; i < length; i++) {
            System.out.println(i+1 + ". " + branchList.get(i).getName());
        }
    }

    /**
     * Find a branch.
     * @param index is the index of the branch object in the array of branch objects
     * @return branch object corresponding to the index.
     */
    public static Branch findBranch(int index) throws IndexOutOfBoundsException{
        if (index >= 0 && index < branchList.size()) {
            return branchList.get(index);
        }
        else {
            throw new IndexOutOfBoundsException("Branch not found!");
        }
    }

}

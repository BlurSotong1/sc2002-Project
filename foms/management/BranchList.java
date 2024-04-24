package foms.management;

import java.util.ArrayList;
import java.util.Scanner;

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

    /*
     * Add a branch into list of branches.
     * @param branch is the Branch object.
     */
    public static void addCreatedBranch(Branch branch) {
        System.out.printf("Please confirm that you want to add %s.\n",branch.getName());
        System.out.println("Enter 1 to add (Enter 0 to exit): ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {

                if (scanner.next().equals("1")) {
                    branchList.add(branch);
                    System.out.printf("Added %s.\n", branch.getName());
                    return;
                } else if (scanner.next().equals("2")) {
                    System.out.println("Did not add branch.");
                    return;
                } else {
                    System.out.println("Enter 1 to add (Enter 0 to exit): ");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong.");
            }
        }
    }

    /**
     * Gets the list of branches.
     * @return list of branches.
     */
    public ArrayList<Branch> getBranchList() {
        return branchList;
    }
}

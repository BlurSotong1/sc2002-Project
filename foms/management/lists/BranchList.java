package foms.management.lists;

import foms.management.branch.Branch;
import foms.workers.AdminWorker;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BranchList implements Serializable {
    /**
    * list of branches.
     */
    private static ArrayList<Branch> branchList;

    public static void serializeBranchList() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BranchList.ser"))) {
            oos.writeObject(branchList);
        }
    }

    public static void deserializeBranchList()throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BranchList.ser"))) {
            branchList= (ArrayList<Branch>) ois.readObject();
        }
    }




    /**
     * Constructor for BranchList class.
     * Create a branch list.
     */
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
        System.out.println("List of branches:");
        for (i = 0; i < length; i++) {
            System.out.printf(i+1 + ". " + branchList.get(i).getName());
            if (branchList.get(i).getStatus()==false)
                System.out.printf(" (closed)");
            System.out.println();

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
    public void addCreatedBranch(Branch branch) {
        System.out.printf("Please confirm that you want to add %s.\n",branch.getName());
        System.out.println("Enter 1 to add (Enter 0 to exit): ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String choice = scanner.next();
                if (choice.equals("1")) {
                    branchList.add(branch);
                    System.out.printf("Added %s.\n", branch.getName());
                    displayBranchNames();
                    return;
                } else if (choice.equals("0")) {
                    System.out.println("Did not add branch.\nReturning to previous page..");
                    return;
                } else {
                    System.out.println("Enter 1 to add (Enter 0 to exit): ");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong.");
            }
        }
    }

    /*
     * Remove a branch from list of branches.
     * @param index is the index of the object.
     */
    public void removeBranchObject(Branch branch) {
        System.out.printf("Please confirm that you want to remove %s.\n",branch.getName());
        System.out.println("Enter 1 to remove (Enter 0 to exit):");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String choice = scanner.next();
                if (choice.equals("1")) {
                    branchList.remove(branch);
                    System.out.printf("Removed %s.\n", branch.getName());
                    displayBranchNames();
                    return;
                }
                else if (choice.equals("0")) {
                    System.out.println("Did not remove branch.\nReturning to previous page..");
                    return;
                }
                else {
                    System.out.println("Enter 1 to remove (Enter 0 to exit): ");
                }
            }
            catch (Exception e) {
                System.out.println("Something went wrong.");
            }
        }
    }

    /**
     * @param branchName is the name of branch that you want to check if duplicate exists.
     * @return  Branch if there is a duplicate, null if no duplicate.
     */
    public Branch isBranchInBranchList(String branchName) {
        for (Branch branch: branchList) {
            if (branch.getName().equals(branchName)) { //duplicate exists!
                return branch;
            }
        }
        return null;
    }

    /**
     * Gets the list of branches.
     * @return list of branches.
     */
    public ArrayList<Branch> getBranchList() {
        return branchList;
    }


}

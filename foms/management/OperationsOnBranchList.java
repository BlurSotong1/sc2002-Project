package foms.management;

import foms.workers.AdminWorker;

public class OperationsOnBranchList {
    /**
     * operations On branch list is performed by admin worker.
     */
    private static AdminWorker admin;

    /**
     * Constructor for OperationsOnBranchList class
     * @param admin is the admin worker performing the operations on branch list.
     */
    public OperationsOnBranchList(AdminWorker admin){
        this.admin = admin;
    }

    /**
     * Displays all the branch names.
     */
    public static void displayBranchNames() {
        int i;
        int length;

        length = admin.getBranchList().size();
        for (i = 0; i < length; i++) {
            System.out.println(i+1 + ". " + admin.getBranchList().get(i).getName());
        }
    }

    /**
     * Find a branch.
     * @param index is the index of the branch object in the array of branch objects
     * @return branch object corresponding to the index.
     */
    public static Branch findBranch(int index){
        return admin.getBranchList().get(index);
    }

}

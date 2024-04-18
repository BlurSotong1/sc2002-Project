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

    public static void displayBranch() {
        int i;
        int length;

        length = admin.getBranchList().size();
        for (i = 0; i < length; i++) {
            System.out.println(i+1 + ". " + admin.getBranchList().get(i).getName());
        }
    }

    public static Branch findBranch(int index){
        return admin.getBranchList().get(index);
    }

}

package foms.management;

import foms.workers.AdminWorker;

public class OperationsOnBranchList {
    /**
     * operations On branch list is performed by admin worker.
     */
    private AdminWorker admin;

    /**
     * Constructor for OperationsOnBranchList class
     * @param admin is the admin worker performing the operations on branch list.
     */
    public OperationsOnBranchList(AdminWorker admin){
        this.admin = admin;
    }

}

package foms.management;

import foms.workers.AdminWorker;

public class OperationsOnBranchList {
    /**
     * Operations On Branch List is performed by Admin Worker.
     */
    private AdminWorker admin;

    /**
     * Constructor for OperationsOnBranchList class
     * @param admin is the admin worker performing the operations on Branch List.
     */
    public OperationsOnBranchList(AdminWorker admin){
        this.admin = admin;
    }

}

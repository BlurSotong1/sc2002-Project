package foms.management;

import foms.workers.AdminWorker;

public class OperationsOnWorkerList {
    /**
     * Operations on worker list is performed by admin worker.
     */
    private AdminWorker admin;


    /**
     * Constructor for OperationsOnWorkerList class
     * @param admin is the admin worker performing operations on worker list.
     */
    public OperationsOnWorkerList(AdminWorker admin){
        this.admin = admin;
    }

}

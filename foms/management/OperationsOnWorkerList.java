package foms.management;

import foms.workers.AdminWorker;
import foms.workers.Worker;

public class OperationsOnWorkerList {
    /**
     * Operations on worker list is performed by admin worker.
     */
    private static AdminWorker admin;


    /**
     * Constructor for OperationsOnWorkerList class
     * @param admin is the admin worker performing operations on worker list.
     */
    public OperationsOnWorkerList(AdminWorker admin){
        this.admin = admin;
    }

    /**
     * Find worker.
     * @param loginID is the unique login ID of the worker
     * @return admin object corresponding to the login ID or null if no worker is found
     */
    public static Worker findWorker(String loginID){
        for (Branch branch : admin.getBranchList())
            for(Worker worker : branch.getWorkerList())
                if (loginID.equals(worker.getLoginID()))
                    return worker;
        if (loginID.equals(admin.getLoginID()))
            return admin;
        return null;
    }

}

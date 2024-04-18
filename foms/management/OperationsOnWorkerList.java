package foms.management;

import java.util.Scanner;

import foms.workers.AdminWorker;
import foms.workers.*;

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

    /**
     * Add worker into the worker list of a branch
     * @param newWorker
     * @return true if worker is successfully added and false if a login ID has been taken.
     */
    public boolean addWorker(Worker newWorker){
        // Check Duplicate
        if (findWorker(newWorker.getLoginID()) != null)
            return false;
        //Add worker
        if (newWorker instanceof StaffWorker)
            ((StaffWorker)newWorker).getBranch().getWorkerList().add(newWorker);
        return true;
    }

    /**
     * Remove worker from the worker list of a branch
     * @return true if worker is successfully removed and false if worker cannot be found.
     */
    public void removeWorker() {
        Scanner sc = new Scanner(System.in);
        Worker worker = null;
        do {
            System.out.println("Enter the worker's login ID to be removed: ");
            /**
             * TODO display list of worker
             */
            String loginID = sc.nextLine();
            if (loginID.equals("<"))
                return;
            //find worker
            worker = findWorker(loginID);
            if (worker == null)
                System.out.println("Worker not found.");
        } while (worker == null);

        //remove worker
        if (worker instanceof StaffWorker) {
            ((StaffWorker) worker).getBranch().getWorkerList().remove(worker);
        }
    }
}

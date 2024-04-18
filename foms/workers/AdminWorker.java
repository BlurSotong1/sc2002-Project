package foms.workers;

import java.util.ArrayList;

import foms.management.*;


public class AdminWorker extends Worker{

    /**
     * list of branches.
     */
    private ArrayList<Branch> branchList;

    /**
     * Admin worker perform operations on branch list.
     */
    private OperationsOnBranchList jobsOnBranchList;

    /**
     * Admin worker perform operations on worker list of one of the branch.
     */
    private OperationsOnWorkerList jobsOnWorkerList;

    /**
     * Admin worker perform change on one of the branch's status.
     */
    private OperationsOnBranchStatus changeBranchStatus;

    /**
     * Admin worker perform change on one of the branch's payment status.
     */
    private OperationsOnPaymentStatus changePaymentStatus;

    /**
     * Constructor for AdminWorker class.
     * Create an admin worker.
     * @param name    full name of the worker.
     * @param age     age of the worker.
     * @param gender  gender of the worker.
     * @param loginID loginID of the worker.
     */
    public AdminWorker(String name, int age, char gender, String loginID) {
        super(name, age, gender, loginID);
        this.branchList = BranchList.getBranchList();
        this.jobsOnBranchList = new OperationsOnBranchList(this);
        this.jobsOnWorkerList = new OperationsOnWorkerList(this);
        this.changeBranchStatus = new OperationsOnBranchStatus(this);
        this.changePaymentStatus = new OperationsOnPaymentStatus(this);
    }

    /**
     * Gets the list of branches.
     * @return list of branches.
     */
    public ArrayList<Branch> getBranchList() {
        return branchList;
    }



}

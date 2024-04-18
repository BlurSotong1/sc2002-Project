package foms.workers;

import java.util.ArrayList;

import foms.management.*;


public class AdminWorker extends Worker{

    /**
     * list of branches.
     */
    private static ArrayList<Branch> branchList;

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
        setRole('A');
        branchList = new ArrayList<Branch>();
        jobsOnBranchList = new OperationsOnBranchList(this);
        jobsOnWorkerList = new OperationsOnWorkerList(this);
        changeBranchStatus = new OperationsOnBranchStatus(this);
        changePaymentStatus = new OperationsOnPaymentStatus(this);
    }

    /**
     * Gets the list of branches.
     * @return list of branches.
     */
    public ArrayList<Branch> getBranchList() {
        return branchList;
    }

    /**
     * Use the operations on branch list.
     * @return operations on branch list.
     */
    public OperationsOnBranchList getJobsOnBranchList() {
        return jobsOnBranchList;
    }

    /**
     * Use the operations on worker list.
     * @return operations on worker list.
     */
    public OperationsOnWorkerList getJobsOnWorkerList(){
        return jobsOnWorkerList;
    }

    /**
     * Change the branch status.
     * @return change branch status.
     */
    public OperationsOnBranchStatus getChangeBranchStatus(){
        return changeBranchStatus;
    }

    /**
     * Change the payment status of a branch.
     * @return change payment status of a branch.
     */
    public OperationsOnPaymentStatus getChangePaymentStatus(){
        return changePaymentStatus;
    }




}

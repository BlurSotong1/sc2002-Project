package foms.workers;

import java.util.ArrayList;

import foms.management.*;


public class AdminWorker extends Worker{

    /**
     * list of branches.
     */
    private ArrayList<Branch> branchList;

    /**
     * operations on branch list.
     */
    private OperationsOnBranchlist jobsOnBranchList;

    /**
     * operations on worker list of one of the branch.
     */
    private OperationsOnWorkerlist jobsOnWorkerList;

    /**
     * change one of the branch's status.
     */
    private OperationsOnBranchStatus changeBranchStatus;

    /**
     * change one of the branch's payment status.
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
        this.branchList = BranchList.branchList;
        this.jobsOnBranchList = new OperationsOnBranchlist(this);
        this.jobsOnWorkerList = new OperationsOnWorkerlist(this);
        this.changeBranchStatus = new OperationsOnBranchStatus(this);
        this.changePaymentStatus = new OperationsOnPaymentStatus(this);
    }


}

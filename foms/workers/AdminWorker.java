package foms.workers;

import java.io.Serializable;
import java.util.ArrayList;

import foms.management.*;


public class AdminWorker extends Worker implements Serializable {

    /**
     * list of branches.
     */
    private BranchList branchList;

    /**
     * list of all workers in the company.
     */
    private WorkerList allWorkersList;

    /**
     * Admin worker perform operations on branch list.
     */
    private OperationsOnBranchList jobsOnBranchList;

    /**
     * Admin worker perform operations on worker list of one of the branch.
     */
    private OperationsOnWorkerList jobsOnWorkerList;

    /**
     * Admin worker perform operations on a branch..
     */
    private OperationsOnBranchStatus jobsOnBranch;

    /**
     * Admin worker perform operations on payment list of one of the branch.
     */
    private OperationsOnPaymentList jobsOnPaymentList;

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
        branchList = new BranchList();
        allWorkersList = new WorkerList();
        jobsOnBranchList = new OperationsOnBranchList(this);
        jobsOnWorkerList = new OperationsOnWorkerList(this);
        jobsOnBranch = new OperationsOnBranchStatus(this);
        jobsOnPaymentList = new OperationsOnPaymentList(this);
    }

    /**
     * Gets the list of branches.
     * @return list of branches.
     */
    public BranchList getBranchList() {
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
     * Use the operations on branch.
     * @return change branch status.
     */
    public OperationsOnBranchStatus getJobsOnBranch(){
        return jobsOnBranch;
    }

    /**
     * Use the operations on payment list.
     * @return change payment status of a branch.
     */
    public OperationsOnPaymentList getJobsOnPaymentList(){
        return jobsOnPaymentList;
    }

}

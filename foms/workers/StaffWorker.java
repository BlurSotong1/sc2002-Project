package foms.workers;

import foms.management.Branch;

public class StaffWorker extends Worker{
    /**
     * branch that the staff works at.
     */
    private Branch branch;

    /**
     * is the class that enables staff to do their job through functions that deals with menu.
     */
    private Order order;

    /**
     * Constructor for manager class.
     * @param name    full name of the worker.
     * @param age     age of the worker.
     * @param gender  gender of the worker.
     * @param loginID loginID of the worker. Duplicate will be checked in previous function.
     * @param branch is the branch the manager works at.
     */
    public StaffWorker(String name, int age, char gender, String loginID, Branch branch) {
        super(name, age, gender, loginID);
        this.branch = branch;
        jobsOnOrderlist = new OperationsOnOrderList();
        setRole('S');
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "%-15s", branch);
    }

    /**
     * Gets the branch object of this worker.
     * @return this worker's branch object.
     */
    public Branch getBranch() {
        return branch;
    }

    /**
     * Changes the branch of this worker.
     * @param branch is the new branch of this worker.
     */
    public void setBranch(Branch branch){
        this.branch = branch;
    }
}

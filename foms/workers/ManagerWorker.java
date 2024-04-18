package foms.workers;

import foms.management.Branch;
import foms.management.OperationsOnMenu;

/**
 * manager of the branch
 */

public class ManagerWorker extends StaffWorker {
    /**
     * is the branch that the manager works at.
     */
    Branch branch;
    /**
     * is the class that enables manager to do their job through functions that change the menu.
     */
    OperationsOnMenu jobsOnMenu;
    /**
     * Constructor for manager class.
     *
     * @param name    full name of the worker.
     * @param age     age of the worker.
     * @param gender  gender of the worker.
     * @param loginID loginID of the worker. Duplicate will be checked in previous function.
     * @param branch is the branch the manager works at.
     */
    public ManagerWorker(String name, int age, char gender, String loginID, Branch branch) {
        super(name, age, gender, loginID);
        this.branch = branch;
        jobsOnMenu = new OperationsOnMenu(this);
    }

    @Override
    public String toString() {
        return String.format(super.toString()+"%-20s Manager",branch.getName() );
    }
}

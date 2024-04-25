package foms.management;

import java.util.ArrayList;

import foms.management.filters.workerfilters.WorkerFilters;
import foms.workers.AdminWorker;
import foms.workers.Worker;

public class AllWorkersList {
    /**
     * list of all workers in the company.
     */
    private static ArrayList<Worker> allWorkersList;

    /**
     * admin worker in the company.
     */
    private static AdminWorker admin;

    /**
     * Constructor for AllWorkerList class.
     * Create a worker list.
     * @param admin admin worker in the company
     */
    public AllWorkersList(AdminWorker admin) {
        this.admin = admin;
        this.allWorkersList = new ArrayList<Worker>();
        allWorkersList.add(admin);
    }

    /*
     * Add a worker into list of workers.
     * @param worker is the Worker object.
     */
    public void addCreatedWorker(Worker worker) {
        allWorkersList.add(worker);
    }

    /*
     * Display list of workers in the coompany.
     * @param filter is the filter selected to display worker list.
     */
    public void displayWorkerListInSystem(WorkerFilters filter) {
        filter.display(allWorkersList);
    }

    /**
     * @param loginID is the name of branch that you want to check if duplicate exists.
     * @return  Branch if there is a duplicate, null if no duplicate.
     */
    public static Worker isWorkerInSystem(String loginID) {
        for(Worker worker : allWorkersList) {
            if (worker.getLoginID().equals(loginID)) // duplicate exist.
                return worker;
        }
        return null;
    }

    /**
     * Gets the list of workers in a branch.
     * @return list of workers in a branch.
     */
    public ArrayList<Worker> getWorkerList() {
        return allWorkersList;
    }

}

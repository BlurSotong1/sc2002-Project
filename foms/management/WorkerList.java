package foms.management;

import java.util.ArrayList;

import foms.workers.Worker;

public class WorkerList {

    /**
     * list of workers in a branch.
     */
    private ArrayList<Worker> workerList;

    /**
     * Constructor for WorkerList class.
     * Create a worker list.
     */
    public WorkerList() {
        this.workerList = new ArrayList<Worker>();
    }

    /*
     * Add a worker into list of workers.
     * @param worker is the Worker object.
     */
    public void addCreatedWorker(Worker worker) {
        workerList.add(worker);
    }

    /**
     * Gets the list of workers in a branch.
     * @return list of workers in a branch.
     */
    public ArrayList<Worker> getWorkerList() {
        return workerList;
    }
}

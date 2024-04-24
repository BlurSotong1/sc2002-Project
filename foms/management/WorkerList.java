package foms.management;

import java.util.ArrayList;

import foms.workers.Worker;

public class WorkerList {

    /**
     * list of workers in a branch.
     */
    private static ArrayList<Worker> workerList;

    /**
     * Constructor for WorkerList class.
     * Create a worker list.
     */
    public WorkerList() {
        this.workerList = new ArrayList<Worker>();
    }
}

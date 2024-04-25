package foms.management.filters.workerfilters;

import foms.workers.Worker;

import java.util.ArrayList;
public class NoFilter {

    /**
     * Display worker's information (no filter)
     * @param workerList list of workers
     */
    public void display(ArrayList<Worker> workerList) {
        for(Worker worker : workerList) {
            System.out.println(worker.toString());
        }
    }
}

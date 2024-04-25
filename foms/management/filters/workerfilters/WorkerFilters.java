package foms.management.filters.workerfilters;

import foms.workers.Worker;

import java.util.ArrayList;

public interface WorkerFilters {

    /**
     * return list of workers (filtered)
     * @param workerList list of workers
     * @return filtered list of workers
     */
    public ArrayList<Worker> filter(ArrayList<Worker> workerList);

}
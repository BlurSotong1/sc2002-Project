package foms.management.filters.workerfilters;

import foms.workers.Worker;

import java.io.Serializable;
import java.util.ArrayList;

public interface WorkerFilters extends Serializable {

    /**
     * return list of workers (filtered)
     * @param workerList list of workers
     * @return filtered list of workers
     */
    public ArrayList<Worker> filter(ArrayList<Worker> workerList);

}
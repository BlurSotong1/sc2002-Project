package foms.management.filters.workerfilters;
import foms.workers.Worker;

import java.util.ArrayList;
public interface WorkerFilters {

    /**
     * Abstract method
     * Display worker's information
     * @param workerList list of workers
     */
    public void display(ArrayList<Worker> workerList);
}

package foms.workerfilters;

import foms.workers.Worker;

import java.util.ArrayList;
public class NoFilter {
    public void display(ArrayList<Worker> workerList) {
        for(Worker worker : workerList) {
            System.out.println(worker.toString());
        }
    }
}

package foms.management.filters.workerfilters;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import foms.workers.Worker;

public class AgeFilter implements WorkerFilters{

    /**
     * return list of workers (gender filtered)
     * @param workerList list of workers
     * @return filtered list of workers
     */
    public ArrayList<Worker> filter(ArrayList<Worker> workerList) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Worker> filteredList= new ArrayList<Worker>();
        System.out.println("Enter age to filter (Enter 0 to exit): ");
        while (true) {
            try {
                int choice = sc.nextInt();
                if (choice==0) {
                    System.out.println("Returning to previous page..");
                    return null;
                }
                else {
                    for(Worker worker: workerList) {
                        if (worker.getAge()==choice) {
                            filteredList.add(worker);
                            return filteredList;
                        }
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Not a number. Enter age to filter (Enter 0 to exit): ");
                continue;
            }
            catch (Exception e) {
                System.out.println("Something Went Wrong");
                return null;
            }
        }

    }

}

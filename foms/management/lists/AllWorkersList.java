package foms.management.lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import foms.management.filters.workerfilters.*;
import foms.management.filters.workerfilters.WorkerFilters;
import foms.workers.AdminWorker;
import foms.workers.Worker;

public class AllWorkersList implements Serializable {
    /**
     * list of all workers in the company.
     */
    private static ArrayList<Worker> allWorkersList;

    /**
     * admin worker in the company.
     */
    private AdminWorker admin;

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

    /**
     * Add a worker into list of workers.
     * @param worker is the Worker object.
     */
    public void addCreatedWorker(Worker worker) {
        allWorkersList.add(worker);
    }

    /**
     * Remove worker from list of workers.
     * @param worker is the Worker object.
     */
    public void removeWorkerObject(Worker worker) {
        allWorkersList.remove(worker);
    }

    /**
     * Display worker list in the company.
     */
    public void displayWorkerListInSystem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("List of workers");
        for (Worker worker: allWorkersList) {
            System.out.println(worker.toString());
        }

        System.out.println("Do you want to filter the list of workers? Y/N");

        while(true) {
            String choice = sc.nextLine();
            if (choice.equals("Y")) {
                filterWorkerListInSystem(allWorkersList);
                return;
            }
            else if (choice.equals("N")) {
                System.out.println("Returning to previous page..");
                return;
            }
            else {
                System.out.println("Do you want to filter the list of workers? Y/N");
                continue;
            }
        }
    }


    /**
     * Filter worker list
     * @param workerList list of workers
     */
    public void filterWorkerListInSystem(ArrayList<Worker> workerList) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Worker> filteredList;

        System.out.println("Filters:\n1.Role\n2.Age\n3.Gender\n4.Branch");
        System.out.println("Enter filter (Enter 0 to exit): ");
        while(true) {
            try {
                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    WorkerFilters roleFilter = new RoleFilter();
                    filteredList = roleFilter.filter(workerList);
                    if (filteredList == null)
                        return;
                    for (Worker worker: filteredList) {
                        System.out.println(worker.toString());
                    }
                    break;
                }
                else if (choice.equals("2")) {
                    WorkerFilters ageFilter = new AgeFilter();
                    filteredList = ageFilter.filter(workerList);
                    if (filteredList == null)
                        return;
                    for (Worker worker: filteredList) {
                        System.out.println(worker.toString());
                    }
                    break;
                }
                else if (choice.equals("3")) {
                    WorkerFilters genderFilter = new GenderFilter();
                    filteredList = genderFilter.filter(workerList);
                    if (filteredList == null)
                        return;
                    for (Worker worker: filteredList) {
                        System.out.println(worker.toString());
                    }
                    break;
                }
                else if (choice.equals("4")) {
                    WorkerFilters branchFilter = new BranchFilter();
                    filteredList = branchFilter.filter(workerList);
                    if (filteredList == null)
                        return;
                    for (Worker worker: filteredList) {
                        System.out.println(worker.toString());
                    }
                    break;
                }
                else if (choice.equals("0")) {
                    System.out.println("Returning to previous page..");
                    return;
                }
                else {
                    System.out.println("Filters:\n1.Role\n2.Age\n3.Gender\n4.Branch");
                    System.out.println("Invalid filter. Enter filter (number) (Enter 0 to exit): ");
                    continue;
                }
            }
            catch (Exception e) {
                System.out.println("Something Went Wrong");
                return;
            }
        }

        System.out.println("Do you want to add another filter? Y/N");
        while(true) {
            String choice = sc.nextLine();
            if (choice.equals("Y")) {
                filterWorkerListInSystem(filteredList);
                return;
            }
            else if (choice.equals("N")) {
                System.out.println("Returning to previous page..");
                return;
            }
            else {
                System.out.println("Do you want to add another filter? Y/N");
                continue;
            }
        }

    }

    /**
     * Find a worker.
     * @param index is the index of the worker object in the array of worker objects
     * @return worker object corresponding to the index.
     */
    public static Worker findWorker(int index) throws IndexOutOfBoundsException{
        if (index >= 0 && index < allWorkersList.size()) {
            return allWorkersList.get(index);
        }
        else {
            throw new IndexOutOfBoundsException("Worker not found!");
        }
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

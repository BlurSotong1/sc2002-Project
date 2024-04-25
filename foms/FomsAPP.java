package foms;

import foms.food.*;
import foms.management.branch.Branch;
import foms.management.lists.AllWorkersList;
import foms.management.lists.BranchList;
import foms.management.lists.WorkerList;
import foms.order.Customer;
import foms.workers.AdminWorker;
import foms.workers.ManagerWorker;
import foms.workers.StaffWorker;
import foms.workers.Worker;

import java.io.*;
import java.util.Scanner;

public class FomsAPP implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //calling state
        AdminWorker admin = null;
        try {
            FileInputStream fileIn = new FileInputStream("AdminInfo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            admin = (AdminWorker) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            System.out.println("this didnt work");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        BranchList.deserializeBranchList();
        AllWorkersList.deserializeAllWorkerList();


        createTestCases(admin);







/*
        Scanner scanner = new Scanner(System.in);
        System.out.println("**************************************************");
        System.out.println("Welcome to FastFood Operations Management System!");
        System.out.println("To start ordering, enter: 1");
        System.out.println("To login as a Staff Member, enter: 2.");
        System.out.print("Enter your choice: ");




        for (Branch branch2: admin.getBranchList().getBranchList())
            System.out.printf("\n\n%s", branch2.getName());
       // Customer customer = new Customer();
       // System.out.println("Welcome Customer!");
        customer.addFoodItemToCart(); */

        admin.getJobsOnWorkerList().addWorker();


    }

    public void allCustomerActions() {
        System.out.println("1. Place New Order");

        Customer customer = new Customer();

        customer.addFoodItemToCart();

        System.out.println("2. Check Existing Order");



    }

    public static void createTestCases(AdminWorker admin) {
        BranchList dummy = new BranchList(); //quota is staff+manager
        Branch branch1 = new Branch("NTU", "NTU", 5); //2 manager demo transfer manager to branch 2
        dummy.addCreatedBranch(branch1);
        Branch branch2 = new Branch("JE", "JE", 5); //1 manager, demo add 1, transferred from 1
        dummy.addCreatedBranch(branch2);
        Branch branch3 = new Branch("JP", "JP", 10); //2 manager, demo add worker but fail?
        dummy.addCreatedBranch(branch3);


        FoodItem item1 = new Sides("FRIES",3.20,"yummy and crispy fries");
        branch1.getMenu().addCreatedFoodItemToMenu(item1);
        branch2.getMenu().addCreatedFoodItemToMenu(item1);
        FoodItem item2 = new Sides("FRIES",3.50,"yummy and crispy fries");
        branch3.getMenu().addCreatedFoodItemToMenu(item2);

        FoodItem item3 = new MainDish("3PC TENDER", 15.30,"Yummy tenders");
        branch1.getMenu().addCreatedFoodItemToMenu(item3);
        FoodItem item4 = new MainDish("3PC TENDER", 19.30,"Yummy tenders");
        branch2.getMenu().addCreatedFoodItemToMenu(item4);
        branch3.getMenu().addCreatedFoodItemToMenu(item4);

        FoodItem item5 = new Drinks("Pepsi", 2.10,"coke is superior");
        branch1.getMenu().addCreatedFoodItemToMenu(item5);
        branch2.getMenu().addCreatedFoodItemToMenu(item5);
        branch3.getMenu().addCreatedFoodItemToMenu(item5);

        FoodItem item6 = new Drinks("Chicken Mcnugget", 6.10,"i love chicken nuggets");
        branch1.getMenu().addCreatedFoodItemToMenu(item6);
        branch2.getMenu().addCreatedFoodItemToMenu(item6);
        branch3.getMenu().addCreatedFoodItemToMenu(item6);


        // Creating a new Drinks item
        FoodItem item7 = new Drinks("Coca-Cola", 2.00, "Coca-Cola is a classic choice");
        branch1.getMenu().addCreatedFoodItemToMenu(item7);
        branch2.getMenu().addCreatedFoodItemToMenu(item7);
        branch3.getMenu().addCreatedFoodItemToMenu(item7);


        // Creating a new MainCourse item
        FoodItem item8 = new SetMeal(item3, 21.30, "Creamy pasta with bacon and cheese");
        branch1.getMenu().addCreatedFoodItemToMenu(item8);
        branch2.getMenu().addCreatedFoodItemToMenu(item8);
        branch3.getMenu().addCreatedFoodItemToMenu(item8);


        // Creating Cajun Fish food item
        FoodItem item9 = new MainDish("Cajun Fish", 5.6, "Spicy Cajun-seasoned fish fillet");
        branch1.getMenu().addCreatedFoodItemToMenu(item9);
        branch2.getMenu().addCreatedFoodItemToMenu(new MainDish("Cajun Fish", 6.0, "Spicy Cajun-seasoned fish fillet"));
        branch3.getMenu().addCreatedFoodItemToMenu(item9);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //create staff workers
        AllWorkersList allWorkersList = new AllWorkersList(admin);

        Worker worker1 = new StaffWorker("kumar Blackmore",42,'M',"KumarBlack",branch1);
        branch1.getWorkerList().addCreatedWorker(worker1);
        allWorkersList.addCreatedWorker(worker1);

        Worker worker2 = new StaffWorker("Emily White", 28, 'F', "EmilyW", branch1);
        branch1.getWorkerList().addCreatedWorker(worker2);
        allWorkersList.addCreatedWorker(worker2);

        Worker worker3 = new StaffWorker("James Johnson", 35, 'M', "JamesJ", branch1);
        branch1.getWorkerList().addCreatedWorker(worker3);
        allWorkersList.addCreatedWorker(worker3);

        Worker worker4 = new StaffWorker("Sophia Garcia", 23, 'F', "SophiaG", branch1);
        branch1.getWorkerList().addCreatedWorker(worker4);
        allWorkersList.addCreatedWorker(worker4);


        Worker worker5 = new StaffWorker("Daniel Smith", 30, 'M', "DanielS", branch2);
        branch2.getWorkerList().addCreatedWorker(worker5);
        allWorkersList.addCreatedWorker(worker5);

        Worker worker6 = new StaffWorker("Olivia Martinez", 25, 'F', "OliviaM", branch2);
        branch2.getWorkerList().addCreatedWorker(worker6);
        allWorkersList.addCreatedWorker(worker6);

        Worker worker7 = new StaffWorker("Michael Brown", 33, 'M', "MichaelB", branch2);
        branch2.getWorkerList().addCreatedWorker(worker7);
        allWorkersList.addCreatedWorker(worker7);

        Worker worker8 = new StaffWorker("Ava Johnson", 27, 'F', "AvaJ", branch2);
        branch2.getWorkerList().addCreatedWorker(worker8);
        allWorkersList.addCreatedWorker(worker8);

        // Creating worker 9 for branch3
        Worker worker9 = new StaffWorker("Ethan Davis", 31, 'M', "EthanD", branch3);
        branch3.getWorkerList().addCreatedWorker(worker9);
        allWorkersList.addCreatedWorker(worker9);


        Worker worker10 = new StaffWorker("Isabella Taylor", 26, 'F', "IsabellaT", branch3);
        branch3.getWorkerList().addCreatedWorker(worker10);
        allWorkersList.addCreatedWorker(worker10);

        Worker worker11 = new StaffWorker("William Anderson", 29, 'M', "WilliamA", branch3);
        branch3.getWorkerList().addCreatedWorker(worker11);
        allWorkersList.addCreatedWorker(worker11);

        Worker worker12 = new StaffWorker("Sophie Wilson", 34, 'F', "SophieW", branch3);
        branch3.getWorkerList().addCreatedWorker(worker12);
        allWorkersList.addCreatedWorker(worker12);

        Worker worker13 = new StaffWorker("Alexander Martinez", 32, 'M', "AlexanderM", branch3);
        branch3.getWorkerList().addCreatedWorker(worker13);
        allWorkersList.addCreatedWorker(worker13);

        Worker worker14 = new StaffWorker("Charlotte Brown", 28, 'F', "CharlotteB", branch3);
        branch3.getWorkerList().addCreatedWorker(worker14);
        allWorkersList.addCreatedWorker(worker14);

        Worker worker15 = new StaffWorker("Benjamin Garcia", 30, 'M', "BenjaminG", branch3);
        branch3.getWorkerList().addCreatedWorker(worker15);
        allWorkersList.addCreatedWorker(worker15);

        Worker worker16 = new StaffWorker("Amelia Jones", 27, 'F', "AmeliaJ", branch3);
        branch3.getWorkerList().addCreatedWorker(worker16);
        allWorkersList.addCreatedWorker(worker16);

        Worker worker17 = new StaffWorker("Matthew Miller", 33, 'M', "MatthewM", branch3);
        branch3.getWorkerList().addCreatedWorker(worker17);
        allWorkersList.addCreatedWorker(worker17);

        Worker worker18 = new StaffWorker("Ella Hernandez", 25, 'F', "EllaH", branch3);
        branch3.getWorkerList().addCreatedWorker(worker18);
        allWorkersList.addCreatedWorker(worker18);


        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////


        Worker manager1 = new ManagerWorker("hehe",31,'F',"heheChoonggi",branch1);
        branch1.getWorkerList().addCreatedWorker(manager1);
        allWorkersList.addCreatedWorker(manager1);

        // Creating 1 more manager for branch1
        Worker manager2 = new ManagerWorker("Haha", 35, 'M', "Haha123", branch1);
        branch1.getWorkerList().addCreatedWorker(manager2);
        allWorkersList.addCreatedWorker(manager2);

// Creating 1 manager for branch2
        Worker manager3 = new ManagerWorker("Soojin", 40, 'F', "Soojin123", branch2);
        branch2.getWorkerList().addCreatedWorker(manager3);
        allWorkersList.addCreatedWorker(manager3);

// Creating 2 managers for branch3
        Worker manager4 = new ManagerWorker("Doyeon", 38, 'F', "Doyeon2", branch3);
        branch3.getWorkerList().addCreatedWorker(manager4);
        allWorkersList.addCreatedWorker(manager4);

        Worker manager5 = new ManagerWorker("Jihoon", 36, 'M', "Jihoon4", branch3);
        branch3.getWorkerList().addCreatedWorker(manager5);
        allWorkersList.addCreatedWorker(manager5);


    }

}

/*Branch branch = new Branch("jp", "jp",15);
        AdminWorker choonggi = new AdminWorker("choonggi",20,'M',"choonggi001");
        choonggi.getJobsOnBranchList().addBranch();

        try {
            FileOutputStream fileOut = new FileOutputStream("AdminInfo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(choonggi);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("we are screwed");
        }

      BranchList.serializeBranchList();
        AllWorkersList.serializeALlWorkerList();*/
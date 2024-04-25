package foms;

import foms.food.Drinks;
import foms.food.FoodItem;
import foms.food.MainDish;
import foms.food.Sides;
import foms.management.branch.Branch;
import foms.management.lists.AllWorkersList;
import foms.management.lists.BranchList;
import foms.order.Customer;
import foms.workers.AdminWorker;

import java.io.*;
import java.util.Scanner;

public class FomsAPP implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BranchList dummy = new BranchList();
        Branch branch1 = new Branch("NTU", "NTU", 18);
        dummy.addCreatedBranch(branch1);
        Branch branch2 = new Branch("JE", "JE", 18);
        dummy.addCreatedBranch(branch2);
        Branch branch3 = new Branch("JP", "JP", 18);
        dummy.addCreatedBranch(branch3);
        Branch branch4 = new Branch("HOUGANG", "HOUGANG", 18);
        dummy.addCreatedBranch(branch4);

        FoodItem item1 = new Sides("FRIES",3.20,"yummy and crispy fries");
        branch1.getMenu().addCreatedFoodItemToMenu(item1);
        branch2.getMenu().addCreatedFoodItemToMenu(item1);
        FoodItem item2 = new Sides("FRIES",3.50,"yummy and crispy fries");
        branch3.getMenu().addCreatedFoodItemToMenu(item2);
        branch4.getMenu().addCreatedFoodItemToMenu(item2);

        FoodItem item3 = new MainDish("3PC TENDER", 15.30,"Yummy tenders");
        branch1.getMenu().addCreatedFoodItemToMenu(item3);
        branch4.getMenu().addCreatedFoodItemToMenu(item3);
        FoodItem item4 = new MainDish("3PC TENDER", 19.30,"Yummy tenders");
        branch2.getMenu().addCreatedFoodItemToMenu(item4);
        branch3.getMenu().addCreatedFoodItemToMenu(item4);

        FoodItem item5 = new Drinks("Pepsi", 2.10,"coke is superior");
        branch1.getMenu().addCreatedFoodItemToMenu(item5);
        branch2.getMenu().addCreatedFoodItemToMenu(item5);
        branch3.getMenu().addCreatedFoodItemToMenu(item5);
        branch4.getMenu().addCreatedFoodItemToMenu(item5);


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
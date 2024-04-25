package foms;

import foms.management.branch.Branch;
import foms.order.Customer;
import foms.workers.AdminWorker;

import java.io.*;
import java.util.Scanner;

public class FomsAPP implements Serializable {
    public static void main(String[] args) {
/*
        AdminWorker choonggi = new AdminWorker("choonggi",20,'M',"choonggi001");
        try {
            FileOutputStream fileOut = new FileOutputStream("AdminInfo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(choonggi);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("we are screwed");
        }
*/
        // intro
        AdminWorker adminWorker = new AdminWorker("choonggi",20,'M',"choonggi001");
        Branch branch = new Branch("jp", "jp",15);
        try {
            FileInputStream fileIn = new FileInputStream("AdminInfo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            adminWorker = (AdminWorker) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            System.out.println("this didnt work");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        adminWorker.getBranchList().addCreatedBranch(branch);

        Scanner scanner = new Scanner(System.in);
        System.out.println("**************************************************");
        System.out.println("Welcome to FastFood Operations Management System!");
        System.out.println("To start ordering, enter: 1");
        System.out.println("To login as a Staff Member, enter: 2.");
        System.out.print("Enter your choice: ");

        System.out.println("Welcome Customer!");
        Customer customer = new Customer();

    }

    public void allCustomerActions() {
        System.out.println("1. Place New Order");

        Customer customer = new Customer();



        System.out.println("2. Check Existing Order");



    }
}

package foms;

import foms.management.branch.Branch;
import foms.workers.AdminWorker;

import java.io.*;
import java.util.Scanner;

public class FomsAPP implements Serializable {
    public static void main(String[] args) {


        // intro
        AdminWorker adminWorker = null;
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

        System.out.printf("%s\n\n", adminWorker.toString());

        Scanner scanner = new Scanner(System.in);
        System.out.println("**************************************************");
        System.out.println("Welcome to FastFood Operations Management System!");
        System.out.println("To start ordering, enter: 1");
        System.out.println("To login as a Staff Member, enter: 2.");
        System.out.print("Enter your choice: ");


    }
}

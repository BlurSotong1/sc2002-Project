package foms.management.branch;

import foms.workers.Worker;

import java.io.Serializable;
import java.util.Scanner;

import foms.management.lists.AllWorkersList;

public class loginSystemCtrl implements Serializable {
    /**
     * TODO check the arraylist for all workers.
     * @return Worker if the loginID exists and matches the password/ null if either one is incorrect.
     */

    public static Worker loginToSystemAsWorker(){
        String loginID;
        String loginPassword;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter your login ID (press 0 to exit): ");
                loginID = scanner.nextLine();

                if (loginID.equals("0")) { //return to main menu
                    System.out.println("Exiting login...");
                    return null;
                }

                System.out.print("Enter your login Password: ");
                loginPassword = scanner.nextLine();


                //find for worker then check if password is equal

                Worker loggingInWorker;

               loggingInWorker = AllWorkersList.isWorkerInSystem(loginID);


                if (loggingInWorker != null) {
                    if (loginPassword.equals(loggingInWorker.getLoginPassword())) {
                        //id and password matches.
                        return loggingInWorker;
                    }
                }
                System.out.println("Incorrect ID or Password!");

            } catch (Exception e) {
                System.out.println("Unknown Error Occurred");
            }
        }

    }
}

package foms.management;

import foms.workers.Worker;

import java.util.Scanner;

import static jdk.dynalink.linker.support.Guards.isNotNull;

public class loginSystemCtrl {
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
                loginID = scanner.next();

                if (loginID.equals("0")) { //return to main menu
                    System.out.println("Exiting login...");
                    return null;
                }

                System.out.print("Enter your login Password: ");
                loginPassword = scanner.next();


                //find for worker then check if password is equal

                Worker loggingInWorker;

                loggingInWorker = OperationsOnWorkerList.findWorker(loginID);


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

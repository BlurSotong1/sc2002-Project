package foms.workers;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An abstract class that will be used to store all workers in an arraylist
 */
public abstract class Worker {
    /**
     * fullname of the worker.
     */
    private String name;
    /**
     * age of the worker.
     */
    private int age;
    /**
     * gender of the worker.
     */
    private char gender;
    /**
     * login ID of the worker. will not be changeable once set.
     */
    private final String loginID;
    /**
     * login Password of the worker. Default password is default, changeable later on.
     */
    private String loginPassword = "default";

    /**
     * Constructor for worker class.
     * @param name full name of the worker.
     * @param age age of the worker.
     * @param gender gender of the worker.
     * @param loginID loginID of the worker. Duplicate will be checked in previous function.
     */
    public Worker(String name, int age, char gender, String loginID) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.loginID = loginID;


    }
    /**
     * Displays the information of the worker in the following order:
     * name, loginID, age, gender, role.
     */
    public String toString() {
        return String.format("%-20s %-15s %-5d %-5c", name, loginID, age, gender);
    }

    /**
     * Used to change password of the User. must enter previous password to make changes to current password.
     */
    public void changePassword() {

        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print("Enter your current password: (press 0 to exit)");
            input = scanner.next();
            try {
                if (input.equals("0")) {
                    System.out.println("Returning...");
                    scanner.close();
                    return;
                }
                if (!loginPassword.equals(input)) { //Not equal then wrong password
                    System.out.println("Wrong Password!");
                } else {
                    System.out.print("Enter your new password: ");
                    this.loginPassword = scanner.next();
                    System.out.println("Successfully changed your password!");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid Password!");
            }
        }
    }

    /**
     * TODO check the arraylist for all workers.
     * @param loginID is the login id of the worker.
     * @param loginPassword is the login password of the worker.
     * @return Worker if the loginID exists and matches the password/ null if either one is incorrect.
     */
    public static Worker loginToSystemAsWorker(String loginID, String loginPassword) {
        for (Worker worker: AdminWorker.getAllWorkersList().getWorkerList()) {
            if (worker.loginID.equals(loginID)){
                if (worker.loginPassword.equals(loginPassword)) {
                    return worker;
                } else { //wrong password so return null.
                    return null;
                }
            }
        }
        return null;
    }

}

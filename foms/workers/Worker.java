package foms.workers;

import foms.management.OperationsOnWorkerList;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An abstract class that will be used to store all workers in an arraylist
 */
public abstract class Worker {
    /**
     * first name and last name of this worker.
     */
    private String name;
    /**
     * age of this worker.
     */
    private int age;
    /**
     * gender of this worker.
     */
    private char gender;
    /**
     * role of this worker;
     */
    private char role;
    /**
     * login ID of this worker. It will not be changeable once set.
     */
    private final String loginID;
    /**
     * login Password of this worker. Default password is default, changeable later on.
     */
    private String loginPassword = "default";

    /**
     * Constructor for Worker class.
     * @param name first name and last name of the worker.
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
     * Displays the information of this worker in the following order:
     * name, loginID, age, gender, role.
     */
    public String toString() {
        return String.format("%-20s %-15s %-5d %-3c", name, loginID, age, gender);
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

    public static Worker loginToSystemAsWorker(String loginID, String loginPassword){
        return OperationsOnWorkerList.findWorker(loginID); // return worker object if have, return null is do not have
    }

    /** public static Worker loginToSystemAsWorker(String loginID, String loginPassword) {
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
    }*/

    /**
     * Gets the name of this worker
     * @return this worker's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the age of this worker
     * @return this worker's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the gender of this worker
     * @return this worker's gender.
     */
    public char getGender() {
        return gender;
    }

    /**
     * Gets the role of this worker
     * @return this worker's role.
     */
    public char getRole() {
        return role;
    }

    /**
     * Gets the login ID of this worker
     * @return this worker's login ID.
     */
    public String getLoginID() {
        return loginID;
    }

    /**
     * Gets the login password of this worker
     * @return this worker's login password.
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * Changes the name of this worker.
     * @param name is the new name of this worker.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Changes the age of this worker.
     * @param age is the new age of this worker.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Changes the gender of this worker.
     * @param gender is the new gender of this worker.
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Changes the role of this worker.
     * @param role is the new role of this worker.
     */
    public void setRole(char role) {
        this.role = role;
    }
}

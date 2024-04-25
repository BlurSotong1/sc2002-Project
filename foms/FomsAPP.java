package foms;

import foms.food.*;
import foms.management.branch.Branch;
import foms.management.branch.loginSystemCtrl;
import foms.management.lists.AllWorkersList;
import foms.management.lists.BranchList;
import foms.management.lists.WorkerList;
import foms.order.Customer;
import foms.workers.AdminWorker;
import foms.workers.ManagerWorker;
import foms.workers.StaffWorker;
import foms.workers.Worker;

import java.io.*;
import java.util.ArrayDeque;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FomsAPP implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Deserialisation
//        AdminWorker admin = null;
//        try {
//            FileInputStream fileIn = new FileInputStream("AdminInfo.ser");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            admin = (AdminWorker) in.readObject();
//            in.close();
//            fileIn.close();
//        } catch (IOException e) {
//            System.out.println("this didnt work");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        BranchList.deserializeBranchList();
//        AllWorkersList.deserializeAllWorkerList();

        AdminWorker admin = new AdminWorker("choonggi",20,'M',"Choonggi001");
        createTestCases(admin);

        //Start of Program
        Scanner scanner = new Scanner(System.in);
        System.out.println("**************************************************");
        System.out.println("Welcome to FastFood Operations Management System!");
        System.out.println("To start ordering, enter: 1");
        System.out.println("To login as a Staff Member, enter: 2.");
        System.out.print("Enter your choice: ");

        while (true){
            try {
                int choice = scanner.nextInt();
                if (choice == 1) {
                    allCustomerActions();
                    serialisationBeforeQuitting(admin);
                    return;
                }
                else if (choice == 2) {
                    Worker worker = loginSystemCtrl.loginToSystemAsWorker();
                    System.out.println("Login successful.");

                    //check if user is first login
                    if (worker.getLoginPassword().equals("default")) {
                        System.out.println("Enter your new password:");
                        String newPassword = scanner.nextLine();
                        worker.setLoginPassword(newPassword);
                    }
                    if (worker instanceof ManagerWorker){
                        allManagerActions((ManagerWorker)worker);
                        serialisationBeforeQuitting(admin);
                        return;
                    }
                    else if (worker instanceof StaffWorker){
                        allStaffActions((StaffWorker)worker);
                        serialisationBeforeQuitting(admin);
                        return;
                    }
                    else if (worker instanceof AdminWorker){
                        allAdminActions((AdminWorker)admin);
                        serialisationBeforeQuitting(admin);
                        return;
                    }


                }
                else{
                    System.out.print("Invalid choice. Enter again: ");
                    continue;
                }
            }catch (InputMismatchException e){
                System.out.print("Invalid choice. Enter again: ");
                scanner.next();
                continue;
            }
        }




    }

    private static void allCustomerActions() {
        Customer customer = new Customer();

        String input;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("What do you want to do?" +
                        "\n1. Place new Order" +
                        "\n2. Check for Order Status" +
                        "\n3. Collect your Order" +
                        "\n4. Select a different Branch" +
                        "\n0. Exit to Previous Screen" +
                        "\nEnter Your Choice: ");


                input = scanner.next();

                switch (input) {
                    case "0" -> {
                        System.out.println("Quitting the Application...");
                        return;
                    }
                    case "1" -> {
                        placeNewOrder(customer);
                    }
                    case "2" -> {
                        customer.getCollectOrder().checkOrder();
                    }
                    case "3" -> {
                        customer.getCollectOrder().collectOrder();
                    }
                    case "4" -> {
                        customer.selectBranch();
                    }
                    default -> System.out.println("Invalid input. Please enter a valid option.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number!");
            } catch (Exception e) {
                System.out.println("Error occurred");
                scanner.next();
            }
        }
    }


    private static void placeNewOrder(Customer customer) {

        String input;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Placing New Order" +
                        "\n1. Add food item to cart" +
                        "\n2. Edit food items in cart" +
                        "\n3. Delete food item from cart" +
                        "\n4. Check out" +
                        "\n0. Exit to Previous Screen" +
                        "\nEnter Your Choice: ");


                input = scanner.next();

                switch (input) {
                    case "0" -> {
                        System.out.println("Quitting place order...");
                        return;
                    }
                    case "1" -> {
                        System.out.println("Adding food item to cart...");
                        customer.addFoodItemToCart();
                    }
                    case "2" -> {
                        System.out.println("Editing food items in cart...");
                        customer.editFoodItem();
                    }

                    case "3" -> {
                        System.out.println("Removing food item from cart...");
                        customer.removeFoodItem();
                    }
                    case "4" -> {
                        System.out.println("Checking out...");
                        customer.getCheckOutProcess().CheckOut();
                    }
                    default -> System.out.println("Invalid input. Please enter a valid option.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number!"); }
//            } catch (Exception e) {
//                System.out.println("Error occurrexd");
//
//            }
        }
    }

    private static void allManagerActions(Worker manager){


    }

    private static void allStaffActions(StaffWorker staff){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome "+staff.getName());
        System.out.println();
        while (true){
            try{
                System.out.println("1. Display orders");
                System.out.println("2. Process order");
                System.out.println();
                System.out.println("0. Exit");
                System.out.println("What would you like to do?");

                String choice = sc.nextLine();
                if (choice.equals("1"))
                    staff.displayOrders();
                else if (choice.equals("2"))
                    staff.processOrder();
                else if (choice.equals("0")) {
                    System.out.println("Signing out account..");
                    return;
                }
                else{
                    System.out.println("Invalid. Please enter again:");
                    continue;
                }
            }
            catch (Exception e) {
                System.out.println("Something went wrong.");
            }
        }

    }

    private static void allAdminActions(AdminWorker admin){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome "+admin.getName());
        System.out.println();

        while (true) {
            try {
                System.out.println("------------------ Staff Account Settings------------------");
                System.out.println("1. Add staff account");
                System.out.println("2. Remove staff account");
                System.out.println("3. Display staff list");
                System.out.println("4. Promote a staff");
                System.out.println("5. Transfer a staff");
                System.out.println();
                System.out.println("------------------ Branch Settings------------------");
                System.out.println("6. Add a branch");
                System.out.println("7. Remove a branch");
                System.out.println("8. Open a branch");
                System.out.println("9. Close a branch");
                System.out.println("10. Add payment method in a branch");
                System.out.println("11. Remove payment method in a branch");
                System.out.println();
                System.out.println("0. Exit");
                System.out.println("What would you like to do?");

                String choice = sc.nextLine();
                if (choice.equals("1"))
                    admin.getJobsOnWorkerList().addWorker();
                else if (choice.equals("2"))
                    admin.getJobsOnWorkerList().removeWorker();
                else if (choice.equals("3"))
                    admin.getJobsOnWorkerList().displayWorkerList();
                else if (choice.equals("4"))
                    admin.getJobsOnWorkerList().promoteToManager();
                else if (choice.equals("5"))
                    admin.getJobsOnBranchList().transferStaff();
                else if (choice.equals("6"))
                    admin.getJobsOnBranchList().addBranch();
                else if (choice.equals("7"))
                    admin.getJobsOnBranchList().removeBranch();
                else if (choice.equals("8"))
                    admin.getJobsOnBranch().openBranch();
                else if (choice.equals("9"))
                    admin.getJobsOnBranch().closeBranch();
                else if (choice.equals("10"))
                    admin.getJobsOnPaymentList().addPayment();
                else if (choice.equals("11"))
                    admin.getJobsOnPaymentList().removeFromPaymentList();
                else if (choice.equals("0")) {
                    System.out.println("Signing out account..");
                    return;
                }
                else {
                    System.out.println("Invalid. Please enter again:");
                    continue;
                }
            }
            catch (Exception e) {
                System.out.println("Something went wrong.");
            }
        }
    }


    private static void createTestCases(AdminWorker admin) {
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

        System.out.println("All Workers has been added");
    }

    private static void serialisationBeforeQuitting(AdminWorker admin) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("AdminInfo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(admin);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("we are screwed");
        }

        BranchList.serializeBranchList();
        AllWorkersList.serializeALlWorkerList();
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
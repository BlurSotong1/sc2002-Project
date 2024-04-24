package foms.food;
import java.util.*;
public class CustomerApp {
    static Scanner sc = new Scanner(System.in);
    static List<FoodItem> orderedItems = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Choose a branch\n" +
                "(1) Jurong Point\n" +
                "(2) Jurong East\n" +
                "(3) Choa Chu Kang\n" +
                "(4) Woodlands\n");

        System.out.println("Enter the number of your choice:");
        int branchOption = sc.nextInt();
        System.out.println("Branch " + getBranchName(branchOption) + " selected");
        displayMenu();
    }

    private static String getBranchName(int option) {
        switch (option) {
            case 1: return "Jurong Point";
            case 2: return "Jurong East";
            case 3: return "Choa Chu Kang";
            case 4: return "Woodlands";
            default: return "Unknown";
        }
    }

    private static void displayMenu() {
        System.out.println("Menu:\n" +
                "1. SetMeal\n" +
                "2. Drinks\n" +
                "3. SideDish\n" +
                "4. MainDish\n");

        System.out.println("Choose an option:");
        int menuOption = sc.nextInt();
        switch (menuOption) {
            case 1:
                handleSetMeal();
                break;
            case 2:
                handleDrinks();
                break;
            case 3:
                handleSideDish();
                break;
            case 4:
                handleMainDish();
                break;
            default:
                System.out.println("Invalid menu option.");
                return;
        }

        finalizeOrder();
    }

    private static void handleSetMeal() {
        SetMeal setMeal = SetMeal.selectSetMeal();
        if (setMeal != null) {
            setMeal.displayFoodInformation();
            orderedItems.addAll(setMeal.getSetMealItems());
        } else {
            System.out.println("Failed to select a valid set meal. Please try again.");
        }
    }

    private static void handleDrinks() {
        Drink drink = Drink.selectDrink();
        if (drink != null) {
            drink.displayFoodInformation();
            orderedItems.add(drink);
        } else {
            System.out.println("No drink was selected.");
        }
    }

    private static void handleSideDish() {
        SideDish sideDish = SideDish.selectSideDish();
        if (sideDish != null) {
            sideDish.displayFoodInformation();
            orderedItems.add(sideDish);
        } else {
            System.out.println("No side dish was selected.");
        }
    }

    private static void handleMainDish() {
        MainDish mainDish = MainDish.selectMainDish();
        if (mainDish != null) {
            mainDish.displayFoodInformation();
            orderedItems.add(mainDish);
        } else {
            System.out.println("No main dish was selected.");
        }
    }

    private static void finalizeOrder() {
        System.out.println("Do you want to:\n" +
                "1. Continue ordering\n" +
                "2. End order and proceed to customization");
        int choice = sc.nextInt();
        if (choice == 1) {
            displayMenu();
        } else {
            showOrderAndCustomize();
        }
    }

    private static void showOrderAndCustomize() {
        System.out.println("Your ordered items:");
        for (int i = 0; i < orderedItems.size(); i++) {
            System.out.println((i + 1) + ". " + orderedItems.get(i));
        }

        while (true) {
            System.out.println("Do you want to customize any item? (yes/no)");
            String customizeOption = sc.next();
            if ("no".equalsIgnoreCase(customizeOption)) {
                break; // Exit the loop if the user doesn't want to customize any more items
            } else if (!"yes".equalsIgnoreCase(customizeOption)) {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                continue; // Continue the loop if the input is neither 'yes' nor 'no'
            }

            System.out.println("Select the item number to customize:");
            int itemNumber = sc.nextInt();
            FoodItem itemToCustomize = orderedItems.get(itemNumber - 1);
            itemToCustomize.customise();
            System.out.println("Updated item: " + itemToCustomize);
        }

        printReceipt();
    }


    private static void printReceipt() {
        System.out.println("Here is your receipt:");
        for (FoodItem item : orderedItems) {
            System.out.println(item);
        }
    }
}

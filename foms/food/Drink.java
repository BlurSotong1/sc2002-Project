package foms.food;

import java.awt.*;
import java.io.Serializable;
import java.util.Scanner;

public class Drink extends FoodItem implements Serializable {
    /**
     * ice levels of a drink that is customisable.
     */
    private iceLevels iceLevel = iceLevels.DEFAULT_ICE;

    /**
     * constructors to create food item by manager
     * @param name name of the drink
     * @param price of the drink
     * @param description what is the drink
     */
    public Drink(String name, double price, String description) {
        super(name, price, description);
    }

    /**
     * used to copy a new object over for customers into the cart
     * @param drink is the original menu item.
     */
    public Drink(FoodItem drink) {
        super(drink.getName(),drink.getPrice(),drink.getDescription());
    }

    /**
     * overriden method. will change ice levels for drinks too,
     */
    @Override
    public void customiseFoodItem() {
        System.out.printf("Customising food item %s...\n", getName());

        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Enter your choice: \n1. Change ice level" +
                    "\n2. Input Custom Request. (press 0 to quit): ");
            input = scanner.nextLine(); scanner.nextLine(); //read in the \n
            switch (input) {
                case "0"-> {
                    System.out.println("Returning to main page..");
                    return;
                }
                case "1" -> {
                    updateIceLevel();
                    return;
                }
                case "2" -> {
                    super.customiseFoodItem();
                    return;
                }
            }
        }


    }

    public void updateIceLevel() {
        System.out.println("Enter your choice for ice level.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. No Ice");
            System.out.println("2. Less Ice");
            System.out.println("3. Default Ice");
            System.out.print("4. More Ice. Press 0 to exit.");
            String input = scanner.next();
            switch (input) {
                case "0" -> System.out.println("Did not edit. Returning to main page..");
                case "1" -> {
                    System.out.println("Changed ice level to no ice.");
                    iceLevel = iceLevels.NO_ICE;
                    return;
                }

                case "2" -> {
                    System.out.println("Changed ice level to less ice.");
                    iceLevel = iceLevels.LESS_ICE;
                    return;
                }

                case "3" -> {
                    System.out.println("Changed ice level to default ice.");
                    iceLevel = iceLevels.DEFAULT_ICE;
                    return;
                }

                case "4" -> {
                    System.out.println("Changed ice level to more ice.");
                    iceLevel = iceLevels.MORE_ICE;
                    return;
                }

                default -> System.out.println("Enter a valid input!");
            }


        }
    }



}

package foms.food;


import java.util.*;
import java.util.ArrayList;


public class SetMeal extends FoodItem{
    private ArrayList<FoodItem> setMeal;
    private static Scanner sc = new Scanner(System.in);

    public SetMeal(FoodItem mainDish, FoodItem sideDish, FoodItem drink) {
        this.setMeal = new ArrayList<>(List.of(mainDish, sideDish, drink));
    }

    // This method will present the set meal options and return the selected SetMeal
    public static SetMeal selectSetMeal() {
        System.out.println("Available Set Meals:");
        System.out.println("1. Chicken Set (Chicken Rice, Coleslaw, Cola)");
        System.out.println("2. Beef Set (Beef Steak, Fries, Beer)");
        System.out.println("3. Vegetarian Set (Veggie Burger, Salad, Juice)");
        System.out.println("Enter the number of the Set Meal you want:");

        int option = sc.nextInt();
        switch (option) {
            case 1:
                return new SetMeal(new MainDish("Chicken Rice", 5.00, true, "Main Dish", "Delicious chicken with fragrant rice"),
                        new SideDish("Coleslaw", 2.00, true, "Side Dish", "Crunchy fresh coleslaw"),
                        new Drink("Cola", 1.50, true, "Cold Drinks", "A refreshing cola drink"));
            case 2:
                return new SetMeal(new MainDish("Beef Steak", 12.00, true, "Main Dish", "Tender beef steak"),
                        new SideDish("Fries", 3.00, true, "Side Dish", "Golden crispy fries"),
                        new Drink("Beer", 4.00, true, "Alcoholic Beverages", "Chilled premium beer"));
            case 3:
                return new SetMeal(new MainDish("Veggie Burger", 7.00, true, "Main Dish", "Juicy grilled veggie burger"),
                        new SideDish("Salad", 3.50, true, "Side Dish", "Fresh green salad"),
                        new Drink("Juice", 2.50, true, "Cold Drinks", "Freshly squeezed orange juice"));
            default:
                System.out.println("Invalid choice. Please select a valid Set Meal.");
                return null;
        }
    }

    @Override
    public void displayFoodInformation() {
        System.out.println("Your Set Meal includes:");
        setMeal.forEach(item -> item.displayFoodInformation());
    }

    @Override
    public void customise() {
        System.out.println("Choose an item to customise within the Set Meal:");
        for (int i = 0; i < setMeal.size(); i++) {
            System.out.println((i + 1) + ". " + setMeal.get(i).getName());
        }
        int itemChoice = sc.nextInt();
        if (itemChoice > 0 && itemChoice <= setMeal.size()) {
            setMeal.get(itemChoice - 1).customise();
        } else {
            System.out.println("Invalid selection.");
        }
    }

    public ArrayList<FoodItem> getSetMealItems() {
        return setMeal;
    }
}
package foms.food;
import java.util.Scanner;

public class SideDish extends FoodItem {
    private Scanner sc = new Scanner(System.in);
    private int saltLevel;
    private String baseDescription;

    public SideDish(String name, double price, String description) {
        super(name, price, description);

    }



    @Override
    public void displayFoodInformation() {
        System.out.println(toString());
    }




}

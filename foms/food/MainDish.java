package foms.food;

import java.io.Serializable;
import java.util.Scanner;


public class MainDish extends FoodItem implements Serializable {
    private String baseDescription;

    public MainDish(String name, double price, String description) {
        super(name, price, description);
    }

    public MainDish(FoodItem menuItem) {
        super(menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }


}

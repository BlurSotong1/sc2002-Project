package foms.food;


import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;


public class SetMeal extends FoodItem implements Serializable {
    private FoodItem mainDish;
    private FoodItem sideDish;
    private FoodItem drink;

    /**
     * constructor for set meal. creates a setmeal with maindish, lets the customer choose later.
     * @param mainDish is the maindish of the food. set meal is auto set name to __ set meal
     * @param price  is the price of the setmeal
     * @param description is the description of the set meal
     */
    public SetMeal(FoodItem mainDish, double price, String description) {
        super(String.format(mainDish.getName() + " set meal"), price, description);
        this.mainDish = mainDish;
    }


    }

}
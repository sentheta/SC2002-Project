package projectjava;

import java.util.ArrayList;
import java.util.List;

class Menu {
    private List<Food> foodItems;

  public Menu() { this.foodItems = new ArrayList<>(); }
  public void addFood(Food food) { foodItems.add(food); }
  public void deleteFood(Food food) { foodItems.remove(food); }
  public void updateFood(Food oldFood, Food newFood) {  deleteFood(oldFood);   addFood(newFood);      }

  public void display() {
        System.out.println("Menu Items:");
        for (Food food : foodItems) {
            System.out.println("Name: " + food.getName());
            System.out.println("Price: $" + food.getPrice());
            System.out.println("Availability: " + (food.getAvailable() ? "Available" : "Not Available"));
            System.out.println("Category: " + food.getCategory());
            System.out.println("---------------------");
        }
    }
}
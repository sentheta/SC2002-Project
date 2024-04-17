import java.util.*;

class Menu {
    private ArrayList<Food> foods;

    //================================================================//


    public Menu(){
        this.foods = new ArrayList<Food>();
    }

    public void addFood(){
        Food food = new Food();
        // ?? ask user to input food
        foods.add(food);
    }
    
    public void deleteFood(){
        Food food = new Food();
        // ?? ask user to input food
        foods.remove(food);
    }
    public void updatePrice(){
        Food food = new Food();
        // ?? ask user to input food then update
    }
    public void updateAvail(){
        Food food = new Food();
        // ?? ask user to input food then update
    }


    public void display() {
        System.out.println("Menu Items:");
        for (Food food : foods) {
            System.out.println("Name: " + food.getName());
            System.out.println("Price: $" + food.getPrice());
            System.out.println("Availability: " + (food.getAvail() ? "Available" : "Not Available"));
            System.out.println("Category: " + food.getCategory());
            System.out.println("---------------------");
        }
    }

    //================================================================//
    
    ArrayList<Food> getFoods() {return foods;}
}
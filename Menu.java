import java.util.*;

class Menu {
    private ArrayList<Food> foods;

    //================================================================//
    //================================================================//

    public Menu(){
        foods = new ArrayList<Food>();
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


    public boolean chooseAction(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose menu action:");
        System.out.println("1. Add food");
        System.out.println("2. Delete food");
        System.out.println("3. Update price");
        System.out.println("4. Update availability");
        System.out.println("Other value to end menu session");

        try{
            int choice = sc.nextInt();
            switch(choice){
            case 1: addFood(); return true;
            case 2: deleteFood(); return true;
            case 3: updatePrice(); return true;
            case 4: updateAvail(); return true;
            }
        }
        catch(Exception e){}

        System.out.println("Other value to end menu session");
        return false;
    }

    //================================================================//
    //================================================================//


    ArrayList<Food> getFoods() {return foods;}
}
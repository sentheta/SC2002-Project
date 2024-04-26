package FOODIE;
import java.util.*;
import java.io.Serializable;
/**
 * Menu class represents the menu of a branch, with functionality such as adding/removing food to/from a menu,
 * updating price/availability of a food item, and displaying a menu. 
 */
public class Menu implements IActionable, Serializable{
    
    private ArrayList<Food> foods;

    //================================================================//
    //================================================================//
    /**
     * Default constructor that constructs new Menu object, with no food items 
     */
    public Menu(){
        foods = new ArrayList<Food>();
        Logger.log("Creating new menu");
    }
    /**
     * Adds a new Food object to the menu. 
     * Method first asks for Food name before checking if it is a duplicate. 
     * If it is not a duplicate, user will be prompted to enter the price and category of Food object, before creating
     * the new Food object and adding it to the Menu object. 
     */
    public void addFood(){
        Scanner sc = new Scanner(System.in);
        
        String name, category;
        Double price;
        try{
            // Getting food name
            System.out.println("Enter food name:");
            System.out.print(">>> ");
            name = sc.nextLine();
            //Testing to see if it is a duplicate food item on the menu
            for(Food food : foods) if(food.getName().equals(name)){
                throw new Exception("Duplicate food entry");
            }
            //Getting information about food
            System.out.println("Enter food price:");
            System.out.print(">>> ");
            price = Double.parseDouble(sc.nextLine());

            System.out.println("Enter food category:");
            System.out.print(">>> ");
            category = sc.nextLine();

            // public Food(String name,double price, String category)
            Food food = new Food(name, price, category);
            // adding food to ArrayList of Food foods
            foods.add(food);
            System.out.println("Food added");
        }
        catch(Exception e){System.out.print(e);}
        
    }
    /**
     * Deletes a food item from the menu. 
     * Method first asks for name of food item, then removes the food item if the food is found.
     */
    public void deleteFood(){
        Scanner sc = new Scanner(System.in);

        try{
            //Getting food name.
            System.out.println("Enter food name:");
            System.out.print(">>> ");
            String name = sc.nextLine();
            //Removing food. 
            for(Food food : foods) if(food.getName().equals(name)){
                foods.remove(food);
                System.out.println("Food removed");
                return;
            }
        }
        catch(Exception e){e.printStackTrace();}

        System.out.println("Food not found");
    }
    /**
     * Updates the price of a food item.
     * Method first asks for food name. If food item exists, asks for new price and updates the new price of the food item.
     */
    public void updatePrice(){
        Scanner sc = new Scanner(System.in);

        try{
            // Getting food name
            System.out.println("Enter food name:");
            System.out.print(">>> ");
            String name = sc.nextLine();
            // Finding food item in menu.
            for(Food food : foods) if(food.getName().equals(name)){
                System.out.println("Enter new price:");
                System.out.print(">>> ");
                double newPrice = Double.parseDouble(sc.nextLine());
                // updating price of new food. 
                food.setPrice(newPrice);
                System.out.println("Food price updated");
                return;
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        System.out.println("Food not found");
    }
    /**
     * Updates the availability of the food item in the menu. 
     * Method first asks for food name, before updating the availability of the food item.
     */
    public void updateAvail(){
        Scanner sc = new Scanner(System.in);

        try{
            // Getting food name.
            System.out.println("Enter food name:");
            System.out.print(">>> ");
            String name = sc.nextLine();
            // Finding food item.
            for(Food food : foods) if(food.getName().equals(name)){
                // Updating food availability.
                food.setAvail(!food.getAvail());
                System.out.println("Food availability updated");
                return;
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        System.out.println("Food not found");
    }
    /**
     * Displays food items on menu, with name, price, category.
     */
    public void display() {
        //displaying of food items on menu
        System.out.println("Menu Items:");
        int i = 1;
        for(Food food : foods) if(food.getAvail()){
            System.out.println("Item " + i);
            System.out.println("Name: " + food.getName());
            System.out.println("Price: $" + food.getPrice());
            System.out.println("Category: " + food.getCategory());
            System.out.println("---------------------");
            i++;
        }
    }

    /**
     * Provides user with option to choose from a range of functionality that is related to Menu, such as adding/deleting 
     * food. 
     * @return true if action is executed, else false
     */
    public boolean chooseAction(){
        Scanner sc = new Scanner(System.in);
        // Prints out options available
        System.out.println();
        System.out.println("--Choose menu action--");
        System.out.println("1. Add food");
        System.out.println("2. Delete food");
        System.out.println("3. Update price");
        System.out.println("4. Update availability");
        System.out.println("5. Display menu items");
        System.out.println("Other value to end menu session");

        try{
            System.out.print(">>> ");
            // Key in choice of action
            int choice = Integer.parseInt(sc.nextLine());
            switch(choice){
            case 1: addFood(); return true;
            case 2: deleteFood(); return true;
            case 3: updatePrice(); return true;
            case 4: updateAvail(); return true;
            case 5: display(); return true;
            }
        }
        catch(Exception e){}

        System.out.println("Terminating menu session");
        return false;
    }

    //================================================================//
    //================================================================//

    /**
     * Gets food items on menu
     * @return ArrayList of food item.
     */
    ArrayList<Food> getFoods() {return foods;}
}

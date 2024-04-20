import java.util.*;

class Menu {
    private ArrayList<Food> foods;

    //================================================================//
    //================================================================//

    public Menu(){
        foods = new ArrayList<Food>();
        Logger.log("Creating new menu");
    }

    public void addFood(){
        Scanner sc = new Scanner(System.in);
        
        String name, category;
        Double price;
        try{
            System.out.println("Enter food name:");
            System.out.print(">>> ");
            name = sc.nextLine();

            System.out.println("Enter food price:");
            System.out.print(">>> ");
            price = Double.parseDouble(sc.nextLine());

            System.out.println("Enter food category:");
            System.out.print(">>> ");
            category = sc.nextLine();

            // public Food(String name,boolean avail,double price, String category)
            Food food = new Food(name, true, price, category);
            foods.add(food);
            System.out.println("Food added");
        }
        catch(Exception e){e.printStackTrace();}
        
    }
        
    public void deleteFood(){
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Enter food name:");
            System.out.print(">>> ");
            String name = sc.nextLine();

            for(Food food : foods) if(food.getName().equals(name)){
                foods.remove(food);
                System.out.println("Food removed");
                return;
            }
        }
        catch(Exception e){e.printStackTrace();}

        System.out.println("Food not found");
    }

    public void updatePrice(){
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Enter food name:");
            System.out.print(">>> ");
            String name = sc.nextLine();

            for(Food food : foods) if(food.getName().equals(name)){
                double newPrice = Double.parseDouble(sc.nextLine());
                food.setPrice(newPrice);
                System.out.println("Food price updated");
                return;
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        System.out.println("Food not found");
    }

    public void updateAvail(){
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Enter food name:");
            System.out.print(">>> ");
            String name = sc.nextLine();

            for(Food food : foods) if(food.getName().equals(name)){
                food.setAvail(!food.getAvail());
                System.out.println("Food availability updated");
                return;
            }
        }
        catch(Exception e){e.printStackTrace();}
        
        System.out.println("Food not found");
    }

    public void display() {
        System.out.println("Menu Items:");
        for(Food food : foods) if(food.getAvail()){
            System.out.println("Name: " + food.getName());
            System.out.println("Price: $" + food.getPrice());
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
            System.out.print(">>> ");
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
package FOODIE;
import java.util.ArrayList;
import java.util.Scanner;

public class branches {
    private ArrayList<Branch> branches;

    public branches() {
        branches = new ArrayList<>();
        initialiseBranch();
    }

    private void initialiseBranch() {
        ArrayList<Staff> staffs1 = new ArrayList<>();
        ArrayList<Manager> managers1 = new ArrayList<>();
        ArrayList<Order> orders1 = new ArrayList<>();
        branches.add(new Branch(new Menu(), "Northspine Plaza", staffs1, managers1, orders1));

        ArrayList<Staff> staffs2 = new ArrayList<>();
        ArrayList<Manager> managers2 = new ArrayList<>();
        ArrayList<Order> orders2 = new ArrayList<>();
        branches.add(new Branch(new Menu(), "Jurong Point", staffs2, managers2, orders2));

        ArrayList<Staff> staffs3 = new ArrayList<>();
        ArrayList<Manager> managers3 = new ArrayList<>();
        ArrayList<Order> orders3 = new ArrayList<>();
        branches.add(new Branch(new Menu(), "Jurong East", staffs3, managers3, orders3));
    }

    public void addBranch(Branch branch) {
        branches.add(branch); 
    }

    public void closeBranch(Branch branch) {
        branches.remove(branch);
    }

    public Branch chooseBranch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Branches:");
        for (int i = 0; i < branches.size(); i++) {
            System.out.println((i + 1) + ". " + branches.get(i).getName());
        }
        System.out.print("Choose a branch number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice >= 1 && choice <= branches.size()) {
            return branches.get(choice - 1);
        } else {
            System.out.println("Please choose a number from 1 to " + branches.size());
            return chooseBranch(); 
        }
    }
}

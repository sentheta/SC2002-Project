public class PayMethod {
    private String name;
   
    public PayMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static void pay(double amount) {
        System.out.println("Payment of $" + amount + " made using " + "name");
    }

    public static PayMethod choosePaymentMethod() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a payment method:");
        System.out.println("1. Paywave");
        System.out.println("2. Credit Card");
        System.out.println("3. NETS");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        PayMethod paymentMethod = null;

        switch (choice) {
            case 1:
                paymentMethod = new PayMethod("Paywave");
                break;
            case 2:
                paymentMethod = new PayMethod("Credit Card");
                break;
            case 3:
                paymentMethod = new PayMethod("NETS");
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
        return paymentMethod;
    }

}

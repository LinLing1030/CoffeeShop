import java.time.LocalDate;
import java.util.Scanner;

public class CoffeeShop {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        double totalAmount = 0; 

        
        String currentDate = LocalDate.now().toString();
        System.out.println("Input " + currentDate + " stock");

        
        System.out.println("Americano:");
        scanner.nextInt();
        System.out.println("Mocha:");
        scanner.nextInt();
        System.out.println("Ice Tea:");
        scanner.nextInt();

        System.out.println("Welcome shop");

        
        boolean shopping = true;
        while (shopping) {
            
            System.out.println("Which coffee do you want?");
            System.out.println("1. Americano - $3.5");
            System.out.println("2. Mocha - $4.0");
            System.out.println("3. Ice Tea - $2.5");

            
            String choice = scanner.next();
            String selectedCoffee = "";
            double price = 0.0;

            
            if (choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("Americano")) {
                selectedCoffee = "Americano";
                price = 3.5;
            } else if (choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("Mocha")) {
                selectedCoffee = "Mocha";
                price = 4.0;
            } else if (choice.equalsIgnoreCase("3") || choice.equalsIgnoreCase("Ice Tea")) {
                selectedCoffee = "Ice Tea";
                price = 2.5;
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

           
            System.out.println("How many cups?");
            int quantity = scanner.nextInt();

            
            double subtotal = price * quantity;
            totalAmount += subtotal;

            System.out.println("Added: " + quantity + " cups of " + selectedCoffee + " - $" + subtotal);

            
            while (true) {
                System.out.println("Do you want to finish shopping? (y/n)");
                String finish = scanner.next();
                if (finish.equalsIgnoreCase("y")) {
                    shopping = false;
                    break;
                } else if (finish.equalsIgnoreCase("n")) {
                    break; 
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }
        }

        
        System.out.println("Total amount: $" + totalAmount);
        System.out.println("Thank you for shopping!");
        scanner.close();
    }
}
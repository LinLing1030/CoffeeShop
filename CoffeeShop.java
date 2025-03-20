import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalAmount = 0;
        int totalCups = 0;

        String currentDate = LocalDate.now().toString();
        System.out.println("Input " + currentDate + " stock");

        Map<String, Integer> stock = new HashMap<>();
        stock.put("Americano", getStockInput(scanner, "Americano"));
        stock.put("Mocha", getStockInput(scanner, "Mocha"));
        stock.put("Ice Tea", getStockInput(scanner, "Ice Tea"));

        Map<String, Integer> purchased = new HashMap<>();
        Map<String, Double> priceMap = new HashMap<>();
        priceMap.put("Americano", 3.5);
        priceMap.put("Mocha", 4.0);
        priceMap.put("Ice Tea", 2.5);

        System.out.println("Welcome to the shop!");

        boolean shopping = true;
        while (shopping) {
            System.out.println("Which coffee do you want?");
            System.out.println("1. Americano - €3.5");
            System.out.println("2. Mocha - €4.0");
            System.out.println("3. Ice Tea - €2.5");

            String choice = scanner.next();
            String selectedCoffee = null;

            switch (choice.toLowerCase()) {
                case "1":
                case "americano":
                    selectedCoffee = "Americano";
                    break;
                case "2":
                case "mocha":
                    selectedCoffee = "Mocha";
                    break;
                case "3":
                case "ice tea":
                    selectedCoffee = "Ice Tea";
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            System.out.println("How many cups?");
            int quantity = getValidIntInput(scanner);

            if (stock.get(selectedCoffee) < quantity) {
                System.out.println("Not enough stock! Available: " + stock.get(selectedCoffee));
                continue;
            }

            double subtotal = calculateTotalAmount(priceMap.get(selectedCoffee), quantity);
            totalAmount += subtotal;
            totalCups += quantity;
            stock.put(selectedCoffee, stock.get(selectedCoffee) - quantity);

            purchased.put(selectedCoffee, purchased.getOrDefault(selectedCoffee, 0) + quantity);

            System.out.println("Added: " + quantity + " cups of " + selectedCoffee + " - €" + subtotal);

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

        System.out.println("\nReceipt:");
        for (Map.Entry<String, Integer> entry : purchased.entrySet()) {
            String coffee = entry.getKey();
            int quantity = entry.getValue();
            double coffeeTotal = priceMap.get(coffee) * quantity;
            System.out.println(coffee + " * " + quantity + " cups -- €" + coffeeTotal);
        }
        System.out.println("Total Cups: " + totalCups);
        System.out.println("Total Amount: €" + totalAmount);
        System.out.println("Thank you for shopping!");
        scanner.close();
    }

    public static double calculateTotalAmount(double price, int quantity) {
        return price * quantity;
    }

    private static int getStockInput(Scanner scanner, String coffeeType) {
        int stock;
        while (true) {
            System.out.println(coffeeType + ":");
            try {
                stock = scanner.nextInt();
                if (stock >= 0) {
                    break;
                } else {
                    System.out.println("Stock cannot be negative. Please enter again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }
        return stock;
    }

    private static int getValidIntInput(Scanner scanner) {
        int value;
        while (true) {
            try {
                value = scanner.nextInt();
                if (value > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid positive number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }
        return value;
    }
}

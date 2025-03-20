package com.coffeeshop;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class CoffeeShop {
    private static final Logger LOGGER = Logger.getLogger(CoffeeShop.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalAmount = 0;

        
        String currentDate = LocalDate.now().toString();
        LOGGER.info("Input " + currentDate + " stock");

        
        Map<String, Integer> stock = new HashMap<>();
        stock.put("Americano", getStockInput(scanner, "Americano"));
        stock.put("Mocha", getStockInput(scanner, "Mocha"));
        stock.put("Ice Tea", getStockInput(scanner, "Ice Tea"));

        LOGGER.info("Welcome to the shop!");

        boolean shopping = true;
        while (shopping) {
            
            LOGGER.info("Which coffee do you want?");
            LOGGER.info("1. Americano - $3.5");
            LOGGER.info("2. Mocha - $4.0");
            LOGGER.info("3. Ice Tea - $2.5");

            String choice = scanner.next();
            String selectedCoffee = null;
            double price = 0.0;

            switch (choice.toLowerCase()) {
                case "1":
                case "americano":
                    selectedCoffee = "Americano";
                    price = 3.5;
                    break;
                case "2":
                case "mocha":
                    selectedCoffee = "Mocha";
                    price = 4.0;
                    break;
                case "3":
                case "ice tea":
                    selectedCoffee = "Ice Tea";
                    price = 2.5;
                    break;
                default:
                    LOGGER.warning("Invalid choice. Please try again.");
                    continue;
            }

            
            LOGGER.info("How many cups?");
            int quantity = getValidIntInput(scanner);

            
            if (stock.get(selectedCoffee) < quantity) {
                LOGGER.warning("Not enough stock! Available: " + stock.get(selectedCoffee));
                continue;
            }

            
            double subtotal = price * quantity;
            totalAmount += subtotal;
            stock.put(selectedCoffee, stock.get(selectedCoffee) - quantity);

            LOGGER.info("Added: " + quantity + " cups of " + selectedCoffee + " - $" + subtotal);

            
            while (true) {
                LOGGER.info("Do you want to finish shopping? (y/n)");
                String finish = scanner.next();
                if (finish.equalsIgnoreCase("y")) {
                    shopping = false;
                    break;
                } else if (finish.equalsIgnoreCase("n")) {
                    break;
                } else {
                    LOGGER.warning("Invalid input. Please enter 'y' or 'n'.");
                }
            }
        }

        
        LOGGER.info("Total amount: $" + totalAmount);
        LOGGER.info("Thank you for shopping!");
        scanner.close();
    }

    
    private static int getStockInput(Scanner scanner, String coffeeType) {
        int stock;
        while (true) {
            LOGGER.info(coffeeType + ":");
            try {
                stock = scanner.nextInt();
                if (stock >= 0) {
                    break;
                } else {
                    LOGGER.warning("Stock cannot be negative. Please enter again.");
                }
            } catch (Exception e) {
                LOGGER.warning("Invalid input! Please enter a valid number.");
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
                    LOGGER.warning("Please enter a valid positive number.");
                }
            } catch (Exception e) {
                LOGGER.warning("Invalid input! Please enter a valid number.");
                scanner.next(); 
            }
        }
        return value;
    }
}

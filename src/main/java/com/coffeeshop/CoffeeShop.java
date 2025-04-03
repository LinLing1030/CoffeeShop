package com.coffeeshop;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoffeeShop {
    private static final Logger LOGGER = Logger.getLogger(CoffeeShop.class.getName());

    private static final String AMERICANO = "Americano";
    private static final String MOCHA = "Mocha";
    private static final String ICETEA = "Ice Tea";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalAmount = 0;

        // * Conditional logging: Invoke method(s) only conditionally
        String currentDate = LocalDate.now().toString();
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(String.format("Input %s stock", currentDate));
        }

        Map<String, Integer> stock = new HashMap<>();
        stock.put(AMERICANO, getStockInput(scanner, AMERICANO));
        stock.put(MOCHA, getStockInput(scanner, MOCHA));
        stock.put(ICETEA, getStockInput(scanner, ICETEA));

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

            // * ues if else
            if ("1".equalsIgnoreCase(choice) || "americano".equalsIgnoreCase(choice)) {
                selectedCoffee = AMERICANO;
                price = 3.5;
            } else if ("2".equalsIgnoreCase(choice) || "mocha".equalsIgnoreCase(choice)) {
                selectedCoffee = MOCHA;
                price = 4.0;
            } else if ("3".equalsIgnoreCase(choice) || "ice tea".equalsIgnoreCase(choice)) {
                selectedCoffee = ICETEA;
                price = 2.5;
                if (LOGGER.isLoggable(Level.INFO)) {
                    LOGGER.info("Ice Tea selected.");
                }
            } else {
                LOGGER.warning("Invalid choice. Please try again.");
                continue;
            }

            LOGGER.info("How many cups?");
            int quantity = getValidIntInput(scanner);

            if (stock.get(selectedCoffee) < quantity) {
                // Conditional logging: Invoke method(s) only conditionally
                if (LOGGER.isLoggable(Level.WARNING)) {
                    LOGGER.warning(String.format("Not enough stock! Available: %d", stock.get(selectedCoffee)));
                }
                continue;
            }

            double subtotal = calculateTotalAmount(price, quantity);
            totalAmount += subtotal;
            stock.put(selectedCoffee, stock.get(selectedCoffee) - quantity);

            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info(String.format("Added: %d cups of %s - $%.2f", quantity, selectedCoffee, subtotal));
            }

            LOGGER.info("Do you want to finish shopping? (y/n)");
            String finish = scanner.next();
            while (!finish.equalsIgnoreCase("y") && !finish.equalsIgnoreCase("n")) {
                LOGGER.warning("Invalid input. Please enter 'y' or 'n'.");
                LOGGER.info("Do you want to finish shopping? (y/n)");
                finish = scanner.next();
            }

            if (finish.equalsIgnoreCase("y")) {
                shopping = false;
            }
        }

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(String.format("Total amount: $%.2f", totalAmount));
        }
        LOGGER.info("Thank you for shopping!");
        scanner.close();
    }

    public static double calculateTotalAmount(double price, int quantity) {
        return price * quantity;
    }

    private static int getStockInput(Scanner scanner, String coffeeType) {
        while (true) {
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info(String.format("%s:", coffeeType));  //  Invoke String.format conditionally
            }
            try {
                int stock = scanner.nextInt();
                if (stock >= 0) return stock;
                LOGGER.warning("Stock cannot be negative. Please enter again.");
            } catch (Exception e) {
                LOGGER.warning("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }
    }

    private static int getValidIntInput(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                if (value > 0) return value;
                LOGGER.warning("Please enter a valid positive number.");
            } catch (Exception e) {
                LOGGER.warning("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }
    }
}

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
        logStockDate();
        Map<String, Integer> stock = initStock(scanner);
        runShop(scanner, stock);
        scanner.close();
    }

    private static void logStockDate() {
        String currentDate = LocalDate.now().toString();
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(String.format("Input %s stock", currentDate));
        }
    }

    private static Map<String, Integer> initStock(Scanner scanner) {
        Map<String, Integer> stock = new HashMap<>();
        stock.put(AMERICANO, getStockInput(scanner, AMERICANO));
        stock.put(MOCHA, getStockInput(scanner, MOCHA));
        stock.put(ICETEA, getStockInput(scanner, ICETEA));
        return stock;
    }

    private static void runShop(Scanner scanner, Map<String, Integer> stock) {
        LOGGER.info("Welcome to the shop!");
        double totalAmount = 0;
        boolean shopping = true;

        while (shopping) {
            String selectedCoffee = promptForCoffee(scanner);
            if (selectedCoffee == null) continue;

            double price = getPrice(selectedCoffee);
            int quantity = getValidIntInput(scanner);

            if (stock.get(selectedCoffee) < quantity) {
                LOGGER.warning(String.format("Not enough stock! Available: %d", stock.get(selectedCoffee)));
                continue;
            }

            double subtotal = calculateTotalAmount(price, quantity);
            totalAmount += subtotal;
            stock.put(selectedCoffee, stock.get(selectedCoffee) - quantity);

            LOGGER.info(String.format("Added: %d cups of %s - $%.2f", quantity, selectedCoffee, subtotal));

            shopping = !askToFinish(scanner);
        }

        LOGGER.info(String.format("Total amount: $%.2f", totalAmount));
        LOGGER.info("Thank you for shopping!");
    }

    private static String promptForCoffee(Scanner scanner) {
        LOGGER.info("Which coffee do you want?");
        LOGGER.info("1. Americano - $3.5");
        LOGGER.info("2. Mocha - $4.0");
        LOGGER.info("3. Ice Tea - $2.5");

        String choice = scanner.next();
        switch (choice.toLowerCase()) {
            case "1":
            case "americano":
                return AMERICANO;
            case "2":
            case "mocha":
                return MOCHA;
            case "3":
            case "ice tea":
                LOGGER.info("Ice Tea selected.");
                return ICETEA;
            default:
                LOGGER.warning("Invalid choice. Please try again.");
                return null;
        }
    }

    private static boolean askToFinish(Scanner scanner) {
        LOGGER.info("Do you want to finish shopping? (y/n)");
        String finish = scanner.next();
        while (!finish.equalsIgnoreCase("y") && !finish.equalsIgnoreCase("n")) {
            LOGGER.warning("Invalid input. Please enter 'y' or 'n'.");
            LOGGER.info("Do you want to finish shopping? (y/n)");
            finish = scanner.next();
        }
        return finish.equalsIgnoreCase("y");
    }

    private static double getPrice(String coffeeType) {
        switch (coffeeType) {
            case AMERICANO:
                return 3.5;
            case MOCHA:
                return 4.0;
            case ICETEA:
                return 2.5;
            default:
                return 0;
        }
    }

    public static double calculateTotalAmount(double price, int quantity) {
        return price * quantity;
    }

    private static int getStockInput(Scanner scanner, String coffeeType) {
        while (true) {
            LOGGER.info(String.format("%s:", coffeeType));
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

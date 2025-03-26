package com.coffeeshop;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Unit tests for CoffeeShop application.
 */
public class CoffeeShopTest extends TestCase {

    public CoffeeShopTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(CoffeeShopTest.class);
    }

    /**
     * Test calculateTotalAmount method with different values.
     */
    public void testCalculateTotalAmount() {
        assertEquals(7.0, CoffeeShop.calculateTotalAmount(3.5, 2), 0.01);
        assertEquals(0.0, CoffeeShop.calculateTotalAmount(3.5, 0), 0.01);
        assertEquals(0.0, CoffeeShop.calculateTotalAmount(0.0, 2), 0.01);
        assertEquals(10000.0, CoffeeShop.calculateTotalAmount(100.0, 100), 0.01);
        assertEquals(-7.0, CoffeeShop.calculateTotalAmount(-3.5, 2), 0.01);
        assertEquals(-7.0, CoffeeShop.calculateTotalAmount(3.5, -2), 0.01);
    }

    /**
     * Simulate a basic shopping flow with one item and checkout.
     */
    public void testUserInputBasicFlow() {
        String input = "10\n10\n10\n1\n2\ny\n";
        simulateInputAndRun(input);
    }

    /**
     * Simulate quitting without purchasing.
     */
    public void testUserQuits() {
        String input = "10\n10\n10\n4\nn\n";
        simulateInputAndRun(input);
    }

    /**
     * Simulate multiple purchases in a single session.
     */
    public void testMultipleOrders() {
        String input = "10\n10\n10\n1\n1\ny\n2\n2\ny\n3\n1\nn\n";
        simulateInputAndRun(input);
    }

    /**
     * Test getStockInput method with negative number then valid input.
     */
    public void testGetStockInputWithNegativeNumberThenValid() {
        String input = "-5\n10\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        int stock = CoffeeShop.getStockInput(scanner, "Americano");
        assertEquals(10, stock);
    }

    /**
     * Test getStockInput with invalid string then valid input.
     */
    public void testGetStockInputWithInvalidStringThenValid() {
        String input = "abc\n15\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        int stock = CoffeeShop.getStockInput(scanner, "Mocha");
        assertEquals(15, stock);
    }

    /**
     * Test getValidIntInput with 0 then valid input.
     */
    public void testGetValidIntInputWithZeroThenValid() {
        String input = "0\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        int result = CoffeeShop.getValidIntInput(scanner);
        assertEquals(2, result);
    }

    /**
     * Test getValidIntInput with invalid string then valid input.
     */
    public void testGetValidIntInputWithInvalidStringThenValid() {
        String input = "xyz\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        int result = CoffeeShop.getValidIntInput(scanner);
        assertEquals(3, result);
    }

    /**
     * Utility method to simulate System.in and run main method.
     */
    private void simulateInputAndRun(String input) {
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            CoffeeShop.main(new String[]{});
        } finally {
            System.setIn(originalSystemIn);
        }
    }
}

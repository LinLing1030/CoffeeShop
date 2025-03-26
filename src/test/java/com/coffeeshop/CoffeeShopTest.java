package com.coffeeshop;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Unit test for CoffeeShop.
 */
public class CoffeeShopTest extends TestCase {

    /**
     * Create the test case
     * @param testName name of the test case
     */
    public CoffeeShopTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CoffeeShopTest.class);
    }

    /**
     * Test the calculateTotalAmount method
     */
    public void testCalculateTotalAmount() {
        assertEquals(7.0, CoffeeShop.calculateTotalAmount(3.5, 2), 0.01);
        assertEquals(10.0, CoffeeShop.calculateTotalAmount(5.0, 2), 0.01);
        assertEquals(0.0, CoffeeShop.calculateTotalAmount(3.5, 0), 0.01);
    }

    /**
     * Test user input simulation with valid purchase flow
     */
    public void testUserInputValidPurchase() {
        String simulatedInput = "10\n10\n10\n1\n2\ny\n";
        simulateInputAndRun(simulatedInput);
    }

    /**
     * Test user quitting without purchasing
     */
    public void testUserQuitsImmediately() {
        String simulatedInput = "10\n10\n10\n4\nn\n";
        simulateInputAndRun(simulatedInput);
    }

    /**
     * Simulate multiple purchases before quitting
     */
    public void testMultiplePurchasesThenQuit() {
        String simulatedInput = "10\n10\n10\n1\n1\ny\n2\n2\ny\n3\n1\nn\n";
        simulateInputAndRun(simulatedInput);
    }

    /**
     * Helper method to inject input and call main
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

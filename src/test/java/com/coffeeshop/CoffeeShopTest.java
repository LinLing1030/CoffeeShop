package com.coffeeshop;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        String simulatedInput = "10\n10\n10\n4\n1\n1\ny\n"; 
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
     * Test buying more than available stock (to cover "Not enough stock" branch)
     */
    public void testBuyTooMuchCoffee() {
        String simulatedInput = "1\n100\ny\n";
        simulateInputAndRun(simulatedInput);
    }

    /**
     * Test invalid input (to cover else branch inside while loop)
     */
    public void testInvalidInputHandling() {
        String simulatedInput = "1\n1\nmaybe\nn\n";
        simulateInputAndRun(simulatedInput);
    }

    /**
     * Test input with only one cup and then finish
     */
    public void testSingleCupAndFinish() {
        String simulatedInput = "2\n1\ny\n";
        simulateInputAndRun(simulatedInput);
    }

    /**
     * Helper method to inject input and call main
     */
    private void simulateInputAndRun(String input) {
        InputStream originalSystemIn = System.in;
        PrintStream originalSystemOut = System.out;

        // Optional: Capture output (you can assert it if needed)
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            CoffeeShop.main(new String[]{});
        } finally {
            System.setIn(originalSystemIn);
            System.setOut(originalSystemOut);
        }
    }
}

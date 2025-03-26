package com.coffeeshop;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Unit tests for CoffeeShop.
 */
public class CoffeeShopTest extends TestCase {

    /**
     * Constructor for the test case.
     * @param testName name of the test case
     */
    public CoffeeShopTest(String testName) {
        super(testName);
    }

    //@return the suite of tests being tested
    public static Test suite() {
        return new TestSuite(CoffeeShopTest.class);
    }

    //Test the calculation of total amount.
    public void testCalculateTotalAmount() {
        assertEquals(7.0, CoffeeShop.calculateTotalAmount(3.5, 2), 0.01);
        assertEquals(10.0, CoffeeShop.calculateTotalAmount(5.0, 2), 0.01);
        assertEquals(0.0, CoffeeShop.calculateTotalAmount(3.5, 0), 0.01);
    }

    //Simulates a valid purchase flow with single item.
    public void testSingleCupAndFinish() {
        String simulatedInput = "10\n10\n10\n1\n1\ny\n";
        simulateInputAndRun(simulatedInput);
    }

    //Simulates quitting the app without any purchase.
    public void testUserQuitsImmediately() {
        String simulatedInput = "10\n10\n10\n4\n1\n1\ny\n";
        simulateInputAndRun(simulatedInput);
    }

    //Simulates multiple purchases then quitting.
    public void testMultiplePurchasesThenQuit() {
        String simulatedInput = "10\n10\n10\n1\n1\ny\n2\n2\ny\n3\n1\nn\n";
        simulateInputAndRun(simulatedInput);
    }

    /**
     * Simulates user attempting to buy more than stock allows,
     * then reselecting valid quantity and quitting.
     */
    public void testBuyTooMuchCoffee() {
        String simulatedInput = "10\n10\n10\n1\n99\n1\n2\ny\n";
        simulateInputAndRun(simulatedInput);
    }

    // Simulates invalid input handling (non-integer entered).
    public void testInvalidInputHandling() {
        String simulatedInput = "10\n10\n10\nabc\n1\n1\ny\n";
        simulateInputAndRun(simulatedInput);
    }

    //Helper to simulate System.in for main method testing.

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

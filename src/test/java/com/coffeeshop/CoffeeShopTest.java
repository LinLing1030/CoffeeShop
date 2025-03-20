package com.coffeeshop;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
     * Test user input simulation
     */
    public void testUserInput() {
        String simulatedInput = "1\n2\n3\ny\n"; 
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            CoffeeShop.main(new String[]{});
        } finally {
            System.setIn(originalSystemIn); 
        }
    }
}

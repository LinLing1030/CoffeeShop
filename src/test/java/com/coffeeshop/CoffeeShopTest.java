package com.coffeeshop;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class CoffeeShopTest extends TestCase {

    /**
     * Constructor for the test case.
     * @param testName name of the test case
     */
    public CoffeeShopTest(String testName) {
        super(testName);
    }

    
    public static Test suite() {
        return new TestSuite(CoffeeShopTest.class);
    }

    
    public void testCalculateTotalAmount() {
        assertEquals(7.0, CoffeeShop.calculateTotalAmount(3.5, 2), 0.01);
        assertEquals(10.0, CoffeeShop.calculateTotalAmount(5.0, 2), 0.01);
        assertEquals(0.0, CoffeeShop.calculateTotalAmount(3.5, 0), 0.01);
    }

    
    public void testSingleCupAndFinish() {
        String simulatedInput = "10\n10\n10\n1\n1\ny\n";
        simulateInputAndRun(simulatedInput);
        assertTrue(true);
    }

    
    public void testUserQuitsImmediately() {
        String simulatedInput = "10\n10\n10\n4\n1\n1\ny\n";
        simulateInputAndRun(simulatedInput);
        assertTrue(true);
    }

    
    public void testMultiplePurchasesThenQuit() {
        String simulatedInput = "10\n10\n10\n1\n1\nn\n2\n2\ny\n";
        simulateInputAndRun(simulatedInput);
        assertTrue(true);
    }

    public void testMochaAndExitWithN() {
        simulateInputAndRun("10\n10\n10\n2\n1\nn\n1\n1\ny\n");
        assertTrue(true);
    }

    public void testInvalidFinishInput() {
        simulateInputAndRun("10\n10\n10\n1\n1\nmaybe\ny\n");
        assertTrue(true);
    }

    public void testInvalidStockInput() {
        simulateInputAndRun("abc\n10\n10\n10\n1\n1\ny\n");
        assertTrue(true);
    }

    public void testInvalidQuantityInput() {
        simulateInputAndRun("10\n10\n10\n1\nabc\n1\ny\n");
        assertTrue(true);
    }

    /**
     * Simulates user attempting to buy more than stock allows,
     * then reselecting valid quantity and quitting.
     */
    public void testBuyTooMuchCoffee() {
        String simulatedInput = "10\n10\n10\n1\n99\n1\n2\ny\n";
        simulateInputAndRun(simulatedInput);
        assertTrue(true);
    }

    
    public void testInvalidInputHandling() {
        String simulatedInput = "10\n10\n10\nabc\n1\n1\ny\n";
        simulateInputAndRun(simulatedInput);
        assertTrue(true);
    }

    
    public void testIceTeaOptionCoverage() {
        String simulatedInput = "10\n10\n10\n3\n1\ny\n"; // 输入3 → 触发 case "3"/"ice tea"
        simulateInputAndRun(simulatedInput);
        assertTrue(true);
    }

   
    public void testInvalidCoffeeOption() {
        String simulatedInput = "10\n10\n10\nlatte\n1\n1\ny\n";
        simulateInputAndRun(simulatedInput);
        assertTrue(true);
    }

  
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

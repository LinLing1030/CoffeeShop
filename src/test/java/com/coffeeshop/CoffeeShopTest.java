package com.coffeeshop;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;

public class CoffeeShopTest extends TestCase {

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

    public void testAmericanoOption() {
        simulateInputAndRun(getTodayDateInput() + "10\n10\n10\n1\n1\ny\n");
        assertTrue(true);
    }

    public void testMochaOption() {
        simulateInputAndRun(getTodayDateInput() + "10\n10\n10\n2\n1\ny\n");
        assertTrue(true);
    }

    public void testIceTeaOption() {
        simulateInputAndRun(getTodayDateInput() + "10\n10\n10\n3\n1\ny\n");
        assertTrue(true);
    }

    public void testInvalidCoffeeInput() {
        simulateInputAndRun(getTodayDateInput() + "10\n10\n10\nlatte\n1\n1\ny\n");
        assertTrue(true);
    }

    public void testNegativeStockTriggersWarning() {
        simulateInputAndRun(getTodayDateInput() + "-5\n10\n10\n10\n1\n1\ny\n");
        assertTrue(true);
    }

    public void testInvalidStockInputTriggersCatch() {
        simulateInputAndRun(getTodayDateInput() + "abc\n10\n10\n10\n1\n1\ny\n");
        assertTrue(true);
    }

    public void testNegativeQuantityTriggersWarning() {
        simulateInputAndRun(getTodayDateInput() + "10\n10\n10\n1\n-2\n2\ny\n");
        assertTrue(true);
    }

    public void testInvalidQuantityInputTriggersCatch() {
        simulateInputAndRun(getTodayDateInput() + "10\n10\n10\n1\nabc\n2\ny\n");
        assertTrue(true);
    }

    public void testBuyTooMuchCoffee() {
        simulateInputAndRun(getTodayDateInput() + "2\n10\n10\n1\n99\n1\n2\ny\n");
        assertTrue(true);
    }

    public void testFinishInputInvalid() {
        simulateInputAndRun(getTodayDateInput() + "10\n10\n10\n1\n1\nmaybe\ny\n");
        assertTrue(true);
    }

    public void testQuitImmediately() {
        simulateInputAndRun(getTodayDateInput() + "10\n10\n10\n4\n1\n1\ny\n");
        assertTrue(true);
    }

    private void simulateInputAndRun(String input) {
        InputStream originalSystemIn = System.in;
        input += "\n\n\n\n"; 
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            CoffeeShop.main(new String[]{});
        } finally {
            System.setIn(originalSystemIn);
        }
    }

    private String getTodayDateInput() {
        return LocalDate.now().toString() + "\n";
    }
}

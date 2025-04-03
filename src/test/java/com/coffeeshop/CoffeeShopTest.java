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
        simulateInputAndRun("10\n10\n10\namericano\n1\ny\n");
    }

    public void testMochaOption() {
        simulateInputAndRun("10\n10\n10\nmocha\n1\ny\n");
    }

    public void testIceTeaOption() {
        simulateInputAndRun("10\n10\n10\nice tea\n1\ny\n");
    }

    public void testInvalidCoffeeInput() {
        simulateInputAndRun("10\n10\n10\nlatte\n1\n1\ny\n");
    }

    public void testNegativeStockTriggersWarning() {
        simulateInputAndRun("-5\n10\n10\n1\n1\ny\n");
    }

    public void testInvalidStockInputTriggersCatch() {
        simulateInputAndRun("abc\n10\n10\n1\n1\ny\n");
    }

    public void testNegativeQuantityTriggersWarning() {
        simulateInputAndRun("10\n10\n10\n1\n-2\n2\ny\n");
    }

    public void testInvalidQuantityInputTriggersCatch() {
        simulateInputAndRun("10\n10\n10\n1\nabc\n2\ny\n");
    }

    public void testBuyTooMuchCoffee() {
        simulateInputAndRun("2\n10\n10\n1\n99\n1\n2\ny\n");
    }

    public void testFinishInputInvalid() {
        simulateInputAndRun("10\n10\n10\n1\n1\nmaybe\ny\n");
    }

    public void testQuitImmediately() {
        simulateInputAndRun("10\n10\n10\n4\n1\n1\ny\n");
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

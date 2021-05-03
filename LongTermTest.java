import oop.ex3.spaceship.*;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Test;

import java.util.Map;

/**
 * The test for the LongTermStorage class of the starship USS Discovery's board.
 */
public class LongTermTest {

    private static final String BASEBALL_BAT_NAME = "baseball bat";
    private static final int MY_CAPACITY = 1000;

    private static LongTermStorage myStorage = new LongTermStorage();
    private static Map<String, Integer> inventory = myStorage.getInventory();

    private static final Item baseballBat = ItemFactory.createSingleItem(BASEBALL_BAT_NAME);
    private static int baseballBatAmountInStorage = myStorage.getItemCount(BASEBALL_BAT_NAME);

    private static int returnValue;

    /**
     * Updates the inventory and the amount of baseball bat in storage.
     */
    private void updateVariables(){
        baseballBatAmountInStorage = myStorage.getItemCount(BASEBALL_BAT_NAME);
        inventory = myStorage.getInventory();
    }

    /**
     * Test the resetInventory method.
     */
    @After
    public void initializeStorage(){
        myStorage.resetInventory();
    }

    /**
     * Executes asserts for testing different scenarios.
     * @param expectedReturnValue The expected value of the return code.
     * @param expectedAmountInMyStorage The expected amount of baseball bat in storage.
     * @param expectedInventoryValue The expected value of baseball bat in the storage's inventory.
     * @param expectedAvailableCapacity The expected available capacity.
     */
    public void lockerAsserts(int expectedReturnValue, int expectedAmountInMyStorage,
                              Integer expectedInventoryValue, int expectedAvailableCapacity){

        assertEquals(expectedReturnValue, returnValue);

        assertEquals(expectedAmountInMyStorage, baseballBatAmountInStorage);

        assertEquals(expectedInventoryValue, inventory.get(BASEBALL_BAT_NAME));

        assertEquals(expectedAvailableCapacity, myStorage.getAvailableCapacity());
    }

    /**
     *
     * @param amountToAddToMyStorage The amount of baseball bats to add to storage.
     * @param expectedAmountInMyStorage The expected amount of baseball bat in storage.
     * @param expectedInventoryValue The expected value of baseball bat in the storage's inventory.
     * @param expectedAvailableCapacity The expected available capacity.
     * @param expectedReturnValue The expected value of the return code.
     */
    public void testAddItemScenario(int amountToAddToMyStorage,
                                    int expectedAmountInMyStorage,
                                    Integer expectedInventoryValue,
                                    int expectedAvailableCapacity,
                                    int expectedReturnValue){

        returnValue = myStorage.addItem(baseballBat, amountToAddToMyStorage);

        updateVariables();

        lockerAsserts(expectedReturnValue, expectedAmountInMyStorage,
                expectedInventoryValue, expectedAvailableCapacity);
    }

    /**
     * Test the getCapacity method.
     */
    @Test
    public void testGetCapacity(){
        assertEquals(MY_CAPACITY, myStorage.getCapacity());
    }

    /**
     * Tests the addItem method and the related methods to it:
     * getItemCount, getAvailableCapacity and getInventory.
     */
    @Test
    public void testAddItemAndRelatedMethods(){

        testAddItemScenario(0, 0,
                null, 1000, 0);

        myStorage.resetInventory();
        assertEquals(0, inventory.size());

        testAddItemScenario(-1, 0,
                null, 1000, -1);

        testAddItemScenario(1, 1,
                1, 998, 0);

        testAddItemScenario(215, 216,
                216, 568, 0);

        testAddItemScenario(284, 500,
                500, 0, 0);

        testAddItemScenario(1, 500,
                500, 0, -1);
    }

    /**
     * Tests the resetInventory method.
     */
    @Test
    public void testResetInventory() {
        myStorage.addItem(baseballBat, 500);
        myStorage.resetInventory();
        assertEquals(0, inventory.size());
    }
}
import oop.ex3.spaceship.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;

import java.util.Map;

/**
 * The test for the Locker class of the lockers on the starship USS Discovery's board.
 */
public class LockerTest {

    private static final String BASEBALL_BAT_NAME = "baseball bat";
    private static final String FOOTBALL = "football";
    private static final Item baseballBat = ItemFactory.createSingleItem(BASEBALL_BAT_NAME);
    private static final Item football = ItemFactory.createSingleItem(FOOTBALL);

    private static final String RETURN_VALUE_MESSAGE = "Return value incorrect";
    private static final String AMOUNT_IN_LOCKER_MESSAGE = "Amount in locker incorrect";
    private static final String AMOUNT_IN_STORAGE_MESSAGE = "Amount in storage incorrect";
    private static final String INVENTORY_VALUE_MESSAGE = "Inventory value incorrect";
    private static final String AVAILABLE_CAPACITY_MESSAGE = "Available capacity incorrect";
    private static final String INVENTORY_SIZE_MESSAGE = "Inventory size incorrect";

    private static final int MY_CAPACITY = 100;
    private static Locker myLocker = new Locker(MY_CAPACITY);

    private static LongTermStorage myLongTermStorage = Locker.longTermStorage;
    private static int baseballBatAmountInLocker = myLocker.getItemCount(BASEBALL_BAT_NAME);
    private static int baseballBatAmountInStorage =myLongTermStorage.getItemCount(BASEBALL_BAT_NAME);
    private static Map<String, Integer> inventory = myLocker.getInventory();

    private static int returnValue;

    /**
     * Updates the quantities of the baseball bat item in the locker and in the storage.
     */
    private void updateBaseballBatAmount(){
        baseballBatAmountInLocker = myLocker.getItemCount(BASEBALL_BAT_NAME);
        baseballBatAmountInStorage = myLongTermStorage.getItemCount(BASEBALL_BAT_NAME);
    }

    /**
     * Updates the locker inventory.
     */
    private void updateMyInventory(){
        inventory = myLocker.getInventory();
    }

    /**
     * Initializes the lockers.
     */
    @After
    public void initializeLockers(){
        myLocker = new Locker(MY_CAPACITY);
        myLongTermStorage.resetInventory();
    }

    /**
     * The asserts to commit for the different scenarios to check.
     * @param expectedReturnValue The expected value of the return code.
     * @param expectedAmountInMyLocker The expected amount of the baseball bat in the locker
     * @param expectedInventoryValue The expected amount of the baseball bat in the inventory.
     * @param expectedAvailableCapacity The expected available capacity.
     * @param expectedInventorySize The expected size of the inventory.
     */
    public void lockerAsserts(int expectedReturnValue, int expectedAmountInMyLocker,
                              Integer expectedInventoryValue, int expectedAvailableCapacity,
                              int expectedInventorySize){

        assertEquals(RETURN_VALUE_MESSAGE, expectedReturnValue, returnValue);

        assertEquals(AMOUNT_IN_LOCKER_MESSAGE, expectedAmountInMyLocker, baseballBatAmountInLocker);

        assertEquals(INVENTORY_VALUE_MESSAGE, expectedInventoryValue, inventory.get(BASEBALL_BAT_NAME));

        assertEquals(AVAILABLE_CAPACITY_MESSAGE, expectedAvailableCapacity, myLocker.getAvailableCapacity());

        assertEquals(INVENTORY_SIZE_MESSAGE, expectedInventorySize, inventory.size());
    }

    /**
     * Executes a scenario of adding an item.
     * @param amountToAddToMyLocker The amount of baseball bat to add to the locker.
     * @param amountToAddToLongTermStorage The amount of baseball bat to add storage.
     * @param expectedReturnValue The expected value of the return code.
     * @param expectedAmountInMyLocker The expected amount of baseball bat in the locker.
     * @param expectedAmountInLongTermStorage The expected amount of the baseball bat in storage.
     * @param expectedInventoryValue The expected amount of the baseball bat in the locker.
     * @param expectedAvailableCapacity The expected available capacity.
     * @param expectedInventorySize The expected size of the inventory.
     */
    public void testAddItemScenario(int amountToAddToMyLocker,
                                    int amountToAddToLongTermStorage,
                                    int expectedReturnValue,
                                    int expectedAmountInMyLocker,
                                    int expectedAmountInLongTermStorage,
                                    Integer expectedInventoryValue,
                                    int expectedAvailableCapacity,
                                    int expectedInventorySize){

        myLongTermStorage.addItem(baseballBat, amountToAddToLongTermStorage);
        returnValue = myLocker.addItem(baseballBat, amountToAddToMyLocker);

        updateBaseballBatAmount();
        updateMyInventory();

        assertEquals(AMOUNT_IN_STORAGE_MESSAGE, expectedAmountInLongTermStorage,
                baseballBatAmountInStorage);

        lockerAsserts(expectedReturnValue, expectedAmountInMyLocker,
                expectedInventoryValue, expectedAvailableCapacity, expectedInventorySize);
    }

    /**
     * Executs a scenario of removing an item.
     * @param amountToAddToMyLocker The amount of baseball bat to add to the locker.
     * @param amountToRemoveFromMyLocker The amount of baseball bat to remove from the locker.
     * @param expectedReturnValue The expected value of the return code.
     * @param expectedAmountInMyLocker The expected amount of baseball bat in the locker.
     * @param expectedInventoryValue The expected value ot the baseball bat in the inventory.
     * @param expectedAvailableCapacity The expected available capacity.
     * @param expectedInventorySize The expected size of the inventory.
     */
    public void testRemoveItemScenario(int amountToAddToMyLocker,
                                       int amountToRemoveFromMyLocker,
                                       int expectedReturnValue,
                                       int expectedAmountInMyLocker,
                                       Integer expectedInventoryValue,
                                       int expectedAvailableCapacity,
                                       int expectedInventorySize){

        myLocker.addItem(baseballBat, amountToAddToMyLocker);
        returnValue = myLocker.removeItem(baseballBat, amountToRemoveFromMyLocker);

        updateBaseballBatAmount();
        updateMyInventory();

        lockerAsserts(expectedReturnValue, expectedAmountInMyLocker,
                expectedInventoryValue, expectedAvailableCapacity, expectedInventorySize);
    }

    /**
     * Tests the getCapacity method.
     */
    @Test
    public void testGetCapacity(){
        assertEquals(MY_CAPACITY, myLocker.getCapacity());
    }

    /**
     * Tests the addItem method and the related methods to it:
     * getItemCount, getAvailableCapacity and getInventory.
     */
    @Test
    public void testAddItemAndRelatedMethods() {

        testAddItemScenario(0, 0,
                0,0, 0,
                null, 100, 0);

        testAddItemScenario(51, 0,
                -1, 0, 0,
                null, 100, 0);

        testAddItemScenario(1, 0,
                0, 1, 0,
                1, 98, 1);

        testAddItemScenario(-1, 0,
                -1, 1, 0,
                1, 98, 1);

        testAddItemScenario(24, 0,
                0, 25, 0,
                25, 50, 1);

        testAddItemScenario(1, 0,
                1, 10, 16,
                10, 80, 1);

        testAddItemScenario(15, 0,
                0, 25, 16,
                25, 50, 1);

        testAddItemScenario(1, 0,
                1, 10, 32,
                10, 80, 1);

        testAddItemScenario(16, 468,
                -1, 10, 500,
                10, 80, 1);
    }


    /**
     * Tests the removeItem method and the related methods to it:
     * getItemCount, getAvailableCapacity and getInventory.
     */
    @Test
    public void testRemoveItemAndRelatedMethods(){

        testRemoveItemScenario(0, 0, 0,
                0, null,
                100, 0);

        testRemoveItemScenario(0, -1, -1,
                0, null,
                100, 0);

        testRemoveItemScenario(10, 11, -1,
                10, 10,
                80, 1);

        testRemoveItemScenario(0, 5, 0,
                5, 5,
                90, 1);

        testRemoveItemScenario(0, 5, 0,
                0, null,
                100, 0);
    }

    /**
     * Tests the addItem method for an edge case regarding
     * rounding the amount of an item to keep in the locker.
     */
    @Test
    public void testAddItemRoundingKeepItemsScenario1(){

        myLocker = new Locker(101);

        testAddItemScenario(25, 0,
                0, 25, 0,
                25, 51, 1);

        testAddItemScenario(1, 0,
                1, 10, 16,
                10, 81, 1);
    }

    /**
     * Tests the addItem method for an edge case regarding
     * rounding the amount of an item to keep in the locker.
     */
    @Test
    public void testAddItemRoundingKeepItemsScenario2(){

        myLocker = new Locker(99);

        testAddItemScenario(25, 0,
                1, 9, 16,
                9, 81, 1);
    }

    /**
     * Executes the asserts of contradicting items scenario.
     * @param item1 First item to test the second item contradicting it.
     * @param item2 Second item to test it contradicting the first item.
     */
    public void contradictingItemsAsserts(Item item1, Item item2) {
        returnValue = myLocker.addItem(item1, 1);
        assertEquals(RETURN_VALUE_MESSAGE, -2, returnValue);
        myLocker.removeItem(item2, 1);
        returnValue = myLocker.addItem(item1, 1);
        assertEquals(RETURN_VALUE_MESSAGE, -2, returnValue);
        myLocker.removeItem(item2, 1);
        returnValue = myLocker.addItem(item1, 2);
        assertEquals(RETURN_VALUE_MESSAGE, 0, returnValue);
    }

    /**
     * Executes the asserts of contradicting items scenarios.
     */
    @Test
    public void testContradictingItems(){
        myLocker.addItem(baseballBat, 2);
        contradictingItemsAsserts(football, baseballBat);
        contradictingItemsAsserts(baseballBat, football);
    }
}
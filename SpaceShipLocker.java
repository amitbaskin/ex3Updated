import oop.ex3.spaceship.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The super class of Locker and LongTermStorage, lockers of starship USS Discovery's board.
 */
public class SpaceShipLocker {

    public static final int SUCCESS_CODE = 0;
    public static final int NEGATIVE_CODE = -1;

    public static final String ERROR_MESSAGE =
            "Error: Your request cannot be completed at this time.";
    public static final String NO_ROOM_MESSAGE = " Problem: no room for ";
    public static final String ITEMS_OF_TYPE_MESSAGE = " items of type ";

    private int capacity;
    private Map<String, Integer> inventory;
    private int availableCapacity;

    /**
     * Initializes an object of a Spaceship Locker.
     * @param capacity The capacity.
     */
    public SpaceShipLocker(int capacity) {
        this.capacity = capacity;
        this.inventory = new HashMap<>();
        this.availableCapacity = capacity;
    }

    /**
     * Increases the amount of the available capacity.
     * @param increaseAmount The amount in which to increase the available capacity.
     */
    public void increaseAvailableCapacity(int increaseAmount) {
        this.availableCapacity += increaseAmount;
    }

    /**
     * Decreases the amount of available capacity.
     * @param decreaseAmount The amount in which to decrease the amount of available capacity.
     */
    public void decreaseAvailableCapacity(int decreaseAmount) {
        this.availableCapacity -= decreaseAmount;
    }

    /**
     * Increases the inventory value of the given item.
     * @param item The item to increase it's value.
     * @param amount The amount in which to increase the item's value.
     */
    public void increaseInventoryValue(Item item, int amount) {
        String itemType = item.getType();
        inventory.put(itemType, getItemCount(itemType) + amount);
    }

    /**
     * Decreases the inventory value of the given item.
     * @param item The item to decrease it's value.
     * @param amount The amount in which to decrease the item's value.
     */
    public void decreaseInventoryValue(Item item, int amount) {
        String itemType = item.getType();
        inventory.put(itemType, getItemCount(itemType) - amount);
    }

    /**
     * Puts to inventory the given item's type as key and the given amount as value.
     * @param item The item to put it's type as key.
     * @param amount The amount to put as value.
     */
    public void putToInventory(Item item, int amount) {
        inventory.put(item.getType(), amount);
    }

    /**
     * Returns the capacity.
     * @return the capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the quantity of the item in the locker.
     * @param itemType The type of the item to return it's quantity.
     * @return the quantity of the item in the locker.
     */
    public int getItemCount(String itemType) {
        if (inventory.containsKey(itemType)){
            return inventory.get(itemType);
        }
        else return 0;
    }

    /**
     * Returns the current inventory.
     * @return the current inventory.
     */
    public Map<String, Integer> getInventory() {
        return inventory;
    }

    /**
     * Returns the available capacity.
     * @return the available capacity.
     */
    public int getAvailableCapacity() {
        return availableCapacity;
    }

    /**
     * Resets the inventory and the available capacity.
     */
    public void resetInventory() {
        inventory.clear();
        availableCapacity = capacity;
    }

    /**
     * Prints a general message that says that a certain action was unsuccessful.
     * @return the code that indicates that a certain action was unsuccessful.
     */
    public int errorMessage() {
        System.out.println(ERROR_MESSAGE);
        return NEGATIVE_CODE;
    }

    /**
     * Prints a message that says that there wasn't any room for the given amount of the given item.
     * @param item The item that was attempted to add,
     * @param amount The amount of the item that was attempted to add.
     * @return the code that indicates that the action was unsuccessful.
     */
    public int noRoomMessage(Item item, int amount) {
        System.out.println(ERROR_MESSAGE + NO_ROOM_MESSAGE +
                amount + ITEMS_OF_TYPE_MESSAGE + item.getType());
        return NEGATIVE_CODE;
    }

    /**
     * Returns the units amount that the given amount of the given item takes.
      * @param item The given item.
     * @param amount The given amount.
     * @return the units amount that the given amount of the given item takes.
     */
    public int itemAmountCapacity(Item item, int amount) {
        return amount * item.getVolume();
    }

    /**
     * Returns whether or not there is room for the given amount of the given item.
     * @param item The given item.
     * @param amount The given amount
     * @return true if there is room and false otherwise.
     */
    public boolean isRoomForItems(Item item, int amount) {
        return itemAmountCapacity(item, amount) <= getAvailableCapacity();
    }

    /**
     * Returns whether or not the inventory contain the given item.
     * @param item The given item.
     * @return true if it does contain it and false otherwise.
     */
    public boolean isInventoryContainItem(Item item) {
        return inventory.containsKey(item.getType());
    }

    /**
     * Adds a new item with it's amount to the inventory and decreases the available capacity.
     * @param item The item to add.
     * @param amount The amount of the item to add.
     */
    private void addNewItem(Item item, int amount) {
        inventory.put(item.getType(), amount);
        decreaseAvailableCapacity(itemAmountCapacity(item, amount));
    }

    /**
     * Executes the adding action.
     * @param item The item to add.
     * @param amount The amount of the item to add.
     * @return the code that indicates that the action was successful.
     */
    public int addItemSuccessScenario(Item item, int amount) {
        if(amount == 0){
            return SUCCESS_CODE;
        }
        if (isInventoryContainItem(item)) {
            increaseInventoryValue(item, amount);
            decreaseAvailableCapacity(itemAmountCapacity(item, amount));
        } else addNewItem(item, amount);
        return SUCCESS_CODE;
    }

    /**
     * If the value of the given item in the inventory reaches zero then it is being removed from the
     * inventory.
     * @param item The item to check if to remove from the inventory.
     */
    public void ifToDeleteItem(Item item){
        String itemType = item.getType();
        if(inventory.get(itemType) == 0){
            inventory.remove(itemType);
        }
    }
}
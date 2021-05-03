import oop.ex3.spaceship.*;
import java.util.Map;

/**
 * The USS Discovery single centralized long-term storage.
 */
public class LongTermStorage extends SpaceShipLocker{
    private static final int STORAGE_CAPACITY = 1000;

    /**
     * This constructor initializes a Long-Term Storage object.
     */
    public LongTermStorage() {
        super(STORAGE_CAPACITY);
    }

    /**
     * Returns the capacity.
     * @return the capacity.
     */
    public int getCapacity() {
        return super.getCapacity();
    }

    /**
     * Returns the available capacity.
     * @return the available capacity.
     */
    public int getAvailableCapacity() {
        return super.getAvailableCapacity();
    }

    /**
     * Returns the current inventory.
     * @return the current inventory.
     */
    public Map<String, Integer> getInventory() {
        return super.getInventory();
    }

    /**
     * Returns the amount of the given item which is currently in storage.
     * @param itemType The item to return its amount in storage.
     * @return the amount of the given item which is currently in storage.
     */
    public int getItemCount(String itemType) {
        return super.getItemCount(itemType);
    }

    /**
     * Resets the inventory and the available capacity.
     */
    public void resetInventory() {
        super.resetInventory();
    }

    /**
     * Commits the action of adding an item into storage.
     * @param item The item of which an amount of it is attempted to be moved into storage.
     * @param amount The amount of the item that is attempted to move into storage.
     * @return the code of the result of the action.
     */
    public int addItem(Item item, int amount){

        if(amount < 0){
            return errorMessage();

        } if (!isRoomForItems(item, amount)) {
            return noRoomMessage(item, amount);

        } else {
            return addItemSuccessScenario(item, amount);
        }
    }
}
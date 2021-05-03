import oop.ex3.spaceship.*;
import java.util.Map;

/**
 * A locker on the starship USS Discovery's board.
 * A locker can contain different types of items.
 */
public class Locker extends SpaceShipLocker{

    private static final int WARNING_CODE = 1;
    private static final int CONTRADICTION_CODE = -2;

    private static final String WARNING_MESSAGE =
            "Warning: Action Successful, but has caused items to be moved to storage";
    private static final String CONTENT_MESSAGE = " Problem: the locker does not contain ";
    private static final String NEGATIVE_AMOUNT_MESSAGE =
            " Problem: cannot remove a negative number of items of type ";
    private static final String CONTRADICTION_MESSAGE_PART_1 =
            " Problem: the locker cannot contain items of type ";
    private static final String CONTRADICTION_MESSAGE_PART_2 = ", as it contains a contradicting item";

    private static final double MOVE_PERCENTAGE = 0.5;
    private static final double KEEP_PERCENTAGE = 0.2;
    private final int KEEP_UNITS_AMOUNT = setKeepUnitsAmount();

    private static final String BASEBALL_BAT_NAME = "baseball bat";
    private static final String FOOTBALL = "football";
    private static final String baseballBatType = ItemFactory.createSingleItem(BASEBALL_BAT_NAME).getType();
    private static final String footballType = ItemFactory.createSingleItem(FOOTBALL).getType();

    protected static LongTermStorage longTermStorage = new LongTermStorage();

    /**
     * This constructor initializes a Locker object with the given capacity.
     * @param capacity the capacity to initialize the locker with.
     */
    public Locker(int capacity){
        super(capacity);
    }

    /**
     * Sets the amount of units the locker can have of the same item.
     * @return the amount of units the locker can have of the same item.
     */
    private int setKeepUnitsAmount(){
        return (int) (KEEP_PERCENTAGE * (double) getCapacity());
    }

    /**
     * Return the capacity.
     * @return the capacity.
     */
    public int getCapacity() {
        return super.getCapacity();
    }

    /**
     * Returns the amount of available capacity.
     * @return the amount of available capacity.
     */
    public int getAvailableCapacity() {
        return super.getAvailableCapacity();
    }

    /**
     * returns the current inventory.
     * @return the current inventory.
     */
    public Map<String, Integer> getInventory() {
        return super.getInventory();
    }

    /**
     * Returns the quantity of a given item in the locker.
     * @param itemType the item to retrieve its quantity.
     * @return the quantity of a given item in the locker.
     */
    public int getItemCount(String itemType) {
        return super.getItemCount(itemType);
    }

    /**
     * Returns the ratio between the capacity of a given amount of a given item, and the total capacity.
     * @param item The given item.
     * @param amount The given Amount.
     * @return the ratio between the capacity of a the amount of the item, and the total capacity.
     */
    private double itemTotalCapacityRatio(Item item, int amount) {
        return (double) itemAmountCapacity(item, amount) / (double) getCapacity();
    }

    /**
     * Checks whether or not items should be moved out of the locker and into the storage.
     * @param item The item that might take to much room.
     * @param amount The amount of the item that might be to big.
     * @return true if items of the given item should be moved and false otherwise.
     */
    private boolean isMoveItems(Item item, int amount){
        return itemTotalCapacityRatio(item, amount + getItemCount(item.getType()))
                > MOVE_PERCENTAGE;
    }

    /**
     * Checks whether or not there is room in the storage for the given amount of the given item.
     * @param item The given item potentially move to storage.
     * @param amount The amount of the item to potentially move to storage.
     * @return true if there is room in the storage for the amount of the item and false otherwise.
     */
    private boolean isRoomForItemsInStorage(Item item, int amount){
        return longTermStorage.getAvailableCapacity() >= itemAmountCapacity(item, amount);
    }

    /**
     * Returns the amount of the given item that the locker can keep.
     * @param item The given item to return the amount of it the locker can keep.
     * @return the amount of the given item that the locker can keep.
     */
    private int keepItemsAmount(Item item){
        return KEEP_UNITS_AMOUNT / item.getVolume();
    }

    /**
     * Returns the amount of the given item that should be moved into the storage.
     * @param item The item to move an amount of it to the storage.
     * @param amount The amount of the given item that was attempted to insert into the locker,
     *               and which caused the moving to the storage.
     * @return the amount of the given item that should be moved into the storage.
     */
    private int itemsAmountToMoveToStorage(Item item, int amount){
        return getItemCount(item.getType()) + amount - keepItemsAmount(item);
    }

    /**
     * Returns the code that indicates that the adding attempt of the given item was unsuccessful,
     * and prints a the message that says that the locker
     * does not contain the given amount of the given item.
     * @param item The item that was attempted to insert an amount of it into the locker.
     * @param amount The amount that was attempted to insert to the locker.
     * @return the code that indicates that the adding attempt of the given item was unsuccessful.
     */
    private int contentMessage(Item item, int amount){
        System.out.println(ERROR_MESSAGE + CONTENT_MESSAGE +
                amount + ITEMS_OF_TYPE_MESSAGE + item.getType());
        return NEGATIVE_CODE;
    }

    /**
     * Returns the code that indicates that there was a contradiction as a result of the attempt to insert
     * an item into the locker, and prints a the message that says that there wasn't there was a
     * contradiction between the item that was attempted to insert to the locker and an item which is
     * already in the locker.
     * @param item The item that was attempted to insert into the locker.
     * @return the code that indicates that there was a contradiction as a result of the attempt to insert
     * an item into the locker.
     */
    private int contradictionMessage(Item item) {
        System.out.println(ERROR_MESSAGE + CONTRADICTION_MESSAGE_PART_1 +
                item.getType() + CONTRADICTION_MESSAGE_PART_2);
        return CONTRADICTION_CODE;
    }

    /**
     * Returns the code that indicates that due to the insert that was committed, items had to be moved to
     * storage, and prints a message that says that.
     * @return the code that indicates that due to the insert that was committed, items had to be moved to
     * storage.
     */
    private int warningMessage(){
        System.out.println(WARNING_MESSAGE);
        return WARNING_CODE;
    }

    /**
     * Returns the code that indicates that the adding attempt of the given item was unsuccessful,
     * and prints a the message that says that a negative amount of the item cannot be removed from the
     * locker.
     * @param item The item that was attempted to remove a negative amount of it.
     * @return the code that indicates that the adding attempt of the given item was unsuccessful.
     */
    private int negativeAmountMessage(Item item){
        System.out.println(ERROR_MESSAGE + NEGATIVE_AMOUNT_MESSAGE + item.getType());
        return NEGATIVE_CODE;
    }

    /**
     * Returns the amount that was actually moved from the locker.
     * @param item The item of which an amount of it was moved.
     * @return the amount that was actually moved from the locker.
     */
    private int amountToMoveFromLocker(Item item){
      return getItemCount(item.getType()) - keepItemsAmount(item);
    }

    /**
     * Returns whether of not a given item contradicts an item which is already in the locker.
     * @param itemType The type of item that is attempted to insert into the locker.
     * @return true if there is a contradiction and false otherwise.
     */
    private boolean isContradictingItems(String itemType) {
        if (itemType.equals(footballType)) {
            return getInventory().containsKey(baseballBatType);
        } else if (itemType.equals(baseballBatType)) {
            return getInventory().containsKey(footballType);
        } else return false;
    }

    /**
     * If possible, it commits the action of inserting the given amount of the given item into the locker.
     * @param item The item to add.
     * @param amount The amount of the item to add.
     * @return the code of the result of this action.
     */
    public int addItem(Item item, int amount) {

        if(isContradictingItems(item.getType())){return contradictionMessage(item);}

        if(amount < 0) return errorMessage();

        if (!isRoomForItems(item, amount)) {return noRoomMessage(item, amount);}

        int amountToMove = itemsAmountToMoveToStorage(item, amount);
        if (isMoveItems(item, amount) & isRoomForItemsInStorage(item, amountToMove)) {
            increaseAvailableCapacity(itemAmountCapacity(item, amountToMoveFromLocker(item)));
            putToInventory(item, keepItemsAmount(item));
            longTermStorage.addItem(item, amountToMove);
            return warningMessage();

        } else if (isMoveItems(item, amount) & !isRoomForItemsInStorage(item, amount)) {
            noRoomMessage(item, itemsAmountToMoveToStorage(item, amount));
            return NEGATIVE_CODE;

        } else {
            return addItemSuccessScenario(item, amount);
        }
    }

    /**
     * If possible, it removes the given amount of the given item.
     * @param item The item of which an amount of it is attempted to remove.
     * @param amount The amount of the item to remove.
     * @return the code of the result of this action.
     */
    public int removeItem(Item item, int amount) {

        if(amount > getItemCount(item.getType())) {
            return contentMessage(item, amount);

        } else if(amount < 0) {
            return negativeAmountMessage(item);

        } else {
            decreaseInventoryValue(item, amount);
            ifToDeleteItem(item);
            increaseAvailableCapacity(itemAmountCapacity(item, amount));
            return SUCCESS_CODE;
        }
    }
}
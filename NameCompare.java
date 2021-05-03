import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

/**
 * A comparator that compares Hotel objects according to their property name.
 */
public class NameCompare implements Comparator<Hotel> {

    /**
     * The compare method.
     * @param hotel1 First hotel.
     * @param hotel2 Second hotel.
     * @return the return value that the method compareTo returns.
     */
    public int compare(Hotel hotel1, Hotel hotel2){
        return hotel1.getPropertyName().compareTo(hotel2.getPropertyName());
    }
}
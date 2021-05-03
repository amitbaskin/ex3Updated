import oop.ex3.searchengine.Hotel;

import java.util.Comparator;

/**
 * A comparator that compares between hotels according to their star rankings.
 * Hotels that have the same star ranking are sorted alphabetically.
 */
public class RatingCompare implements Comparator<Hotel> {

    /**
     * The compare method.
     * @param hotel1 The first hotel.
     * @param hotel2 The second Hotel
     * @return -1 if the first hotel has greater star rating than the second hotel,
     * 1 if it is the opposite and if they're equal it returns the value that a NameCompare comparator
     * returns according to their alphabetic order.
     */
    public int compare(Hotel hotel1, Hotel hotel2){
        NameCompare nameCompare = new NameCompare();
        if (hotel1.getStarRating() > hotel2.getStarRating()) return -1;
        if (hotel1.getStarRating() < hotel2.getStarRating()) return 1;
        else return nameCompare.compare(hotel1, hotel2);
    }
}
import java.util.Comparator;

/**
 * A comparator that compares DistanceHotel objects according to the number of POI's they have.
 * If they have the same number then it compares between them alphabetically.
 */
public class PoiCompare implements Comparator<DistanceHotel> {

    /**
     *
     * @param distanceHotel1 The first DistanceHotel object
     * @param distanceHotel2 The second DistanceHotel object
     * @return -1 if the first DistanceHotel object has more POI's than the second DistanceHotel object.
     * If it the opposite then it returns 1, and it they are equal then it returns the value that a
     * NameCompare comparator returns as a result of comparing between them.
     */
    public int compare(DistanceHotel distanceHotel1, DistanceHotel distanceHotel2){
        NameCompare nameCompare = new NameCompare();
        if (distanceHotel1.getNumPOI() > distanceHotel2.getNumPOI()) return -1;
        if (distanceHotel1.getNumPOI() < distanceHotel2.getNumPOI()) return 1;
        else return nameCompare.compare(distanceHotel1.getHotel(), distanceHotel2.getHotel());
    }
}
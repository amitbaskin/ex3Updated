import java.util.Comparator;

/**
 * A comparator that compares DistanceHotel objects according to their distance attribute.
 * If they have the same distance attribute then it compares between them according to the number of POI's
 * they have.
 */
public class DistanceCompare implements Comparator<DistanceHotel> {

    /**
     * The compare method.
     * @param distanceHotel1 The first DistanceHotel object.
     * @param distanceHotel2 The second DistanceHotel object.
     * @return -1 if the distance attribute of the first DistanceHotel object is smaller than the one of
     * the second DistanceHotel object, it returns 1 if it is the opposite, and if they are equal then it
     * returns the value that a PoiCompare comparator returns as a result of comparing between them.
     */
    public int compare(DistanceHotel distanceHotel1, DistanceHotel distanceHotel2){
        PoiCompare poiCompare =new PoiCompare();
        if(distanceHotel1.getDistance() < distanceHotel2.getDistance()) return -1;
        if(distanceHotel1.getDistance() > distanceHotel2.getDistance()) return 1;
        else return poiCompare.compare(distanceHotel1, distanceHotel2);
    }
}

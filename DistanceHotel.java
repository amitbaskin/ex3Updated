import oop.ex3.searchengine.Hotel;

/**
 * A class that enables to add another attribute to an Hotel object, which is the attribute of distance
 * that is used to in the BoopinSite class in order to sort an Array of Hotel objects according to the
 * distance from a given position.
 */
public class DistanceHotel {
    private final Hotel hotel;
    private final double distance;

    /**
     * This constructor initializes a DistanceHotel object which holds an Hotel hotel attribute and a
     * double distance attribute.
     * @param hotel The Hotel object it holds.
     * @param distance The distance it holds.
     */
    public DistanceHotel(Hotel hotel, double distance){
        this.hotel = hotel;
        this.distance = distance;
    }

    /**
     * Returns the Hotel object it holds.
     * @return the Hotel object it holds.
     */
    public Hotel getHotel(){
        return hotel;
    }

    /**
     * Returns the distance attribute.
     * @return the distance attribute.
     */
    public double getDistance(){
        return distance;
    }

    /**
     * Returns the number of POI's the Hotel object it holds has.
     * @return the number of POI's the Hotel object it holds has.
     */
    public int getNumPOI(){
        return hotel.getNumPOI();
    }

    /**
     * Returns the property name of the Hotel object it holds.
     * @return the property name of the Hotel object it holds.
     */
    public String getName(){
        return hotel.getPropertyName();
    }
}
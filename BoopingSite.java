import oop.ex3.searchengine.HotelDataset;
import oop.ex3.searchengine.Hotel;

import java.util.*;

/**
 * An implementation of a new hotel booking site, that allows for personalized search methodologies.
 */
public class BoopingSite {

    private static final int LATITUDE_MAX = 90;
    private static final int LATITUDE_MIN = -90;
    private static final int LONGITUDE_MAX = 180;
    private static final int LONGITUDE_MIN = -180;

    private final Hotel [] boopingSiteHotels;

    /**
     * This constructor receives as parameter a string, which is the name of the dataset.
     * @param name The name of the dataset.
     */
    public BoopingSite(String name){
        boopingSiteHotels = HotelDataset.getHotels(name);
    }

    /**
     * Converts an ArrayList of Hotel objects into a regular Array of Hotel objects.
     * @param hotelArrayList The ArrayList to convert.
     * @return The Array of Hotel objects as the result of the conversion.
     */
    private Hotel[] hotelArrayListToHotelArray(ArrayList<Hotel> hotelArrayList) {
        Hotel[] cityHotels = new Hotel[hotelArrayList.size()];
        int counter = 0;
        for (Hotel hotel : hotelArrayList) {
            cityHotels[counter] = hotel;
            ++counter;
        }
        return cityHotels;
        }

    /**
     * Converts an ArrayList of DistanceHotel objects into a regular Array of DistanceHotel objects.
     * @param distanceHotelArrayList The array to convert.
     * @return The Array of DistanceHotel objects as the result of the conversion.
     */
    private Hotel[] distanceHotelArrayListToHotelArray(ArrayList<DistanceHotel> distanceHotelArrayList) {
        Hotel[] cityHotels = new Hotel[distanceHotelArrayList.size()];
        int counter = 0;
        for (DistanceHotel distanceHotel : distanceHotelArrayList) {
            cityHotels[counter] = distanceHotel.getHotel();
            ++counter;
        } return cityHotels;
    }

    /**
     * Returns an Array of Hotel objects of the hotels that are in the given city.
     * @param city The city to get the hotels that are in it.
     * @return an Array of Hotel objects of the hotels that are in the given city.
     */
    private Hotel[] getHotelArrayInCity(String city) {
        ArrayList<Hotel> cityHotelsArray = new ArrayList<>();
        for (Hotel hotel : boopingSiteHotels) {
            if (hotel.getCity().equals(city)) {
                cityHotelsArray.add(hotel);
            }
        }
        return hotelArrayListToHotelArray(cityHotelsArray);
    }

    /**
     * Returns an ArrayList of Hotel objects that are in the given city.
     * @param city The given city to get the hotels that are in it.
     * @return an ArrayList of Hotel objects that are in the given city.
     */
    private ArrayList<Hotel> getHotelArrayListInCity(String city) {
        ArrayList<Hotel> cityHotelsArray = new ArrayList<>();
        for (Hotel hotel : boopingSiteHotels) {
            if (hotel.getCity().equals(city)) {
                cityHotelsArray.add(hotel);
            }
        } return cityHotelsArray;
    }

    /**
     * Return an Array of Hotel objects of the hotels that are in the given city, sorted by their star
     * ranking.
     * @param city The given city.
     * @return an Array of Hotel objects of the hotels that are in the given city, sorted by their star
     * ranking.
     */
    public Hotel[] getHotelsInCityByRating(String city){
        ArrayList<Hotel> cityHotels = getHotelArrayListInCity(city);
        Collections.sort(cityHotels, new RatingCompare());
        return hotelArrayListToHotelArray(cityHotels);
    }

    /**
     * Executes the main part of the method getHotelsByProximityHelper.
     * @param hotelsArray The Array of Hotel objects to sort according to the distance from the given
     *                    coordinates.
     * @param latitude The latitude of the position to sort by the distance from it.
     * @param longitude The longitude of the position to sort by the distance from it.
     * @return the sorted Array of Hotel objects according to the distance from the given coordinates.
     */
    private Hotel[] getHotelsByProximityHelper(Hotel[] hotelsArray, double latitude, double longitude) {
        if(latitude > LATITUDE_MAX || latitude < LATITUDE_MIN ||
                longitude > LONGITUDE_MAX || longitude < LONGITUDE_MIN){
            return new Hotel[0];
        }
        ArrayList<DistanceHotel> distanceHotels = new ArrayList<>();
        for(Hotel hotel : hotelsArray){
            Double[] hotelPosition = {hotel.getLatitude(), hotel.getLongitude()};
            Double[] givenPosition = {latitude, longitude};
            double distance = DistanceBetweenLocations.getDistance(hotelPosition, givenPosition);
            distanceHotels.add(new DistanceHotel(hotel, distance));
        } Collections.sort(distanceHotels, new DistanceCompare());
        return distanceHotelArrayListToHotelArray(distanceHotels);
    }

    /**
     * Returns the sorted Array of Hotel objects according to the distance from the given coordinates.
     * @param latitude The latitude of the position to sort by the distance from it.
     * @param longitude The longitude of the position to sort by the distance from it.
     * @return the sorted Array of Hotel objects according to the distance from the given coordinates.
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude){
       return getHotelsByProximityHelper(boopingSiteHotels, latitude, longitude);
    }

    /**
     * Returns the sorted Array of Hotel objects in the given city according to the distance from the given
     * coordinates.
     * @param city The given city.
     * @param latitude The latitude of the position to sort by the distance from it.
     * @param longitude The longitude of the position to sort by the distance from it.
     * @return the sorted Array of Hotel objects in the given city according to the distance from the given
     * coordinates.
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude){
        return getHotelsByProximityHelper(getHotelArrayInCity(city), latitude, longitude);
    }
}
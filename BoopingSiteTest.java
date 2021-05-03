import oop.ex3.searchengine.Hotel;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The test for the BoopingSite class.
 */
public class BoopingSiteTest {

    private static final String DATASET = "hotels_dataset.txt";
    private static final String JERUSALEM = "jerusalem";
    private static final String INCORRECT_RATING_MESSAGE = "Incorrect sort by rating";
    private static final String INCORRECT_PROXIMITY_IN_CITY_MESSAGE = "Incorrect sort by proximity in city";
    private static final String INCORRECT_PROXIMITY_MESSAGE = "Incorrect sort by proximity";

    private static final String MY_CITY = "manali";
    private static final double APPLE_INN_COTTAGE_LATITUDE = 32.2236026;
    private static final double APPLE_INN_COTTAGE_LONGITUDE = 77.1858995;

    public static final BoopingSite totalBoopingSite = new BoopingSite(DATASET);

    /**
     * The test for the getHotelsInCityByRating method.
     * It checks that indeed the sorting is right.
     */
    @Test
    public void testGetHotelsInCityByRating() {
        Hotel[] actualArray = totalBoopingSite.getHotelsInCityByRating(MY_CITY);
        int actualArrayLength = actualArray.length;
        if (actualArrayLength > 0) {
            Hotel previousHotel = actualArray[0];
            for (int i = 1; i < actualArrayLength - 1; ++i) {
                Hotel currentHotel = actualArray[i];
                RatingCompare ratingCompare = new RatingCompare();
                assertTrue(INCORRECT_RATING_MESSAGE,ratingCompare.compare
                        (previousHotel, currentHotel) <= 0);
                previousHotel = currentHotel;
            }
        }
    }

    /**
     * Tests the method getHotelsInCityByProximity.
     * It checks that indeed the sorting is right.
     */
    @Test
    public void testGetHotelsInCityByProximity() {

        Hotel[] actualArray = totalBoopingSite.getHotelsInCityByProximity(MY_CITY,
                APPLE_INN_COTTAGE_LATITUDE, APPLE_INN_COTTAGE_LONGITUDE);

        testProximityHelper(INCORRECT_PROXIMITY_IN_CITY_MESSAGE, actualArray);
    }

    /**
     * Tests the method getHotelsByProximity.
     * It checks that indeed the sorting is right.
     */
    @Test
    public void testGetHotelsByProximity() {

        Hotel[] actualArray = totalBoopingSite.
                getHotelsByProximity
                        (APPLE_INN_COTTAGE_LATITUDE, APPLE_INN_COTTAGE_LONGITUDE);

        testProximityHelper(INCORRECT_PROXIMITY_MESSAGE, actualArray);
    }

    /**
     * The main implementation of the testing of the proximity methods.
     * @param message     Message to print if it fails.
     * @param actualArray The array that the BoopingSite code gets.
     */
    public void testProximityHelper(String message, Hotel[] actualArray) {
        int actualArrayLength = actualArray.length;
        if (actualArrayLength > 0) {
            Hotel previousHotel = actualArray[0];
            Double[] givenLocation = {APPLE_INN_COTTAGE_LATITUDE, APPLE_INN_COTTAGE_LONGITUDE};
            for (int i = 1; i < actualArrayLength - 1; ++i) {
                Hotel currentHotel = actualArray[i];
                Double[] currentHotelLocation = {currentHotel.getLatitude(), currentHotel.getLongitude()};
                Double[] previousHotelLocation = {previousHotel.getLatitude(), previousHotel.getLongitude()};
                double currentHotelProximity = DistanceBetweenLocations.getDistance(currentHotelLocation,
                        givenLocation);
                double previousHotelProximity = DistanceBetweenLocations.getDistance(previousHotelLocation,
                        givenLocation);
                DistanceHotel distanceCurrentHotel = new DistanceHotel(currentHotel, currentHotelProximity);
                DistanceHotel distancePreviousHotel = new DistanceHotel(previousHotel, previousHotelProximity);
                DistanceCompare distanceCompare = new DistanceCompare();
                assertTrue(message,
                        distanceCompare.compare(distancePreviousHotel, distanceCurrentHotel) <= 0);
                previousHotel = currentHotel;
            }
        }
    }

    /**
     * Tests for illegal inputs the method getHotelsByProximity.
     */
    @Test
    public void testIllegalProximityInput() {

        assertEquals(0, totalBoopingSite.getHotelsByProximity(-90.01, 0).length);
        assertEquals(0, totalBoopingSite.getHotelsByProximity(90.01, 0).length);

        assertEquals(0, totalBoopingSite.getHotelsByProximity(0, -180.01).length);
        assertEquals(0, totalBoopingSite.getHotelsByProximity(0, 180.01).length);

        assertEquals(0, totalBoopingSite.
                getHotelsInCityByProximity(MY_CITY, -90.01, 0).length);

        assertEquals(0, totalBoopingSite.
                getHotelsInCityByProximity(MY_CITY, 90.01, 0).length);

        assertEquals(0, totalBoopingSite.
                getHotelsInCityByProximity(MY_CITY, 0, -180.01).length);

        assertEquals(0, totalBoopingSite.
                getHotelsInCityByProximity(MY_CITY, 0, 180.01).length);
    }

    /**
     * Tests for empty array the method getHotelsInCityByRating.
     */
    @Test
    public void testForEmptyArrayGetHotelsInCityByRating() {
        Hotel[] actualArray = totalBoopingSite.getHotelsInCityByRating(JERUSALEM);
        assertEquals(0, actualArray.length);
    }
}
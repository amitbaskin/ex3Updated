/**
 * Abstract class for calculating the euclidean distance between two locations.
 */
public abstract class DistanceBetweenLocations {

    /**
     * Calculates the euclidean distance between the two given locations.
     * @param location1 First location.
     * @param location2 Second location.
     * @return the euclidean distance between the two given locations.
     */
    public static double getDistance(Double[] location1, Double[] location2){
        double latitudeDifferenceSquare = Math.pow(location1[0] - location2[0], 2);
        double longitudeDifferenceSquare = Math.pow(location1[1] - location2[1], 2);
        return Math.sqrt((latitudeDifferenceSquare + longitudeDifferenceSquare));
    }
}
package com.bubbles.server.util;

/**
 *
 * Ref: http://www.movable-type.co.uk/scripts/latlong.html
 *
 * @author Mingshan Lei
 * @since  2015/7/3.
 */
public class GeoPositionUtils {

    private static final double earthRadius = 6371000; //meters, 3958.75 miles (or 6371.0 kilometers)

    public static double getEarthRadius() {
        return earthRadius;
    }

    /**
     * Calculate distance between two points on the earth.
     * This uses the ‘haversine’ formula to calculate the great-circle distance between two points –
     * that is, the shortest distance over the earth’s surface – giving an ‘as-the-crow-flies’
     * distance between the points
     *
     * @param lat1 latitude of start point
     * @param lon1 longitude of start point
     * @param lat2 latitude of end point
     * @param lon2 longitude of end point
     * @return the calculated distance between two points
     */
    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double dist = earthRadius * c;
        return dist;
    }

    /**
     * Given a start point, initial bearing, and distance, this will calculate the destination point
     * and final bearing travelling along a (shortest distance) great circle arc.
     *
     * @param lat1 latitude of start point
     * @param lon1 longitude of start point
     * @param bearing bearing (clockwise from north)
     * @param distance distance to the destination point
     * @return the destination point position
     */
    public static GeoPosition getDestinationPosition(double lat1, double lon1, double bearing, double distance) {
        double angDist = distance / earthRadius;
        double dBearing = Math.toRadians(bearing);
        double dLat1 = Math.toRadians(lat1);
        double dLon1 = Math.toRadians(lon1);

        double dLat2 = Math.asin(Math.sin(dLat1) * Math.cos(angDist) + Math.cos(dLat1) * Math.sin(angDist) * Math.cos(dBearing));
        double dLon2 =  dLon1 + Math.atan2(Math.sin(dBearing) * Math.sin(angDist) * Math.cos(dLat1), Math.cos(angDist) - Math.sin(dLat1) * Math.sin(dLat2));

        double lat2 = Math.toDegrees(dLat2);
        double lon2 = Math.toDegrees(dLon2);

        return new GeoPosition(lat2, lon2);
    }

}

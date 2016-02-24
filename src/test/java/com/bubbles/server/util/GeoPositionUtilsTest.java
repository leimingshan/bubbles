package com.bubbles.server.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for GeoPosition & GeoPositionUtils.
 *
 * @author Mingshan Lei
 * @since 2015/7/3
 */
public class GeoPositionUtilsTest {

    @Test
    public void testGetEarthRadius() throws Exception {
        assertEquals(6371000, GeoPositionUtils.getEarthRadius(), 0.001);
    }

    @Test
    public void testGetDistance() throws Exception {
        double distance = GeoPositionUtils.getDistance(39.97799365967107, 116.36438688283737, 39.995980091789434, 116.36438688283737);
        System.out.println("distance: " + distance);
        assertNotEquals(0, distance);
        assertEquals(2000, distance, 0.1);
    }

    @Test
    public void testGetDestinationPosition() throws Exception {
        GeoPosition result = GeoPositionUtils.getDestinationPosition(39.97799365967107, 116.36438688283737, 0.0, 2000.0);
        assertNotNull(result);
        System.out.println(result.toString());
    }
}

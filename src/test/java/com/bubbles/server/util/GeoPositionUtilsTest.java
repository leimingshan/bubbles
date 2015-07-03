package com.bubbles.server.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for GeoPositionUtils
 *
 * @author Mingshan Lei
 * @since 2015/7/3
 */
public class GeoPositionUtilsTest {

    @Test
    public void testGetEarthRadius() throws Exception {

    }

    @Test
    public void testGetDistance() throws Exception {

    }

    @Test
    public void testGetDestinationPosition() throws Exception {
        GeoPosition result = GeoPositionUtils.getDestinationPosition(39.97799365967107, 116.36438688283737, 0.0, 2000.0);
        System.out.println(result.toString());
    }
}
package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.Location;
import com.epam.jwd.exception.FactoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationFactoryTest {
    private final double latitude = 155.21;
    private final double longitude = 214.56;
    private final String address = "Минск, проспект Дзержинского, 95";
    private final String secondAddress = "Минск, проспект Дзержинского, 115";
    private final Location location = new Location(latitude, longitude, address);
    private LocationFactory locationFactory = LocationFactory.getInstance();

    @Test
    public void rightEqualsLocationsTest() throws FactoryException {
        Location testLocation = locationFactory.create(latitude, longitude, address);
        assertEquals(location, testLocation);
    }

    @Test
    public void wrongEqualsLocationsTest() throws FactoryException {
        Location testLocation = locationFactory.create(latitude, longitude, secondAddress);
        assertNotEquals(location, testLocation);
    }

    @Test(expected = FactoryException.class)
    public void wrongLocationTest() throws FactoryException {
        locationFactory.create(latitude, longitude);
        Assert.fail("Expected exception");
    }


}

package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.Location;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.AbstractFactory;

public class LocationFactory implements AbstractFactory<Location> {

    private LocationFactory() {
    }

    private static LocationFactory locationFactory;

    public static LocationFactory getInstance() {
        if (locationFactory == null) {
            locationFactory = new LocationFactory();
        }
        return locationFactory;
    }

    @Override
    public Location create(Object... args) throws FactoryException {
        switch (args.length) {
            case 4:
                return new Location((long) args[0], (double) args[1], (double) args[2], (String) args[3]);
            case 3:
                return new Location((double) args[0], (double) args[1], (String) args[2]);
            default:
                throw new FactoryException("Location factory : Illegal arguments length : " + args.length);
        }
    }
}

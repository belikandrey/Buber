package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.impl.Car;
import com.epam.jwd.domain.impl.Driver;
import com.epam.jwd.exception.FactoryException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarFactoryTest {
    private Driver driver;
    private Car car;
    private static final String NUMBER = "1111 AC-4";
    private static final String BRAND = "BMW";
    private static final String MODEL = "M5";
    private static final String SECOND_MODEL = "X6";
    private static final String COLOR = "White";
    private final CarFactory carFactory = CarFactory.getInstance();

    @Before
    public void initCar() {
        driver = Driver.newBuilder().build();
        car = new Car(driver, NUMBER, BRAND, MODEL, COLOR);
    }

    @Test
    public void rightEqualsCarTest() throws FactoryException {
        Car testCar = carFactory.create(driver, NUMBER, BRAND, MODEL, COLOR);
        assertEquals(car, testCar);
    }

    @Test
    public void wrongEqualsCarTest() throws FactoryException {
        Car testCar = carFactory.create(driver, NUMBER, BRAND, SECOND_MODEL, COLOR);
        assertNotEquals(car, testCar);
    }

    @Test(expected = FactoryException.class)
    public void wrongCarTest() throws FactoryException {
        carFactory.create(NUMBER, BRAND, MODEL, COLOR);
    }


}

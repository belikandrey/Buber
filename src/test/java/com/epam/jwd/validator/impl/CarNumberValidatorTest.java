package com.epam.jwd.validator.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarNumberValidatorTest {
    private static final String VALID_NUMBER_FIRST = "6711 AC-4";
    private static final String VALID_NUMBER_SECOND = "1111 ZC-7";
    private static final String INVALID_NUMBER_FIRST = "1234AA-6";
    private static final String INVALID_NUMBER_SECOND = "1234 AB-9";
    private CarNumberValidator carNumberValidator = CarNumberValidator.CAR_NUMBER_VALIDATOR;

    @Test
    public void validateValidNumber() {
        assertTrue(carNumberValidator.validate(VALID_NUMBER_FIRST));
    }

    @Test
    public void validateSecondValidNumber() {
        assertTrue(carNumberValidator.validate(VALID_NUMBER_SECOND));
    }

    @Test
    public void validateInvalidNumber() {
        assertFalse(carNumberValidator.validate(INVALID_NUMBER_FIRST));
    }

    @Test
    public void validateSecondInvalidNumber() {
        assertFalse(carNumberValidator.validate(INVALID_NUMBER_SECOND));
    }

}

package com.epam.jwd.validator.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardDateValidatorTest {

    private static final String VALID_DATE_FIRST = "10/22";
    private static final String VALID_DATE_SECOND = "03/25";
    private static final String INVALID_DATE_FIRST = "25/24";
    private static final String INVALID_DATE_SECOND = "12.23";
    private CardDateValidator cardDateValidator = CardDateValidator.CARD_DATE_VALIDATOR;

    @Test
    public void validateValidDate() {
        assertTrue(cardDateValidator.validate(VALID_DATE_FIRST));
    }

    @Test
    public void validateSecondValidDate() {
        assertTrue(cardDateValidator.validate(VALID_DATE_SECOND));
    }

    @Test
    public void validateWrongDate() {
        assertFalse(cardDateValidator.validate(INVALID_DATE_FIRST));
    }

    @Test
    public void validateSecondWrongDate() {
        assertFalse(cardDateValidator.validate(INVALID_DATE_SECOND));
    }
}

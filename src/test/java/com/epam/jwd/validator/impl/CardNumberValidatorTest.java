package com.epam.jwd.validator.impl;

import static org.junit.Assert.*;
import org.junit.Test;

public class CardNumberValidatorTest {

    private static final String VALID_NUMBER_FIRST = "4255200042551998";
    private static final String VALID_NUMBER_SECOND = "5511231245673689";
    private static final String INVALID_NUMBER_FIRST = "4255 1234 4353 1232";
    private static final String INVALID_NUMBER_SECOND = "9512123111112345";
    private CardNumberValidator cardNumberValidator = CardNumberValidator.CARD_NUMBER_VALIDATOR;

    @Test
    public void validateValidNumber(){
        assertTrue(cardNumberValidator.validate(VALID_NUMBER_FIRST));
    }

    @Test
    public void validateSecondValidNumber(){
        assertTrue(cardNumberValidator.validate(VALID_NUMBER_SECOND));
    }

    @Test
    public void validateInvalidNumber(){
        assertFalse(cardNumberValidator.validate(INVALID_NUMBER_FIRST));
    }

    @Test
    public void validateSecondInvalidNumber(){
        assertFalse(cardNumberValidator.validate(INVALID_NUMBER_SECOND));
    }
}

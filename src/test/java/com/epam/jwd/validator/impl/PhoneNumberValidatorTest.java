package com.epam.jwd.validator.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneNumberValidatorTest {
    private static final String VALID_PHONE_FIRST = "+375333274122";
    private static final String VALID_PHONE_SECOND = "+375251234444";
    private static final String INVALID_PHONE_FIRST = "375291234445";
    private static final String INVALID_PHONE_SECOND = "37533321221";
    private PhoneNumberValidator phoneNumberValidator = PhoneNumberValidator.PHONE_NUMBER_VALIDATOR;

    @Test
    public void validateValidPhone(){
        assertTrue(phoneNumberValidator.validate(VALID_PHONE_FIRST));
    }

    @Test
    public void validateSecondValidPhone(){
        assertTrue(phoneNumberValidator.validate(VALID_PHONE_SECOND));
    }

    @Test
    public void validateInvalidPhone(){
        assertFalse(phoneNumberValidator.validate(INVALID_PHONE_FIRST));
    }

    @Test
    public void validateSecondInvalidPhone(){
        assertFalse(phoneNumberValidator.validate(INVALID_PHONE_SECOND));
    }

}

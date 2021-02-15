package com.epam.jwd.validator.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginValidatorTest {
    private static final String VALID_LOGIN_FIRST = "andreybelik";
    private static final String VALID_LOGIN_SECOND = "andreyka12223";
    private static final String INVALID_LOGIN_FIRST = "and";
    private static final String INVALID_LOGIN_SECOND = "$andret";
    private LoginValidator loginValidator = LoginValidator.LOGIN_VALIDATOR;

    @Test
    public void validateValidLogin(){
        assertTrue(loginValidator.validate(VALID_LOGIN_FIRST));
    }

    @Test
    public void validateSecondValidLogin(){
        assertTrue(loginValidator.validate(VALID_LOGIN_SECOND));
    }

    @Test
    public void validateInvalidLogin(){
        assertFalse(loginValidator.validate(INVALID_LOGIN_FIRST));
    }

    @Test
    public void validateSecondInvalidLogin(){
        assertFalse(loginValidator.validate(INVALID_LOGIN_SECOND));
    }

}

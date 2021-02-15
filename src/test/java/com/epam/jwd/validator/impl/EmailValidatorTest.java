package com.epam.jwd.validator.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {
    private static final String VALID_MAIL_FIRST = "andreybelik@gmail.com";
    private static final String VALID_MAIL_SECOND = "andreyka2145@mail.ru";
    private static final String INVALID_MAIL_FIRST = "and@fdser";
    private static final String INVALID_MAIL_SECOND = "andremailru";
    private EmailValidator emailValidator = EmailValidator.EMAIL_VALIDATOR;

    @Test
    public void validateValidMail(){
        assertTrue(emailValidator.validate(VALID_MAIL_FIRST));
    }

    @Test
    public void validateSecondValidMail(){
        assertTrue(emailValidator.validate(VALID_MAIL_SECOND));
    }

    @Test
    public void validateInvalidMail(){
        assertFalse(emailValidator.validate(INVALID_MAIL_FIRST));
    }

    @Test
    public void validateSecondInvalidMail(){
        assertFalse(emailValidator.validate(INVALID_MAIL_SECOND));
    }
}

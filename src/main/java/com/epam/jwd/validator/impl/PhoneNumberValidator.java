package com.epam.jwd.validator.impl;

import com.epam.jwd.validator.Validator;

public class PhoneNumberValidator implements Validator<String> {

    public static final PhoneNumberValidator PHONE_NUMBER_VALIDATOR = new PhoneNumberValidator();

    private PhoneNumberValidator(){}

    private final String regex = "^\\+375(17|29|33|44)[0-9]{7}$";

    @Override
    public boolean validate(String s) {
        return s.matches(regex);
    }
}

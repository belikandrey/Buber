package com.epam.jwd.validator.impl;

import com.epam.jwd.validator.Validator;

public class CarNumberValidator implements Validator<String> {

    public static final CarNumberValidator CAR_NUMBER_VALIDATOR = new CarNumberValidator();

    private CarNumberValidator() {
    }

    private final String regex = "[0-9]{4} [A-Z]{2}-[1-7]{1}";

    @Override
    public boolean validate(String s) {
        return s.matches(regex);
    }
}
